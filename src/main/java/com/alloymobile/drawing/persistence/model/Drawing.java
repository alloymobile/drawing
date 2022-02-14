package com.alloymobile.drawing.persistence.model;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection  = "drawings")
@Data
public class Drawing implements Serializable {
    @Id
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private String client;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedBy
    private String lastModifiedBy;
    @LastModifiedDate
    private LocalDateTime  lastModifiedDate;
    @Version
    private Integer version;
}
