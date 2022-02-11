package com.alloymobile.drawing.persistence.repository;

import com.alloymobile.drawing.persistence.model.Drawing;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DrawingRepository extends ReactiveMongoRepository<Drawing, String>, ReactiveQuerydslPredicateExecutor<Drawing> {
}
