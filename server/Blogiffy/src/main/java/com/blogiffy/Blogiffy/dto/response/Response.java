package com.blogiffy.Blogiffy.dto.response;


import com.blogiffy.Blogiffy.enums.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {
    private ResponseType type;
    private T payload;
}