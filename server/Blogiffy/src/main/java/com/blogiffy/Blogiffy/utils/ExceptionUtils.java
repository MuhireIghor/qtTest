package com.blogiffy.Blogiffy.utils;

import com.blogiffy.Blogiffy.dto.response.ApiResponse;
import com.blogiffy.Blogiffy.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionUtils {

    // Method to handle exceptions in the controller and return appropriate HTTP responses
    public static <T> ResponseEntity<ApiResponse> handleControllerExceptions(Exception e) {
        System.out.println("Exception caught in controller:");

        // Handle NotFoundException
        if (e instanceof NotFoundException) {
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.NOT_FOUND);
        }
        // Handle InvalidUUIDException
        else if (e instanceof InvalidUUIDException) {
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.BAD_REQUEST);
        }
        // Handle ResourceNotFoundException
        else if (e instanceof ResourceNotFoundException) {
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.NOT_FOUND);
        }
        // Handle InternalServerErrorException
        else if (e instanceof InternalServerErrorException) {
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Handle BadRequestAlertException
        else if (e instanceof BadRequestAlertException) {
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.BAD_REQUEST);
        }
        // Handle other exceptions
        else {
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method to handle exceptions in the service layer and rethrow them as needed
    public static <T> void handleServiceExceptions(Exception e) throws ResourceNotFoundException {
        System.out.println("Exception caught in service:");

        // Handle NotFoundException
        if (e instanceof NotFoundException) {
            throw new NotFoundException(e.getMessage());
        }
        // Handle ResourceNotFoundException
        else if (e instanceof ResourceNotFoundException) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        // Handle InternalServerErrorException
        else if (e instanceof InternalServerErrorException) {
            throw new InternalServerErrorException(e.getMessage());
        }
        // Handle BadRequestAlertException
        else if (e instanceof BadRequestAlertException) {
            throw new BadRequestAlertException(e.getMessage());
        }
        // Uncomment the block below to rethrow other exceptions as needed
//        else {
//            throw new RuntimeException("Failed!! Something went wrong", e);
//        }
    }
}
