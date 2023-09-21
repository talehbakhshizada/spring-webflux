package az.company.springwebflux.mapper;

import az.company.springwebflux.dao.entity.UserEntity;
import az.company.springwebflux.model.request.SaveUserRequest;
import az.company.springwebflux.model.request.UpdateUserRequest;
import az.company.springwebflux.model.response.UserResponse;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;


@UtilityClass
public class UserMapper {

    public static UserResponse mapEntityToResponse(UserEntity entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .age(entity.getAge())
                .birthPlace(entity.getBirthPlace())
                .build();
    }

    public static UserEntity mapRequestToEntity(SaveUserRequest request){
        return UserEntity.builder()
                .username(request.username())
                .age(request.age())
                .birthPlace(request.birthPlace())
                .build();
    }

    public static void updateUser(UserEntity entity , UpdateUserRequest request){
        entity.setUsername(request.username());
        entity.setAge(request.age());
        entity.setBirthPlace(request.birthPlace());
    }
}
