package br.com.escconsulting.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private UUID fileId;
    private String contentType;
    private String originalFileName;
    private byte[] dataAsByteArray;
}