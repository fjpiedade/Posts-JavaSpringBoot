package ao.phi.posts.controller;

import ao.phi.posts.model.CommentModel;
import ao.phi.posts.model.PostModel;
import ao.phi.posts.repository.CommentRepository;
import ao.phi.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/comment")
    public ResponseEntity<List<CommentModel>> showComments(){
        List<CommentModel> comment = commentRepository.findAll();
        if(comment.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<CommentModel>>(comment, HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentModel> createComment(@RequestBody CommentModel comment){
        return new ResponseEntity<CommentModel>(commentRepository.save(comment),HttpStatus.OK);
    }

    @PutMapping("comment/{idcomment}/post/{idpost}")
    public CommentModel updateCommentWithPost(@PathVariable Long idcomment, @PathVariable Long idpost){
        CommentModel comment = commentRepository.findById(idcomment).get();
        PostModel post = postRepository.findById(idpost).get();
        comment.assignPost(post);
        return commentRepository.save(comment);
    }
}
