package ao.phi.posts.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "tb_posts")
public class PostModel extends RepresentationModel<PostModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idpost;
    @Column(nullable = false, unique = true, length = 50)
    private String title;
    private String description;
    private String link;
    private UUID id_owner;
    private LocalDateTime registerDate;

    public UUID getIdpost() {
        return idpost;
    }

    public void setIdpost(UUID idpost) {
        this.idpost = idpost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UUID getId_owner() {
        return id_owner;
    }

    public void setId_owner(UUID id_owner) {
        this.id_owner = id_owner;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
}
