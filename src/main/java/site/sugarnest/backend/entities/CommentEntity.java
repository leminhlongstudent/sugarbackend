package site.sugarnest.backend.entities;

import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comments")
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private AccountEntity customer;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "id_parent")
    private CommentEntity parentCommentEntity;

    @Column(columnDefinition = "TIMESTAMP")
    private Date createAt;

    @Column(columnDefinition = "TIMESTAMP")
    private Date updateAt;

    @Column(columnDefinition = "TIMESTAMP")
    private Date deleteAt;

    private String status;

    private Integer level;

    private String nameAccount;
}