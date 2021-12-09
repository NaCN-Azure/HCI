package com.example.backend.Model;

import com.example.backend.Exception.ResourceNotFoundException;
import lombok.Data;

@Data
public class ResponseVO {

    private ResponseVO(){};

    private int code;

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
     * @param content
     * @return
     */
    public static ResponseVO buildOK(Object content){
        ResponseVO response = new ResponseVO();
        response.setContent(content);
        response.setCode(0);
        return response;
    }

    /**
     * OK 200
     * @param content
     * @param code
     * @return
     */
    public static ResponseVO buildOK(Object content,int code) {
        ResponseVO response = new ResponseVO();
        response.setContent(content);
        response.setCode(code);
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
