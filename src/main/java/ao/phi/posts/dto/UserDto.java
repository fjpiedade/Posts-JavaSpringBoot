package ao.phi.posts.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UserDto {
    @NotNull(message = "Name shouldn't be Null")
    @NotBlank(message = "Name shouldn't be Blank")
    private String name;
    @Email(message = "Email isn't valid")
    private String email;
    private LocalDateTime createdAt;

    //Validation phone number
    //@Pattern(regexp = "^\\d{10}$")
    //private String phoneNumber;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
