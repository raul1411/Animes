package com.example.anime.controller;

import com.example.anime.domain.dto.FileResult;
import com.example.anime.domain.dto.GeneralList;
import com.example.anime.domain.model.File;
import com.example.anime.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/")
    public GeneralList list() {
        return new GeneralList(fileRepository.findBy());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable UUID id) {
        File file = fileRepository.findById(id).orElse(null);

        if (file == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.bytes.length)
                .body(file.bytes);
    }

    @PostMapping("/")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile uploadedFile) {
        try {
            File file = new File();
            file.contenttype = uploadedFile.getContentType();
            file.bytes = uploadedFile.getBytes();
            fileRepository.save(file);
            FileResult fileResult = new FileResult(file.fileid, file.contenttype);
            return ResponseEntity.ok().body(fileResult);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable UUID id) {
        File f = fileRepository.findById(id).orElse(null);

        if (f == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha trobat l'arxiu amd id " + id);
        }
        else {
            fileRepository.delete(f);
            return ResponseEntity.ok().body("S'ha eliminat l'arxiu amd id " + id);
        }
    }

    @DeleteMapping("/")
    public void deleteFiles() {
        fileRepository.deleteAll();
    }
}