package com.example.foodapp._api;


import com.example.foodapp.api_resources.ImageFileSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static com.example.foodapp.api_resources.ImageFileSaveService.getImage;

@RestController
@RequestMapping("/api/v1/media/")
@RequiredArgsConstructor
public class ImageDownload {
    @GetMapping("{filename}")
    public ResponseEntity<byte[]> getImageResource(@PathVariable String filename) throws IOException
    {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(getImage(filename));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
