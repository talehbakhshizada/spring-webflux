package az.company.springwebflux.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class StudentResponse {
    Long id;
    String name;
    String surname;
    String email;
    String phoneNumber;
}
