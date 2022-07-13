package ao.phi.posts.dtos;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class OwnerDto {
    @NotBlank
    private String name;
    private String email;
    private LocalDateTime dateRegister;

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
}
