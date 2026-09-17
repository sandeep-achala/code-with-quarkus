package org.CommonResponse;

public class GetResponse {

    public String message;
    public Boolean status;

    public GetResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}