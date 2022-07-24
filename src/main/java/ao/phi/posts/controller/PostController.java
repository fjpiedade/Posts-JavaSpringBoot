package ao.phi.posts.controller;

import ao.phi.posts.dto.PostDto;
import ao.phi.posts.model.PostModel;
import ao.phi.posts.repository.UserRepository;
import ao.phi.posts.repository.PostRepository;
import ao.phi.posts.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostModel>> getAllPosts() {
        List<PostModel> postModelList = postRepository.findAll();
        if (postModelList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (PostModel post : postModelList) {
                UUID id = post.getPostId();
                post.add(linkTo(methodOn(PostController.class).getOnePost(id)).withSelfRel());
            }
        }
        return new ResponseEntity<List<PostModel>>(postRepository.findAll(), HttpStatus.OK);
        //return new ResponseEntity<List<PostModel>>(postModelList,HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostModel> getOnePost(@PathVariable(value = "id") UUID id) {
        Optional<PostModel> postOne = postRepository.findById(id);
        if (postOne.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<PostModel>(postOne.get(),HttpStatus.OK);
        postOne.get().add(linkTo(methodOn(PostController.class).getAllPosts()).withRel("Post List"));
        return new ResponseEntity<PostModel>(postOne.get(), HttpStatus.OK);
    }

    //
//    @PostMapping("/posts")
//    public ResponseEntity<PostModel>savePost(@RequestBody @Valid PostModel post){
//        return new ResponseEntity<PostModel>(postRepository.save(post), HttpStatus.CREATED);
//    }
//
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") UUID id) {
        Optional<PostModel> postDelete = postRepository.findById(id);
        if (postDelete.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postRepository.delete(postDelete.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //
//    @PutMapping("/posts/{id}")
//    public ResponseEntity<PostModel> updatePost(@PathVariable(value="id") UUID id,
//    @RequestBody @Valid PostModel post){
//        Optional<PostModel> postUpdate = postRepository.findById(id);
//        if (postUpdate.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        post.setIdpost(postUpdate.get().getIdpost());
//        return new ResponseEntity<PostModel>(postRepository.save(post), HttpStatus.OK);
//    }
//
//
//
//    //using Service Module
//    final PostService postService;
//    public PostController(PostService postService){
//        this.postService = postService;
//    }
//
//    //Post with Service
    @PostMapping("/posts/v1")
    public ResponseEntity<Object> savePostV1(@RequestBody @Valid PostDto postDto) {
//        if (postService.existsByTitle(postDto.getTitle())){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Title is already Exist!");
//        }
        var postModel = new PostModel();
        BeanUtils.copyProperties(postDto, postModel);
        postModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postModel));
    }
//
//    //All Posts with Pageable
//    @GetMapping("/posts/v1")
//    public ResponseEntity<Page<PostModel>> getAllPostsV1(@PageableDefault(page = 0, size=10, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
//        return ResponseEntity.status(HttpStatus.OK).body(postService.findAll(pageable));
//    }
//

//    Post by Owner
//    @GetMapping("/posts/{id}/owner")
//    public List<PostModel> getPostByOwner(@PathVariable(value="id")UUID id){
//        List<PostModel> postbyOwner = postService.getPostByIdOwner(id);
//        if (postbyOwner.isEmpty()){
//            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return postService.getPostByIdOwner(id);
//        //return new ResponseEntity<PostModel>(postbyOwner.get(),HttpStatus.OK);
//        //postOne.get().add(linkTo(methodOn(PostController.class).getAllPosts()).withRel("Post List"));
//        //return new ResponseEntity<List<PostModel>>(postbyOwner, HttpStatus.OK);
//    }

    @GetMapping("/post")
    public List<PostModel> showPosts() {
        return postRepository.findAll();
    }

    @PostMapping("/post/owner")
    public PostModel savePost(@RequestBody PostModel post) {
        post.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return postRepository.save(post);
    }

    @PutMapping("post/{idpost}/owner/{idowner}")
    public PostModel updatePostWithOwner(@PathVariable UUID idpost, @PathVariable UUID idowner) {
        PostModel post = postRepository.findById(idpost).get();
        //UserModel user = ownerRepository.findById(idowner).get();
        //post.assignUser(user);
        return postRepository.save(post);
    }
}
