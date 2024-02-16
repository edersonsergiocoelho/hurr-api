package br.com.escconsulting.dto.file.approved;

import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FileApprovedSearchDTO {

    private UUID approvedBy;

    private UUID reprovedBy;

    private FileTable fileTable;

    private FileType fileType;

    private Boolean enabled;

    private String filter;
}
