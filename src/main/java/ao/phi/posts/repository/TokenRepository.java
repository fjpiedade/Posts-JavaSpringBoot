package ao.phi.posts.repository;

import ao.phi.posts.model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<TokenModel, UUID> {
    TokenModel findByToken(String token);
}
