package site.sugarnest.backend.entities;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "producers")
@Data
public class ProducerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameProducer;

    @Column(columnDefinition = "mediumtext")
    private String information;

    @Column(columnDefinition = "mediumtext")
    private String logo;

    private String isActive;
}