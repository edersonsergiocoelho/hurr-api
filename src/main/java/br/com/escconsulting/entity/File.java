package br.com.escconsulting.entity;

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
@EqualsAndHashCode(callSuper = false, of = "fileId")
@ToString
@Builder(toBuilder = true)
@Table(name = "file")
public class File extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -2073879407182934779L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "content_type", length = 50, nullable = false)
    private String contentType;

    @Column(name = "original_file_name", length = 1000, nullable = false)
    private String originalFileName;

    @Column(name = "data_as_byte_array", nullable = false)
    private byte[] dataAsByteArray;
}