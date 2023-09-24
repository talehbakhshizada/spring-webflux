package az.company.springwebflux.clent;

import az.company.springwebflux.model.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Component
@RequiredArgsConstructor
@Slf4j
public class StudentWebClient {

    private static final String URL = "/students/{id}";
    private final WebClient webClient;

    public Mono<StudentResponse> retrieveStudent(Long id) {
        return webClient
                .get()
                .uri(URL, id)
                .retrieve()
                .bodyToMono(StudentResponse.class)
                .doOnError(error -> log.error("There is an error while sending request {}", error.getMessage()));
    }

    public Flux<StudentResponse> retrieveStudents() {
        return webClient
                .get()
                .uri("/students")
                .retrieve()
                .bodyToFlux(StudentResponse.class)
                .doOnError(error -> log.error("There is an error while sending request {}", error.getMessage()));
    }

}
