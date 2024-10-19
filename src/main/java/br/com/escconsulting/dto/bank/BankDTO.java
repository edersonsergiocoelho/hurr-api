package br.com.escconsulting.dto.bank;

import br.com.escconsulting.dto.file.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private UUID bankId;
    private String bankCode;
    private String bankName;
    private FileDTO file;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}