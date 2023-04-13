package com.mmd_cnu.MapMyDay.controller;

import com.mmd_cnu.MapMyDay.entity.Post;
import com.mmd_cnu.MapMyDay.model.request.PostRequest;
import com.mmd_cnu.MapMyDay.service.googleService.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postService.createPost(postRequest));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(postService.getPost(postId).orElse(null));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId")Integer postId,
                                           @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postService.updatePost(postId, postRequest).orElse(null));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Integer postId) {
        postService.deletePost(postId);

        return ResponseEntity.noContent().build();
    }
}
