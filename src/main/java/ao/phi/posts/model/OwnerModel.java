package ao.phi.posts.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_owner")
public class OwnerModel {
    //Model represent Owner of Comments about specific Post or Comments

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID id_Owner;
    @Column(nullable = false)
    private String name;
    private String email;
    private LocalDateTime dateRegister;

    public UUID getId_Owner() {
        return id_Owner;
    }

    public void setId_Owner(UUID id_Owner) {
        this.id_Owner = id_Owner;
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
}
