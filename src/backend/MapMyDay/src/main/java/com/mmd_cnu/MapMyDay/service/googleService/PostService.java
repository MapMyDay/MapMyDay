package com.mmd_cnu.MapMyDay.service.googleService;

import com.mmd_cnu.MapMyDay.entity.Post;
import com.mmd_cnu.MapMyDay.model.request.PostRequest;
import com.mmd_cnu.MapMyDay.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(PostRequest postRequest) {
        return postRepository.save(postRequest.toEntity());
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPost(Integer postId) {
        return postRepository.findById(postId);
    }

    public Optional<Post> updatePost(Integer postId, PostRequest postRequest) {
        return postRepository.findById(postId)
                .map(post -> {
                    post.setTitle(postRequest.getTitle());
                    post.setContents(postRequest.getContents());
                    post.setTag(postRequest.getTag());
                    return postRepository.save(post);
                });
    }

    public void deletePost(Integer postId) {
        postRepository.findById(postId)
                .ifPresent(postRepository::delete);
    }
}
