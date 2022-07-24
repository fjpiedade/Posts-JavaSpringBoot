package ao.phi.posts.repository;

import ao.phi.posts.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostModel, UUID> {
    PostModel findByPostId(UUID id);
    //boolean existsByTitle(String title);
    //List<PostModel> findByIdOwner(UUID uuid);
}
