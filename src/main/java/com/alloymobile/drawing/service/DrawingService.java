package com.alloymobile.drawing.service;

import com.alloymobile.drawing.integration.client.ClientServiceCaller;
import com.alloymobile.drawing.persistence.model.Drawing;
import com.alloymobile.drawing.persistence.repository.DrawingRepository;
import com.querydsl.core.types.Predicate;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DrawingService {

    private final DrawingRepository drawingRepository;

    private final ClientServiceCaller clientServiceCaller;

    public DrawingService(DrawingRepository drawingRepository, ClientServiceCaller clientServiceCaller) {
        this.drawingRepository = drawingRepository;
        this.clientServiceCaller = clientServiceCaller;
    }

    public Mono<Page<Drawing>> findAllDrawing(Predicate predicate, Pageable pageable){
        return this.drawingRepository.count()
                .flatMap(drawingCount -> {
                    return this.drawingRepository.findAll(predicate,pageable.getSort())
                            .buffer(pageable.getPageSize(),(pageable.getPageNumber() + 1))
                            .elementAt(pageable.getPageNumber(), new ArrayList<>())
                            .map(drawings -> new PageImpl<>(drawings, pageable, drawingCount));
                });
    }

    public Mono<Drawing> findDrawingById(String id){
        return drawingRepository.findById(id);
    }

    public Mono<Drawing> addDrawing(Drawing drawing){
        drawing.setId(new ObjectId().toString());
//        return this.clientServiceCaller.getClientById(drawing.getClient(),token).flatMap(c->{
            return drawingRepository.save(drawing);
//        });
    }

    public Mono<Drawing> updateDrawing(String id, Drawing drawing){
        return drawingRepository.findById(id).flatMap(f->{
            f.setName(drawing.getName());
            f.setDescription(drawing.getDescription());
            f.setImageUrl(drawing.getImageUrl());
            f.setClient(drawing.getClient());
            return this.drawingRepository.save(f);
        });
    }

    public Mono<Void> deleteDrawing(String id){
        return this.drawingRepository.deleteById(id);
    }
}
