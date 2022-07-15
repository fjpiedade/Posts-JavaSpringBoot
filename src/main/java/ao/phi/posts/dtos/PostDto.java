package ao.phi.posts.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

public class PostDto {
    @NotBlank
    @Size(max = 20)
    private String title;
    private String description;
    private String link;
    //private UUID id_owner;
    private LocalDateTime dateRegister;

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

//    public UUID getId_owner() {
//        return id_owner;
//    }
//
//    public void setId_owner(UUID id_owner) {
//        this.id_owner = id_owner;
//    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }
}
