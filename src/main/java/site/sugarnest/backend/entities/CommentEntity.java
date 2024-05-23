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

    @Column(nullable = false, columnDefinition = "longtext")
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

    @Column(columnDefinition = "datetime default current_timestamp")
    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String status;

    private Integer level;

    private String nameAccount;
}