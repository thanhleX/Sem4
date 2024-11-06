package test.project4v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.project4v2.Mediator.Mediator;
 // Ensure the correct package
import test.project4v2.command.C.AddProductCommand;
import test.project4v2.command.D.DeleteProductCommand;
import test.project4v2.command.U.UpdateProductCommand;
import test.project4v2.dto.ProductDTO;
import test.project4v2.query.GetAllProductQuery;
import test.project4v2.query.GetProductsIdQuery;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/products") // Use a consistent API prefix
public class ProductController {

    @Autowired
    private Mediator mediator;

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        GetProductsIdQuery query = new GetProductsIdQuery(id);
        ProductDTO product = mediator.send(query);
        return ResponseEntity.ok(product);
    }

    // Add Product (Admin)
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductCommand command) {
        ProductDTO productDTO = mediator.send(command);
        return ResponseEntity.ok(productDTO);
    }

    // Update Product (Admin)
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody UpdateProductCommand command) {
        command.setId(id); // Set the ID from the path variable
        ProductDTO updatedProduct = mediator.send(command); // Ensure this returns a single ProductDTO
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete Product (Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        mediator.send(new DeleteProductCommand(id));
        return ResponseEntity.ok("Product deleted successfully.");
    }

    // Get All Products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = mediator.send(new GetAllProductQuery());
        System.out.println("Retrieved products: " + products);
        return ResponseEntity.ok(products);
    }
}