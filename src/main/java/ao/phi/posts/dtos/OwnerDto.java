package ao.phi.posts.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class OwnerDto {
    @NotNull(message = "Name shouldn't be Null")
    @NotBlank(message = "Name shouldn't be Blank")
    private String name;
    @Email(message = "Email isn't valid")
    private String email;
    private LocalDateTime dateRegister;

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

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }
}
