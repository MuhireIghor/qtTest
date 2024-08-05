package com.blogiffy.Blogiffy.exceptions;

import com.blogiffy.Blogiffy.dto.response.ErrorResponse;
import com.blogiffy.Blogiffy.dto.response.Response;
import com.blogiffy.Blogiffy.enums.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }

    public ResponseEntity<Response> getResponse(){
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse().setMessage("Failed to get a resource").setDetails(details);
        Response<ErrorResponse> response = new Response<>();
        response.setType(ResponseType.RESOURCE_NOT_FOUND);
        response.setPayload(errorResponse);
        return new ResponseEntity<Response>(response , HttpStatus.NOT_FOUND);
    }
}
