package ao.phi.posts.repository;

import ao.phi.posts.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Long> {
}
