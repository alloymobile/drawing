package com.alloymobile.drawing.service;

import com.alloymobile.drawing.persistence.model.QDrawing;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Component;

@Component
public class DrawingBinding  implements QuerydslBinderCustomizer<QDrawing> {
    @Override
    public void customize(QuerydslBindings querydslBindings, QDrawing qDrawing) {
        // Make case-insensitive 'like' filter for all string properties
        querydslBindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    public static Predicate createSearchQuery(String search) {
        BooleanBuilder where = new BooleanBuilder();
        QDrawing qDrawing = QDrawing.drawing;

        BooleanBuilder whereId = new BooleanBuilder();
        whereId.and(qDrawing.name.containsIgnoreCase(search));
        where.or(qDrawing.name.containsIgnoreCase(search));

        whereId = new BooleanBuilder();
        whereId.and(qDrawing.description.containsIgnoreCase(search));
        where.or(whereId);

        whereId = new BooleanBuilder();
        whereId.and(qDrawing.imageUrl.containsIgnoreCase(search));
        where.or(whereId);

        return where;
    }
}
