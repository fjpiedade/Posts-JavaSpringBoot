package ao.phi.posts.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_comment")
public class CommentModel {
    //manager all comments - Entity represent
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idcomment;

    @Column(nullable = false)
    private String description;
    private LocalDateTime dateRegister;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idowner", referencedColumnName = "idowner")
    private OwnerModel owner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idpost", referencedColumnName = "idpost")
    private PostModel post;

    public UUID getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(UUID idcomment) {
        this.idcomment = idcomment;
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

    public OwnerModel getOwner() {
        return owner;
    }

    public void setOwner(OwnerModel owner) {
        this.owner = owner;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }
}
