package ao.phi.posts.controller;

import ao.phi.posts.model.CommentModel;
import ao.phi.posts.model.PostModel;
import ao.phi.posts.repository.CommentRepository;
import ao.phi.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/comment")
    public Page<CommentModel> showComments(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        List<CommentModel> commentModelList = commentRepository.findAll();
        if (commentModelList.isEmpty()) {
            return commentRepository.findAll(
                    PageRequest.of(
                            page.orElse(0),
                            2,
                            Sort.Direction.ASC, sortBy.orElse("idcomment")
                    )
            );
        } else {
            for (CommentModel comment : commentModelList) {
                long id = comment.getIdcomment();
                comment.add(linkTo(methodOn(CommentController.class).getOneComment(id)).withSelfRel());
            }
        }
        return commentRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        2,
                        Sort.Direction.ASC, sortBy.orElse("idcomment")
                )
        );
    }

    public ResponseEntity<List<CommentModel>> getAllComments() {
        List<CommentModel> commentModelList = commentRepository.findAll();
        if (commentModelList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (CommentModel comment : commentModelList) {
                long id = comment.getIdcomment();
                comment.add(linkTo(methodOn(CommentController.class).getOneComment(id)).withSelfRel());
            }
        }
        return new ResponseEntity<List<CommentModel>>(commentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentModel> getOneComment(@PathVariable(value = "id") long id) {
        Optional<CommentModel> commentModel = commentRepository.findById(id);
        if (commentModel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentModel.get().add(linkTo(methodOn(CommentController.class).getAllComments()).withRel("Comment List"));
        return new ResponseEntity<CommentModel>(commentModel.get(), HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentModel> createComment(@RequestBody CommentModel comment) {
        comment.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return new ResponseEntity<CommentModel>(commentRepository.save(comment), HttpStatus.OK);
    }

    @PutMapping("comment/{idcomment}/post/{idpost}")
    public CommentModel updateCommentWithPost(@PathVariable Long idcomment, @PathVariable Long idpost) {
        CommentModel comment = commentRepository.findById(idcomment).get();
        PostModel post = postRepository.findById(idpost).get();
        comment.assignPost(post);
        return commentRepository.save(comment);
    }
}
