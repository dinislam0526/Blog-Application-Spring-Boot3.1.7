package com.blog.application.payloads;

import com.blog.application.entities.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private int id;

    private String content;


}
