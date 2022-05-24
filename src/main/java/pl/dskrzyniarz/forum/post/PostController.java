package pl.dskrzyniarz.forum.post;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostRepository repository;
    private final PostModelAssembler assembler;

    public PostController(PostRepository repository, PostModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/posts/{id}")
    EntityModel<Post> one(@PathVariable Long id){
        Post post = repository.findById(id)
                .orElseThrow(()-> new PostNotFoundException(id));
        return EntityModel.of(post);
    }

    @PostMapping("/posts")
    ResponseEntity<?> newPost(@RequestBody Post newPost){
        EntityModel<Post> entityModel = assembler.toModel(repository.save(newPost));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/posts/{id}")
    ResponseEntity<?> replacePost(@RequestBody Post newPost, @PathVariable Long id){
        Post updatedPost = repository.findById(id)
                .map(post -> {
                    post.setBody(newPost.getBody());
                    return repository.save(post);
                })
                .orElseGet(() -> {
                    newPost.setId(id);
                    return repository.save(newPost);
                });
        EntityModel<Post> entityModel = assembler.toModel(updatedPost);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/posts/{id}")
    ResponseEntity<?> deletePost(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
