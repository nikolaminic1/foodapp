package com.example.foodapp.api_resources;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import static com.example.foodapp.api_resources.RandomStringGenerator.createBusinessId;


@Log4j2
public class ImageFileSaveService {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    public static String slugify(String input) {
        String nonwhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nonwhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static String saveFile(String fileName, MultipartFile multipartFile)
            throws IOException {

//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
        String fileCode = createBusinessId(10);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            String path = fileCode + "-" + slugify(fileName) + ".jpg";
            String a = System.getProperty("user.dir");
            String fullPath = a + "/src/main/resources/media" + path;
            Path path1 = Paths.get(fullPath);
            log.error(path1);
            Files.copy(inputStream, path1, StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return "File is not saved";
        }
    }

//    public static String saveProductFile(String fileName, MultipartFile multipartFile)
//            throws IOException {
//
////        if (!Files.exists(uploadPath)) {
////            Files.createDirectories(uploadPath);
////        }
//        String fileCode = createBusinessId(10);
//
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            String download = "download/";
//            String path = fileCode + "-" + fileName;
//            String a = System.getProperty("user.dir");
//            String fullPath = a + "/src/main/resources/" + download + path;
//            Path path1 = Paths.get(fullPath);
//            Files.copy(inputStream, path1, StandardCopyOption.REPLACE_EXISTING);
//            return path;
//        } catch (IOException ioe) {
//            System.out.println(ioe);
//            return "File is not saved";
//        }
//    }

}
