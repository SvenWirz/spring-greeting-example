package de.sven_wirz.springresttest.greeting.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class MessageEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String message;
}
