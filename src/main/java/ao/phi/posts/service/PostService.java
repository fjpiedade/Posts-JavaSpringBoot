package ao.phi.posts.service;

import ao.phi.posts.exception.PostNotFoundException;
import ao.phi.posts.model.PostModel;
import ao.phi.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.UUID;

@Service
@RequestMapping(path = "api/v1/token")
public class PostService {

    //@Autowired
    //PostRepository postRepository;

    //alternative
    final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public PostModel save(PostModel postModel) {
        return postRepository.save(postModel);
    }

//    public boolean existsByTitle(String title) {
//        return postRepository.existsByTitle(title);
//    }

    public Page<PostModel> findAll(Pageable pageable) {
        return postRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    public PostModel getPostById(UUID id) throws PostNotFoundException {
        PostModel post = postRepository.findByPostId(id);
        if (post != null) {
            return post;
        } else {
            throw new PostNotFoundException("Post not founded with the id: " + id);
        }
    }
}
