package com.example.foodapp.product.resource.general;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/general/product/image")
@RequiredArgsConstructor
public class ImageDownload {
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) throws IOException
    {
        String path = "download/" + id;
        var imgFile = new ClassPathResource(path);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        System.out.println(imgFile);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
