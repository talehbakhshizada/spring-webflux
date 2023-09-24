package az.company.springwebflux.service;

import az.company.springwebflux.clent.StudentWebClient;
import az.company.springwebflux.dao.repository.UserRepository;
import az.company.springwebflux.exception.NotFoundException;
import az.company.springwebflux.mapper.UserMapper;
import az.company.springwebflux.model.request.SaveUserRequest;
import az.company.springwebflux.model.request.UpdateUserRequest;
import az.company.springwebflux.model.response.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static az.company.springwebflux.mapper.UserMapper.mapRequestToEntity;
import static az.company.springwebflux.model.constant.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

    UserRepository userRepository;
    StudentWebClient studentWebClient;

    public Mono<UserResponse> getUser(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::mapEntityToResponse)
                .doOnSuccess(userResponse -> {
                    log.info("User successfully retrieved, USER: {}", userResponse);
                })
                .switchIfEmpty(Mono.error(() -> new NotFoundException(String.valueOf(USER_NOT_FOUND),
                        String.format(USER_NOT_FOUND.getMessage(), id))));
    }

    public Flux<UserResponse> getUsers() {
        //var students = studentWebClient.retrieveStudents();
        return userRepository.findAll().map(UserMapper::mapEntityToResponse);
    }

    public Mono<Void> saveUser(SaveUserRequest user) {
        return userRepository.save(mapRequestToEntity(user)).then();
    }

    public Mono<Void> updateUser(Long id, UpdateUserRequest request) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new NotFoundException(String.valueOf(USER_NOT_FOUND),
                        String.format(USER_NOT_FOUND.getMessage(), id))))
                .flatMap(userEntity -> {
                    UserMapper.updateUser(userEntity, request);
                    return userRepository.save(userEntity);
                })
                .then();
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id).switchIfEmpty(
                Mono.error(() -> new NotFoundException(String.valueOf(USER_NOT_FOUND),
                        String.format(USER_NOT_FOUND.getMessage(), id))));
    }

}
