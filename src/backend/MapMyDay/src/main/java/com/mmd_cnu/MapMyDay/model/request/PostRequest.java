package com.mmd_cnu.MapMyDay.model.request;


import com.mmd_cnu.MapMyDay.entity.Post;
import com.mmd_cnu.MapMyDay.model.type.Tag;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class PostRequest {
    @Valid
    private String title;
    private String contents;

    private Tag tag;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .contents(contents)
                .tag(tag)
                .build();
    }
}
