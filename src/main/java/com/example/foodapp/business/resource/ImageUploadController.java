package com.example.foodapp.business.resource;


import com.example.foodapp.api_resources.ImageFileSaveService;
import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.service.owner_service.OwnerBusinessService;
import com.example.foodapp.product.model.Image;
import com.example.foodapp.product.repo.ImageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static java.time.LocalDateTime.now;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
@Log4j2
public class ImageUploadController {
    private static OwnerBusinessService businessService;
    private final ImageRepo imageRepo;

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadImage(@RequestParam("file") MultipartFile file) throws IOException{
        if (file != null){
           String fileName = StringUtils.cleanPath(file.getOriginalFilename());
           long size = file.getSize();

           String fileCode = ImageFileSaveService.saveFile(fileName, file);

           String pathToSave = "/general/product/image/" + fileCode;
           System.out.println(pathToSave);
           Image image = new Image();
           image.setImageUrl(pathToSave);
           image.setSize(size);
           image.setNameOfImage(fileName);
           imageRepo.save(image);
        }

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Image uploaded...")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );

    }

    @PostMapping("/product_image_upload")
    public ResponseEntity<Response> productImageUpload(@RequestParam("file") MultipartFile file,
                                                       @RequestParam("productId") Long id) throws IOException{
        if (file != null){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            long size = file.getSize();

            String fileCode = ImageFileSaveService.saveFile(fileName, file);

            String pathToSave = "/general/product/image/" + fileCode;
            System.out.println(pathToSave);
            Image image = new Image();
            image.setImageUrl(pathToSave);
            image.setSize(size);
            image.setNameOfImage(fileName);
            imageRepo.save(image);
        }

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Image uploaded...")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );

    }

//    @GetMapping("/download/{filename}")
//    public ResponseEntity<>
}
