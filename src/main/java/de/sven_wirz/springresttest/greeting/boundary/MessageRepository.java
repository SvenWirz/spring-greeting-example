package de.sven_wirz.springresttest.greeting.boundary;

import de.sven_wirz.springresttest.greeting.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
}
