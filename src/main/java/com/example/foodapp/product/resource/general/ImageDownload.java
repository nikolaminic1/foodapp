package com.example.foodapp.product.resource.general;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
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
            String path = "/media/" + id;
            var imgFile = new ClassPathResource(path);
            System.out.println(imgFile);
            byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
            System.out.println(imgFile);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
