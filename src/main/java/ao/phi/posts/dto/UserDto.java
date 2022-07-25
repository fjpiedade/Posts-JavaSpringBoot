package ao.phi.posts.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class UserDto {
    @NotNull(message = "Name shouldn't be Null")
    @NotBlank(message = "Name shouldn't be Blank")
    private String name;
    @Email(message = "Email isn't valid")
    private String email;
    //Validation phone number
    //@Pattern(regexp = "^\\d{10}$")

    //@NotNull(message = "Name shouldn't be Null")
    //@NotBlank(message = "Name shouldn't be Blank")
    //private String username;

    private String phone;
    private String password;
    private String avatar;
    private boolean enabled = false;
    private boolean locked = false;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    //public String getUsername() {
        //return username;
    //}

    //public void setUsername(String username) {
        //this.username = username;
    //}
}
