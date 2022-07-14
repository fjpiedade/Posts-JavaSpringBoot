package ao.phi.posts.model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_owner")
public class OwnerModel {
    //Model represent Owner of Comments about specific Post or Comments
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID idowner;

    @Column(nullable = false)
    private String name;

    private String email;

    private LocalDateTime dateRegister;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private Set<PostModel> post = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private Set<CommentModel> comment = new HashSet<>();

    public UUID getIdowner() {
        return idowner;
    }

    public void setIdowner(UUID idowner) {
        this.idowner = idowner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Set<PostModel> getPost() {
        return post;
    }

    public void setPost(Set<PostModel> post) {
        this.post = post;
    }

    public Set<CommentModel> getComment() {
        return comment;
    }

    public void setComment(Set<CommentModel> comment) {
        this.comment = comment;
    }
}
