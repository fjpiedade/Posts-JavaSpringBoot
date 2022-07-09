package ao.phi.posts.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_comments")
public class CommentModel {
    //manager all comments - Entity represent
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_comment;
    @Column(nullable = false)
    private String description;
    private LocalDateTime createDate;
    private UUID id_post;

}
