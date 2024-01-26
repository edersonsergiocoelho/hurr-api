package br.com.escconsulting.entity;

import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "fileApprovedId")
@ToString
@Table(name = "file_approved")
public class FileApproved extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 5731193894818810497L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_approved_id")
    private UUID fileApprovedId;

    @Column(name = "file_id", nullable = false)
    private UUID fileId;

    @Column(name = "approved_by", nullable = false)
    private UUID approvedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_table", length = 100, nullable = false)
    private FileTable fileTable;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_type", length = 100, nullable = false)
    private FileType fileType;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "modified_by")
    private UUID modifiedBy;
}