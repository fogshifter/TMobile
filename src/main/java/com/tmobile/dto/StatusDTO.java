package com.tmobile.dto;

public class StatusDTO {
    public enum StatusCode {
        SUCCESS,
        ERROR
    }

    private String status = "success";
    private String description;

    public StatusDTO(){}

    public StatusDTO(StatusCode statusCode, String description) {
        setStatus(statusCode);
        setDescription(description);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(StatusCode statusCode) {
        if(statusCode == StatusCode.ERROR) {
            status = "error";
        }
        else {
            status = "success";
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
