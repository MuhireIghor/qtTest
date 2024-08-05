package com.blogiffy.Blogiffy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nonnull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBlogDto {
    private String content;
    private String title;

}
