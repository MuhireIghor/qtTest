package com.blogiffy.Blogiffy.utils;

import com.blogiffy.Blogiffy.models.Blog;
import com.blogiffy.Blogiffy.models.Comment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BlogSerializer extends JsonSerializer<Blog> {

    @Override
    public void serialize(Blog blog, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", blog.getId().toString());
        gen.writeStringField("title", blog.getTitle());
        gen.writeStringField("content", blog.getContent());

        // Serialize comments
        gen.writeArrayFieldStart("comments");
        for (Comment
                comment : blog.getComments()) {
            gen.writeStartObject();
            gen.writeStringField("id", comment.getId().toString());
            gen.writeStringField("content", comment.getContent());
            gen.writeStringField("authorId", comment.getAuthor().getId().toString());
            gen.writeStringField("blogId", comment.getBlog().getId().toString());
            // Only serialize author ID
            gen.writeEndObject();
        }
        gen.writeEndArray();

        // Serialize author ID only
        gen.writeStringField("authorId", blog.getAuthor().getId().toString());

        gen.writeEndObject();
    }
}