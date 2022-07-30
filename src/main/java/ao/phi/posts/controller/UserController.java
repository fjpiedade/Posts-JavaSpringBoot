package ao.phi.posts.controller;

import ao.phi.posts.dto.PostDto;
import ao.phi.posts.dto.UserDto;
import ao.phi.posts.model.PostModel;
import ao.phi.posts.model.TokenModel;
import ao.phi.posts.model.UserModel;
import ao.phi.posts.repository.EmailSender;
import ao.phi.posts.repository.UserRepository;
import ao.phi.posts.service.TokenService;
import ao.phi.posts.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    private TokenService tokenService;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EmailSender emailSender;

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/user/v1")
    public List<UserModel> showUser() {
        //List<OwnerModel> owners = ownerRepository.findAll();
        //if(owners.isEmpty()){
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //}
        //return new ResponseEntity<List<OwnerModel>>(ownerRepository.findAll(), HttpStatus.OK);
        return userRepository.findAll();
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/user")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDto userDTO) {
        var user = new UserModel();
        BeanUtils.copyProperties(userDTO, user);
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        //return userRepository.save(user);
        return new ResponseEntity<UserModel>(userRepository.save(user), HttpStatus.CREATED);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<UserModel> signUpUser(@RequestBody @Valid UserDto userDTO) {
        var user = new UserModel();
        BeanUtils.copyProperties(userDTO, user);
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        //return userService.signUpUser(user);
        var newUser = new UserModel();
        newUser = userService.signUpUser(user);
        //Create the token
        TokenModel tokenModel = new TokenModel(
                UUID.randomUUID().toString(),
                newUser,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1)
        );
        tokenService.save(tokenModel);

        //sending email
        String link = "http://localhost:9000/api/v1/user/confirm?token=" + tokenModel.getToken();
        emailSender.send(
                newUser.getEmail(),
                userService.buildEmail(newUser.getName(), link));


        return new ResponseEntity<UserModel>(newUser, HttpStatus.CREATED);
    }

    //Confirm Registration
    @GetMapping(path = "/user/confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {
        if (!tokenService.existToken(token)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TokenModel tokenModel = tokenService.getConfirmedByToken(token);
        UserModel userModel = userService.enableUserByTokenConfirmed(tokenModel.getUser().getUserId());
        return new ResponseEntity<String>("Confirmed", HttpStatus.OK);
    }

    @GetMapping("/users")
    public String getAll(){
        return "Ok";
    }
}
