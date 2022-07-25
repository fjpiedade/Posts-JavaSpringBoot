package ao.phi.posts.service;

import ao.phi.posts.model.TokenModel;
import ao.phi.posts.model.UserModel;
import ao.phi.posts.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserService userService;

    public TokenModel save(TokenModel token){
        token.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return tokenRepository.save(token);
    }


    public boolean existToken(String token){
        //System.out.println(token);
        if(tokenRepository.findByToken(token)==null){
            throw new IllegalStateException("Token does not Exist!");
        }
        return true;
    }

    public TokenModel getConfirmedByToken(String token){
        if (!existToken(token)){
            throw new IllegalStateException("Token does not Exist!");
        }
        //System.out.println(token);
        TokenModel tokenModel= tokenRepository.findByToken(token);
        //System.out.println(tokenModel.getExpiresAt());
        LocalDateTime expiredAt = tokenModel.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now(ZoneId.of("UTC")))){
            throw new IllegalStateException("Token expired!");
        }
        tokenModel.setConfirmedAt(LocalDateTime.now(ZoneId.of("UTC")));
        tokenRepository.save(tokenModel);
        return tokenModel;
    }

}
