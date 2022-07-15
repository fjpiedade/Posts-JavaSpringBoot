package ao.phi.posts.model;

//import net.minidev.json.annotate.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_owner")
public class OwnerModel implements Serializable {
    //Model represent Owner of Comments about specific Post or Comments
    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long idowner;

    @Column(nullable = false)
    private String name;

    private String email;

    private LocalDateTime dateRegister;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private Set<PostModel> posts = new HashSet<>();

    public long getIdowner() {
        return idowner;
    }

    public void setIdowner(long idowner) {
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

    public Set<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostModel> posts) {
        this.posts = posts;
    }
}
