package ao.phi.posts.service;

import ao.phi.posts.model.PostModel;
import ao.phi.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    //@Autowired
    //PostRepository postRepository;

    //alternate
    /*final PostRepository postRepository;
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Transactional
    public PostModel save(PostModel postModel) {
        return postRepository.save(postModel);
    }

    public boolean existsByTitle(String title) {
        return postRepository.existsByTitle(title);
    }

    public Page<PostModel> findAll(Pageable pageable) {
        return postRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

//    public List<PostModel> getPostByIdOwner(UUID id_owner){
//        return postRepository.findByIdOwner(id_owner);
//    }


     */
}
