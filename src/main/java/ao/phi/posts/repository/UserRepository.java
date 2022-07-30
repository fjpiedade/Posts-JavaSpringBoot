package ao.phi.posts.repository;

import ao.phi.posts.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String email);
    UserModel findByUserId(UUID id);
    Optional<UserModel> findByName(String name);
}
