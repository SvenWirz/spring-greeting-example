package de.sven_wirz.springresttest.greeting.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.sven_wirz.springresttest.greeting.entity.Greeting;
import de.sven_wirz.springresttest.greeting.entity.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectWriter ow;

    @BeforeEach
    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    void createMessage() throws Exception {
        Greeting greeting = new Greeting();
        greeting.setName("Sven");

        Message expected = new Message();
        expected.setMessage("Hallo Sven!");

        sendGreeting(greeting)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(ow.writeValueAsString(expected)))
                .andDo(print());
    }

    @Test
    void getAllMessages_should_return_list_containing_sven_greeting() throws Exception {
        Greeting svenGreeting = new Greeting();
        svenGreeting.setName("Sven");

        Greeting anonymousGreeting = new Greeting();
        anonymousGreeting.setName("Anonymous");

        // Perform POST-Request to send "Sven"-Greeting
        sendGreeting(svenGreeting);

        // Perform POST-Request to send "Anonymous"-Greeting
        sendGreeting(anonymousGreeting);

        // Perform GET-Request to retrieve all greetings
        mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Hallo Sven!")))
                .andExpect(content().string(containsString("Hallo Anonymous!")));
    }

    private ResultActions sendGreeting(Greeting anonymousGreeting) throws Exception {
        return mockMvc.perform(post("/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(anonymousGreeting)));
    }
}