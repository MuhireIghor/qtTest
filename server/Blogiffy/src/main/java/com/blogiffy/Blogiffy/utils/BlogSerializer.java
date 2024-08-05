package com.blogiffy.Blogiffy.utils;

import com.blogiffy.Blogiffy.models.Blog;
import com.blogiffy.Blogiffy.models.Comment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

// Custom serializer for the Blog model
public class BlogSerializer extends JsonSerializer<Blog> {

    @Override
    public void serialize(Blog blog, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject(); // Start the JSON object

        // Serialize basic blog fields
        gen.writeStringField("id", blog.getId().toString());
        gen.writeStringField("title", blog.getTitle());
        gen.writeStringField("content", blog.getContent());

        // Serialize comments array
        gen.writeArrayFieldStart("comments");
        for (Comment comment : blog.getComments()) {
            gen.writeStartObject(); // Start the JSON object for each comment

            // Serialize basic comment fields
            gen.writeStringField("id", comment.getId().toString());
            gen.writeStringField("content", comment.getContent());
            gen.writeStringField("authorId", comment.getAuthor().getId().toString());
            gen.writeStringField("authorName", comment.getAuthor().getFirstName() + " " + comment.getAuthor().getLastName());
            gen.writeStringField("createdAt", comment.getCreatedAt().toString());
            gen.writeStringField("blogId", comment.getBlog().getId().toString());

            gen.writeEndObject(); // End the JSON object for each comment
        }
        gen.writeEndArray(); // End the comments array

        // Serialize author information for the blog
        gen.writeStringField("authorId", blog.getAuthor().getId().toString());
        gen.writeStringField("authorFirstName", blog.getAuthor().getFirstName());
        gen.writeStringField("authorLastName", blog.getAuthor().getLastName());
        gen.writeStringField("createdAt", blog.getCreatedAt().toString());

        gen.writeEndObject(); // End the JSON object for the blog
    }
}


