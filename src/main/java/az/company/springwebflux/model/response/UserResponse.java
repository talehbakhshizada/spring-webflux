package az.company.springwebflux.model.response;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(Long id, String username, Integer age, String birthPlace, LocalDateTime createdAt, LocalDateTime updatedAt){

}
