package ao.phi.posts.controller;

import ao.phi.posts.model.RoleModel;
import ao.phi.posts.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping(path = "api/v1")
public class RoleControlle {
    @Autowired
    RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<RoleModel> saveRole(@RequestBody RoleModel role){
        role.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return new ResponseEntity<RoleModel>(roleService.save(role), HttpStatus.CREATED);
    }
}
