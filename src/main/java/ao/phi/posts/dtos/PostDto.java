package ao.phi.posts.dtos;

import ao.phi.posts.model.OwnerModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class PostDto {
    @NotNull(message = "Title shouldn't be null")
    @NotBlank(message = "Title shouldn't be Blank")
    @Size(max = 50, message = "Title shouldn't has more than 50 letters")
    private String title;
    private String description;
    private String link;
    private LocalDateTime registerDate;
    private OwnerModel owner;

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
}
