package ao.phi.posts.model;

//import net.minidev.json.annotate.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_post")
public class PostModel extends RepresentationModel<PostModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idpost;

    @Column(nullable = false, unique = true, length = 50)
    private String title;
    private String description;
    private String link;
    private LocalDateTime registerDate;

    //@JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "idowner")
    private OwnerModel owner;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private Set<CommentModel> comment = new HashSet<>();

    public Long getIdpost() {
        return idpost;
    }

    public void setIdpost(Long idpost) {
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

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public OwnerModel getOwner() {
        return owner;
    }

    public void setOwner(OwnerModel owner) {
        this.owner = owner;
    }

    public Set<CommentModel> getComment() {
        return comment;
    }

    public void setComment(Set<CommentModel> comment) {
        this.comment = comment;
    }

    public void assignOwner(OwnerModel owner) {
        this.owner=owner;
    }
}
