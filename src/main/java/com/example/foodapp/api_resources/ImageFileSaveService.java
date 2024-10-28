package com.example.foodapp.api_resources;

import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
import static com.fasterxml.uuid.impl.UUIDUtil.uuid;
import static java.time.LocalTime.now;


@Log4j2
public class ImageFileSaveService {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/static/media/";
    public static String slugify(String input) {
        String nonwhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nonwhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }


    public static byte[] getImage(String filename) throws Exception {
        try {
            File file = new File(UPLOAD_DIR + filename);
            Resource resource = new FileSystemResource(file);
            return resource.getContentAsByteArray();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("File is not found.");
        }
    }

    public static String saveFile(String fileName, MultipartFile multipartFile)
            throws Exception {
        if (multipartFile.getSize() >= 1048576) {
            throw new FileSizeLimitExceededException(
                    "File size exceeded",
                    multipartFile.getSize(),
                    1048576);
        }
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
        String fileCode = createBusinessId(10);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            if (multipartFile.getOriginalFilename() != null) {
                var img_name = multipartFile.getOriginalFilename().split("\\.");
                String file = fileCode + "-" + slugify(img_name[0]) + "." + img_name[1];
                Path fileNameAndPath = Paths.get(UPLOAD_DIR, file);
                Files.write(fileNameAndPath, multipartFile.getBytes());
                return file;
            }
            return "File is not saved";
//            return path.toString();
        } catch (Exception e) {
            log.error(e);
            System.out.println(e.getMessage());
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
