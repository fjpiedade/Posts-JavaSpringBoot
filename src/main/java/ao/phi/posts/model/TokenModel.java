package ao.phi.posts.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_token")
public class TokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID tokenId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

    private LocalDateTime createdAt;
    private LocalDateTime confirmedAt;
    private LocalDateTime expiresAt;


}
