package de.sven_wirz.springresttest.greeting.boundary;

import de.sven_wirz.springresttest.greeting.entity.Greeting;
import de.sven_wirz.springresttest.greeting.entity.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface GreetingController {

    /**
     * Creates a welcome message.
     *
     * @param greeting The name send to the endpoint.
     * @return Personalized welcome message.
     */
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    Message createMessage(@RequestBody Greeting greeting);

    /**
     * Endpoint to retrieve all welcome messages, which are created.
     *
     * @return All welcomes messages, which are created.
     */
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    List<Message> getAllMessages();
}
