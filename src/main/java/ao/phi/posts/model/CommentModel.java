package ao.phi.posts.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comment")
public class CommentModel extends RepresentationModel<PostModel> implements Serializable {
    //manager all comments - Entity represent
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idcomment;

    @Column(nullable = false)
    private String description;
    private LocalDateTime createdAt;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "idowner", referencedColumnName = "idowner")
//    private OwnerModel owner;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private UserModel user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private PostModel post;

    public Long getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(Long idcomment) {
        this.idcomment = idcomment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void assignPost(PostModel post) {
        this.post=post;
    }
}
