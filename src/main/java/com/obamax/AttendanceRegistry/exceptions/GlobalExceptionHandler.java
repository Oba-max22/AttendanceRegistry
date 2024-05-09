package com.obamax.AttendanceRegistry.exceptions;

import com.obamax.AttendanceRegistry.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public BaseResponse<String> handleNotFoundException(NotFoundException ex){
        return  new BaseResponse<>(ex.getMessage(), false, null);
    }

    @ExceptionHandler(IllegalActionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse<String> handleValidationException(IllegalActionException ex){
        return  new BaseResponse<>(ex.getMessage(), false,null);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse<String> handleBadRequestException(BadRequestException ex){
        return  new BaseResponse<>(ex.getMessage(), false,null);
    }
}
