package br.com.escconsulting.controller;

import br.com.escconsulting.entity.File;
import br.com.escconsulting.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileController {

    private final FileService fileService;

    @GetMapping("/{fileId}")
    public ResponseEntity<File> findById(@PathVariable("fileId") UUID fileId) {
        return fileService.findById(fileId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<File>> findAll() {
        List<File> listFile = fileService.findAll();
        return ResponseEntity.ok(listFile);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody File file) {
        return fileService.save(file)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save file approved."));
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<?> update(@PathVariable("fileId") UUID fileId,
                                    @RequestBody File file) {
        return fileService.update(fileId, file)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update file approved."));
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<?> delete(@PathVariable("fileId") UUID fileId) {
        fileService.delete(fileId);
        return ResponseEntity.noContent().build();
    }
}