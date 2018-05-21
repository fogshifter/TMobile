package com.tmobile.dto;

import java.util.Objects;

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

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusDTO statusDTO = (StatusDTO) o;
        return Objects.equals(status, statusDTO.status) &&
                Objects.equals(description, statusDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, description);
    }
}
