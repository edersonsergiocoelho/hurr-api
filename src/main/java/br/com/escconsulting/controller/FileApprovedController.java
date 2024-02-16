package br.com.escconsulting.controller;

import br.com.escconsulting.dto.file.approved.FileApprovedSearchDTO;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.service.FileApprovedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file-approved")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileApprovedController {

    private final FileApprovedService fileApprovedService;

    @GetMapping("/{fileApprovedId}")
    public ResponseEntity<FileApproved> findById(@PathVariable("fileApprovedId") UUID fileApprovedId) {
        return fileApprovedService.findById(fileApprovedId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/fileId/{fileId}")
    public ResponseEntity<FileApproved> findByFileId(@PathVariable("fileId") UUID fileId) {
        return fileApprovedService.findByFileId(fileId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<FileApproved>> findAll() {
        List<FileApproved> listFileApproved = fileApprovedService.findAll();
        return ResponseEntity.ok(listFileApproved);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody FileApprovedSearchDTO fileApprovedSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<FileApproved> fileApproveds = fileApprovedService.searchPage(fileApprovedSearchDTO, pageable);

        return ResponseEntity.ok(fileApproveds);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody FileApproved fileApproved) {
        return fileApprovedService.save(fileApproved)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save file approved."));
    }

    @PutMapping("/{fileApprovedId}")
    public ResponseEntity<?> update(@PathVariable("fileApprovedId") UUID fileApprovedId,
                                    @RequestBody FileApproved fileApproved) {
        return fileApprovedService.update(fileApprovedId, fileApproved)
                .map(updatedFileApproved -> ResponseEntity.ok(updatedFileApproved))
                .orElseThrow(() -> new IllegalStateException("Failed to update file approved."));
    }

    @DeleteMapping("/{fileApprovedId}")
    public ResponseEntity<?> delete(@PathVariable("fileApprovedId") UUID fileApprovedId) {
        fileApprovedService.delete(fileApprovedId);
        return ResponseEntity.noContent().build();
    }
}