package com.example.foodapp.product.resource.general;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/media/")
@RequiredArgsConstructor
public class ImageDownload {
    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) throws IOException
    {
        try {
            String path = "static/media/" + id;
            var imgFile = new ClassPathResource(path);
            System.out.println(path);
            byte[] bytes = path.getBytes();
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }
}
