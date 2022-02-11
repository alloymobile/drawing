package com.alloymobile.drawing.persistence.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection  = "drawings")
@Data
public class Drawing implements Serializable {
    @Id
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private String client;
}
