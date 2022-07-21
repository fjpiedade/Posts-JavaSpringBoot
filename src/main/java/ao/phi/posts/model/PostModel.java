package ao.phi.posts.model;

//import net.minidev.json.annotate.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_post")
public class PostModel extends RepresentationModel<PostModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(nullable = false, unique = true, length = 50)
    private String title;
    private String description;
    private String link;
    private LocalDateTime createdAt;

    //@JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "userId")
    private UserModel user;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private Set<CommentModel> comment = new HashSet<>();

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Set<CommentModel> getComment() {
        return comment;
    }

    public void setComment(Set<CommentModel> comment) {
        this.comment = comment;
    }

    public void assignUser(UserModel user) {
        this.user=user;
    }
}
