package es.gusguma.template.microservice.user.controller;

import es.gusguma.template.microservice.user.exceptions.BadRequestException;

public class UserPatchDto {

    private String path;

    private String newValue;

    public UserPatchDto() {
        // empty for framework
    }

    public UserPatchDto(String path, String newValue) {
        this.path = path;
        this.newValue = newValue;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void validate() {
        if (this.path == null || this.path.isEmpty()) {
            throw new BadRequestException("Incomplete UserPatchDto");
        }
    }

    @Override
    public String toString() {
        return "UserPatchDto{" +
                "path='" + path + '\'' +
                ", newValue='" + newValue + '\'' +
                '}';
    }

}
