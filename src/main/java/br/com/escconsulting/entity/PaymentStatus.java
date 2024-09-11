package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_status")
public class PaymentStatus extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do status do pagamento.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "payment_status_id", updatable = false, nullable = false)
    private UUID paymentStatusId;

    /**
     * Nome do status do pagamento.
     */
    @Column(name = "payment_status_name", length = 100, nullable = false, unique = true)
    private String paymentStatusName;

    /**
     * Identificador do arquivo associado ao status do pagamento.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @PrePersist
    protected void prePersist() {
        if (this.getCreatedDate() == null) {
            this.setCreatedDate(Instant.now());
        }

        if (this.getEnabled() == null) {
            this.setEnabled(Boolean.TRUE);
        }
    }
}