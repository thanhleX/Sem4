package org.example.project4.WebPacket.Application.Controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class ControllerImages {

    private final RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    public ControllerImages(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String url = "TERABOX_UPLOAD_URL"; // Terabox upload API endpoint

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer YOUR_ACCESS_TOKEN"); // If token is required

        // Create Multipart Request
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", file.getResource());

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        // Send POST request to Terabox
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Handle response
        return response.getBody();
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("fileName") String fileName) {
        String url = "TERABOX_DOWNLOAD_URL"; // Terabox download API endpoint

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer YOUR_ACCESS_TOKEN"); // If token is required

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Send GET request to download file
        ResponseEntity<byte[]> response = restTemplate.exchange(
                url + "?file=" + fileName,
                HttpMethod.GET,
                requestEntity,
                byte[].class);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(response.getBody());
    }

    @DeleteMapping("/delete") // Added @DeleteMapping for clarity
    public String deleteFile(@RequestParam("fileName") String fileName) {
        String url = "TERABOX_DELETE_URL"; // Terabox delete API endpoint

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer YOUR_ACCESS_TOKEN"); // If token is required

        // Create Request to delete file
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Send DELETE request to delete file on Terabox
        ResponseEntity<String> response = restTemplate.exchange(
                url + "?file=" + fileName,
                HttpMethod.DELETE,
                requestEntity,
                String.class
        );

        // Handle response from API
        return response.getBody();
    }
}