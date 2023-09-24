package az.company.springwebflux.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    UNEXPECTED_ERROR("Something went wrong "),

    USER_NOT_FOUND("User not found with ID: %s ");

    private final String message;
}