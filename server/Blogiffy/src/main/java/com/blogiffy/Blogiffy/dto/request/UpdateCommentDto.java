package com.blogiffy.Blogiffy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class UpdateCommentDto {
    private String content;

}
