package site.sugarnest.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.sugarnest.backend.service.upload.CloudinaryService;

import java.io.IOException;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/images")
public class ImageUploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Map uploadResult = cloudinaryService.upload(file);
            String secureUrl = (String) uploadResult.get("secure_url");
            return new ResponseEntity<>(secureUrl, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
