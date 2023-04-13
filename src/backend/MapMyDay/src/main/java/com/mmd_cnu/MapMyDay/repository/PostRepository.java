package com.mmd_cnu.MapMyDay.repository;

import com.mmd_cnu.MapMyDay.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
//    Post save(Post post);
//    List<Post> findAll();
//    Optional<Post> findById(Integer postId);
//    void delete(Post post);
}
