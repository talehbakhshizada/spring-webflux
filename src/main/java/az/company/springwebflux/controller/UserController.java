package az.company.springwebflux.controller;

import az.company.springwebflux.model.request.SaveUserRequest;
import az.company.springwebflux.model.request.UpdateUserRequest;
import az.company.springwebflux.model.response.UserResponse;
import az.company.springwebflux.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserService userService;

    @GetMapping("/{id}")
    public Mono<UserResponse> getUser(@PathVariable Long id){
        return  userService.getUser(id);
    }

    @GetMapping()
    public Flux<UserResponse> getUsers(){
        return  userService.getUsers();
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public Mono<Void> saveUser(@RequestBody SaveUserRequest userRequest){
        return  userService.saveUser(userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteUser(@PathVariable Long id, @RequestBody UpdateUserRequest request){
        return  userService.updateUser(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteUser(@PathVariable Long id){
        return  userService.deleteUser(id);
    }
}
