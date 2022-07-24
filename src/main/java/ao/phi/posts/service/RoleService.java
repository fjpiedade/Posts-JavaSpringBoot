package ao.phi.posts.service;

import ao.phi.posts.model.RoleModel;
import ao.phi.posts.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public RoleModel save(RoleModel role){
        roleRepository.save(role);
        return role;
    }
}
