package br.com.escconsulting.controller;

import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.service.FileApprovedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file-approved")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileApprovedController {

    private final FileApprovedService fileApprovedService;

    @GetMapping("/{id}")
    public ResponseEntity<FileApproved> findById(@PathVariable("id") UUID fileApprovedId) {
        FileApproved fileApproved = fileApprovedService.findById(fileApprovedId);
        return ResponseEntity.ok(fileApproved);
    }

    @GetMapping("/by/fileId/{id}")
    public ResponseEntity<FileApproved> findByFileId(@PathVariable("id") UUID fileId) {
        FileApproved fileApproved = fileApprovedService.findByFileId(fileId);
        return ResponseEntity.ok(fileApproved);
    }

    @GetMapping
    public ResponseEntity<List<FileApproved>> findAll() {
        List<FileApproved> listFileApproved = fileApprovedService.findAll();
        return ResponseEntity.ok(listFileApproved);
    }

    @PostMapping
    public ResponseEntity<FileApproved> save(@RequestBody FileApproved fileApproved) {
        FileApproved fileApprovedSaved = fileApprovedService.save(fileApproved);
        return new ResponseEntity<>(fileApprovedSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileApproved> update(@PathVariable("id") UUID fileApprovedId, @RequestBody FileApproved fileApproved) {
        FileApproved fileApprovedUpdated = fileApprovedService.update(fileApprovedId, fileApproved);
        return ResponseEntity.ok(fileApprovedUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID fileApprovedId) {
        fileApprovedService.delete(fileApprovedId);
        return ResponseEntity.noContent().build();
    }
}