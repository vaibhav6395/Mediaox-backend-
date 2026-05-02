package com.mediabox.mediabox.Resposes;

public class AuthRespons {
    private String token;
    private String message;

    public AuthRespons(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public AuthRespons() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
