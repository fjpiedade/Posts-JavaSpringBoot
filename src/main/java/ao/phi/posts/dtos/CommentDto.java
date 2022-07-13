package ao.phi.posts.dtos;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

public class CommentDto {
    @NotBlank
    private String description;
    @NotBlank
    private UUID id_owner;
    @NotBlank
    private UUID id_post;
    private LocalDateTime dateRegister;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }
}
