package az.company.springwebflux.model.request;

public record UpdateUserRequest(String username, Integer age ,String birthPlace) {
}
