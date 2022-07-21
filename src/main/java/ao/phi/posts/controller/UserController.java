package ao.phi.posts.controller;

import ao.phi.posts.model.UserModel;
import ao.phi.posts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/user")
    public List<UserModel> showUser(){
        //List<OwnerModel> owners = ownerRepository.findAll();
        //if(owners.isEmpty()){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //}
        //return new ResponseEntity<List<OwnerModel>>(ownerRepository.findAll(), HttpStatus.OK);
        return userRepository.findAll();
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/user")
    public UserModel saveUser(@RequestBody UserModel user){
        user.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        //return new List<OwnerModel>(ownerRepository.save(owner), HttpStatus.CREATED) {
        return userRepository.save(user);
    }
}
