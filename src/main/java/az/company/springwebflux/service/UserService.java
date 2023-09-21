package az.company.springwebflux.service;

import az.company.springwebflux.dao.repository.UserRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

    UserRepository userRepository;
    public Mono<UserResponse> getUser(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::mapEntityToResponse)
                .doOnSuccess(userResponse -> {
                    log.info("User successfully retrieved, USER: {}", userResponse);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

    public Flux<UserResponse> getUsers() {
        return userRepository.findAll().map(UserMapper::mapEntityToResponse);
    }

    public Mono<Void> saveUser(SaveUserRequest user){
        return userRepository.save(mapRequestToEntity(user)).then();
    }

    public Mono<Void> updateUser(Long id, UpdateUserRequest request) {
        var user = userRepository.findById(id).switchIfEmpty(Mono.error(new RuntimeException("User not found")));
       // user.map(UserMapper.updateUser(user,request));
       // UserMapper.updateUser(user, request);
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

}
