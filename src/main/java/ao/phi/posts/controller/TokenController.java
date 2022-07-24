package ao.phi.posts.controller;

import ao.phi.posts.model.TokenModel;
import ao.phi.posts.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
public class TokenController {
    TokenService tokenService;

    @PostMapping("/register")
    public TokenModel register(@RequestBody TokenModel token){
        return tokenService.save(token);
    }
}
