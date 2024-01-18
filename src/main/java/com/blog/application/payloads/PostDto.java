package com.blog.application.payloads;

import com.blog.application.entities.Category;
import com.blog.application.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private String title;

    private  String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

}
