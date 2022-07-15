package ao.phi.posts.controller;

import ao.phi.posts.model.OwnerModel;
import ao.phi.posts.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
public class OwnerController {
    @Autowired
    OwnerRepository ownerRepository;

    @GetMapping("/owner")
    public List<OwnerModel> showOwner(){
        //List<OwnerModel> owners = ownerRepository.findAll();
        //if(owners.isEmpty()){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //}
        //return new ResponseEntity<List<OwnerModel>>(ownerRepository.findAll(), HttpStatus.OK);
        return ownerRepository.findAll();
   }

    @PostMapping("/owner")
    public OwnerModel saveOwner(@RequestBody OwnerModel owner){
        owner.setDateRegister(LocalDateTime.now(ZoneId.of("UTC")));
        //return new List<OwnerModel>(ownerRepository.save(owner), HttpStatus.CREATED) {
        return ownerRepository.save(owner);
    }
}
