package de.sven_wirz.springresttest.greeting.control;

import de.sven_wirz.springresttest.greeting.entity.Message;
import de.sven_wirz.springresttest.greeting.entity.MessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    Message mapMessageEntityToMessage(MessageEntity entity);
}
