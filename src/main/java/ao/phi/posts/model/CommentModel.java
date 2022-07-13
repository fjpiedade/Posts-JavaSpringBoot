package ao.phi.posts.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_comments")
public class CommentModel {
    //manager all comments - Entity represent
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_comment;
    @Column(nullable = false)
    private String description;
    private UUID id_owner;
    private UUID id_post;
    private LocalDateTime dateRegister;


    public UUID getId_comment() {
        return id_comment;
    }

    public void setId_comment(UUID id_comment) {
        this.id_comment = id_comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    public UUID getId_owner() {
        return id_owner;
    }

    public void setId_owner(UUID id_owner) {
        this.id_owner = id_owner;
    }

    public UUID getId_post() {
        return id_post;
    }

    public void setId_post(UUID id_post) {
        this.id_post = id_post;
    }
}
