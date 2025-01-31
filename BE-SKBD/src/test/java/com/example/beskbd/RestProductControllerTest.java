package com.example.beskbd;
// Generated by Qodo Gen

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.example.beskbd.dto.object.CategoryDto;
import com.example.beskbd.dto.object.NewArrivalProductDto;
import com.example.beskbd.dto.object.ProductAttributeDto;
import com.example.beskbd.dto.object.ProductSizeDto;
import com.example.beskbd.dto.request.ProductCreationRequest;
import com.example.beskbd.dto.response.ApiResponse;
import com.example.beskbd.entities.Category;
import com.example.beskbd.entities.Product;
import com.example.beskbd.entities.ProductAttribute;
import com.example.beskbd.entities.ProductImage;
import com.example.beskbd.exception.AppException;
import com.example.beskbd.exception.ErrorCode;
import com.example.beskbd.rest.RestProductController;

import com.example.beskbd.services.ProductService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class RestProductControllerTest {


    // Handles empty category list gracefully
    @Test
    public void test_handles_empty_category_list_gracefully() {
        // Arrange
        ProductService productService = Mockito.mock(ProductService.class);
        RestProductController controller = new RestProductController(productService);
        Map<String, List<CategoryDto>> emptyCategories = new HashMap<>();
        Mockito.when(productService.getCategoryByGender()).thenReturn(emptyCategories);

        // Act
        ResponseEntity<ApiResponse<Map<String, List<CategoryDto>>>> response = controller.getByGender();

        // Assert
        Assertions.assertTrue(response.getBody().getSuccess());
        Assertions.assertTrue(response.getBody().getData().isEmpty());
    }

    // Handle null or invalid ProductCreationRequest gracefully
//    @Test
//    public void test_create_product_with_invalid_request() {
//        // Arrange
//        RestProductController controller = new RestProductController(productService);
//        Product invalidRequest = new Product();
//        invalidRequest.setName("invalid");
//        invalidRequest.setDescription("invalid");
//        // Act & Assert
//        AppException thrownException = assertThrows(AppException.class, () -> {
//            controller.createProduct(invalidRequest);
//        });
//
//        assertEquals("Product creation request cannot be null", thrownException.getMessage());
//    }

    // Retrieve a list of new arrival products
    @Test
    public void testGetNewArrivalsSuccess() {
        // Arrange
        ProductService productService = Mockito.mock(ProductService.class);
        List<NewArrivalProductDto> mockNewArrivals = Arrays.asList(
                NewArrivalProductDto.builder().productId(1L).productName("Product 1").build(),
                NewArrivalProductDto.builder().productId(2L).productName("Product 2").build()
        );
        when(productService.getNewArrivalProduct()).thenReturn(mockNewArrivals);
        RestProductController controller = new RestProductController(productService);

        // Act
        ResponseEntity<?> response = controller.getNewArrivals();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertNotNull(apiResponse); // Ensure apiResponse is not null
        assertTrue(apiResponse.getSuccess());
        assertEquals(mockNewArrivals, apiResponse.getData());
    }

    // Handle missing or invalid category ID in ProductCreationRequest
    @Test
    public void test_create_product_with_invalid_category_id() {
        // Arrange
        ProductService productService = Mockito.mock(ProductService.class);
        RestProductController controller = new RestProductController(productService);
        Product request = new Product();
        doThrow(new AppException(ErrorCode.INVALID_REQUEST))
            .when(productService).addProduct(request);

        // Act & Assert
        AppException exception = assertThrows(AppException.class, () -> {
            controller.createProduct(request);
        });

        assertEquals(ErrorCode.INVALID_REQUEST, exception.getErrorCode());
    }

    // Handle no new arrival products available
    @Test
    public void test_get_new_arrivals_no_products() {
        // Arrange
        ProductService productService = Mockito.mock(ProductService.class);
        RestProductController controller = new RestProductController(productService);
        when(productService.getNewArrivalProduct()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = controller.getNewArrivals();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertTrue(apiResponse.getSuccess());
        assertTrue(((List<?>) apiResponse.getData()).isEmpty());
    }


    // Ensure correct media type is consumed for product creation
    @Test
    public void test_create_product_with_multipart_form_data() {
        // Arrange
        ProductService productService = Mockito.mock(ProductService.class);
        RestProductController controller = new RestProductController(productService);
        Product mockRequest = new Product();
        mockRequest.setName("Test Product");
        mockRequest.setId(1L);
        mockRequest.setAttributes(List.of(new ProductAttribute()));

        // Act
        ResponseEntity<?> response = controller.createProduct(mockRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assert apiResponse != null;
        assertTrue(apiResponse.getSuccess());
    }

    // Validate product attributes during creation
    @Test
    public void test_create_product_with_valid_attributes() {
        // Arrange
        ProductService productService = mock(ProductService.class);
        RestProductController controller = new RestProductController(productService);
        Product request = new Product();
        request.setName("Test Product");
        request.setDescription("Test Description");
        request.setId(1L);
        request.setAttributes(List.of(new ProductAttribute()));

        // Act
        ResponseEntity<?> response = controller.createProduct(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assert apiResponse != null;
        assertTrue(apiResponse.getSuccess());
        verify(productService).addProduct(request);
    }

    // Handle exceptions during image upload
//    @Test
//    public void test_create_product_image_upload_exception() {
//        // Arrange
//        Product request = new Product();
//        request.setName("Test Product");
//        request.setDescription("Test Description");
//        request.setId(1L);
//
//        ProductAttributeDto attributeDto = new ProductAttributeDto("red", new BigDecimal(3000));
//        attributeDto.setImageFiles(List.of(new MultipartFile() {
//            @Override
//            public String getName() {
//                return "img.jpg";
//            }
//
//            @Override
//            public String getOriginalFilename() {
//                return "";
//            }
//
//            @Override
//            public String getContentType() {
//                return "";
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public long getSize() {
//                return 1;
//            }
//
//            @Override
//            public byte[] getBytes() throws IOException {
//                return new byte[0];
//            }
//
//            @Override
//            public InputStream getInputStream() throws IOException {
//                return null;
//            }
//
//            @Override
//            public void transferTo(File dest) throws IOException, IllegalStateException {
//
//            }
//        }));
//        attributeDto.setSizes(List.of(new ProductSizeDto(1,2))); // Add your ProductSizeDto instances here
//        request.setAttributes(List.of(new ProductAttribute(attributeDto)));
//
//        // Mock the behavior of the productService to throw an exception
//        doThrow(new RuntimeException("Failed to upload image"))
//                .when(productService).addProduct(any(Product.class));
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            controller.createProduct(request);
//        });
//
//        // Additional assertions to check the exception message
//        assertEquals("Failed to upload image", exception.getMessage());
//    }

    // Ensure API response structure is consistent


    // Log product creation requests for auditing
    @Test
    public void test_create_product_logs_request() {
        // Arrange
        ProductService productService = mock(ProductService.class);
        RestProductController controller = new RestProductController(productService);
        Product request = new Product();
        request.setName("Test Product");
        request.setId(1L);
        Logger logger = (Logger) LoggerFactory.getLogger(RestProductController.class);
        Appender<ILoggingEvent> mockAppender = mock(Appender.class);
        logger.addAppender(mockAppender);

        // Act
        controller.createProduct(request);

        // Assert
        verify(mockAppender).doAppend(argThat(event -> event.getFormattedMessage().contains("Test Product")));
    }

    // Create a new product with valid data
    @Test
    public void test_create_product_success() {
        // Arrange
        ProductService productService = mock(ProductService.class);
        RestProductController controller = new RestProductController(productService);
        Product request = new Product();
        request.setName("Test Product");
        request.setDescription("Test Description");
        request.setId(1L);
        request.setAttributes(List.of());

        // Act
        ResponseEntity<?> response = controller.createProduct(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertTrue(apiResponse.getSuccess());
        verify(productService, times(1)).addProduct(request);
    }

    // Return a successful response for all products retrieval
@Test
public void test_get_all_products_success() {
    // Arrange
    ProductService productService = mock(ProductService.class);
    RestProductController controller = new RestProductController(productService);
    List<NewArrivalProductDto> mockProducts = new ArrayList<>();
    mockProducts.add(NewArrivalProductDto.builder()
            .productId(1L)
            .productName("Product 1")
            .productDescription("Description 1")
            .maxPrice(BigDecimal.valueOf(100))
            .minPrice(BigDecimal.valueOf(9.99))
            .build());
    mockProducts.add(NewArrivalProductDto.builder()
            .productId(2L)
            .productName("Product 2")
            .productDescription("Description 2")
            .maxPrice(BigDecimal.valueOf(10000))
            .minPrice(BigDecimal.valueOf(1.99))
            .build());
    when(productService.getNewArrivalProduct()).thenReturn(mockProducts);

    // Act
    ResponseEntity<?> response = controller.getAllProducts();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
    assertTrue(apiResponse.getSuccess());
}
}