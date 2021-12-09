package com.example.backend.Model;

import com.example.backend.Exception.ResourceNotFoundException;
import lombok.Data;

@Data
public class ResponseVO {

    private ResponseVO(){};

    private Integer code;

    private String message;

    private Object content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    /**
     * OK 200
     * @param message
     * @return
     */
    public static ResponseVO buildOK(String message){
        ResponseVO response = new ResponseVO();
        response.setMessage(message);
        response.setCode(0);
        return response;
    }

    /**
     * OK 200
     * @param message
     * @param code
     * @return
     */
    public static ResponseVO buildOK(String message, Integer code) {
        ResponseVO response = new ResponseVO();
        response.setMessage(message);
        response.setCode(code);
        return response;
    }

    /**
     * OK 200
     * @param message
     * @param content
     * @return
     */
    public static ResponseVO buildOK(String message, Object content) {
        ResponseVO response = new ResponseVO();
        response.setMessage(message);
        response.setContent(content);
        response.setCode(0);
        return response;
    }

    /**
     * Not Found 404
     * @param message
     */
    public static void buildNotFound(String message) throws ResourceNotFoundException {
        throw new ResourceNotFoundException(message);
    }

    public static ResponseVO buildFailure(String message){
        ResponseVO response = new ResponseVO();
        response.setCode(-1);
        response.setContent(message);
        return response;
    }


}
