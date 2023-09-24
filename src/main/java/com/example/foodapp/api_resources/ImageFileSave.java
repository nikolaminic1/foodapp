package com.example.foodapp.api_resources;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.example.foodapp.api_resources.RandomStringGenerator.createBusinessId;


public class ImageFileSave {
    public static String saveFile(String fileName, MultipartFile multipartFile)
            throws IOException {

//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
        String fileCode = createBusinessId(10);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            String download = "download/";
            String path = fileCode + "-" + fileName;
            String a = System.getProperty("user.dir");
            String fullPath = a + "/src/main/resources/" + download + path;
            Path path1 = Paths.get(fullPath);
            Files.copy(inputStream, path1, StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (IOException ioe) {
            System.out.println(ioe);
            return "File is not saved";
        }
    }

    public static String saveProductFile(String fileName, MultipartFile multipartFile)
            throws IOException {

//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
        String fileCode = createBusinessId(10);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            String download = "download/";
            String path = fileCode + "-" + fileName;
            String a = System.getProperty("user.dir");
            String fullPath = a + "/src/main/resources/" + download + path;
            Path path1 = Paths.get(fullPath);
            Files.copy(inputStream, path1, StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (IOException ioe) {
            System.out.println(ioe);
            return "File is not saved";
        }
    }

}
