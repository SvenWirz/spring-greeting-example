package de.sven_wirz.springresttest.greeting.control;

import de.sven_wirz.springresttest.greeting.boundary.GreetingController;
import de.sven_wirz.springresttest.greeting.boundary.MessageRepository;
import de.sven_wirz.springresttest.greeting.entity.Greeting;
import de.sven_wirz.springresttest.greeting.entity.Message;
import de.sven_wirz.springresttest.greeting.entity.MessageEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GreetingControllerImpl implements GreetingController {

    private MessageRepository messageRepository;
    private MessageMapper messageMapper;

    public GreetingControllerImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public Message createMessage(Greeting greeting) {
        MessageEntity message = new MessageEntity();
        message.setMessage(String.format("Hallo %s!", greeting.getName()));

        MessageEntity savedMessage = messageRepository.save(message);

        return messageMapper.mapMessageEntityToMessage(savedMessage);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(messageMapper::mapMessageEntityToMessage)
                .collect(Collectors.toList());
    }
}
