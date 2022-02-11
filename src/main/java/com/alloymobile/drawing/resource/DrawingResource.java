package com.alloymobile.drawing.resource;

import com.alloymobile.drawing.application.config.Constant;
import com.alloymobile.drawing.application.utils.PageData;
import com.alloymobile.drawing.persistence.model.Drawing;
import com.alloymobile.drawing.service.DrawingBinding;
import com.alloymobile.drawing.service.DrawingService;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
public class DrawingResource {

    private final DrawingService drawingService;

    private final PageData page;

    public DrawingResource(DrawingService drawingService, PageData page) {
        this.drawingService = drawingService;
        this.page = page;
    }

    @GetMapping(value= Constant.FREE_URL+"/drawings", produces = "application/json")
    public Mono<Page<Drawing>> getAllDrawing(@QuerydslPredicate(root = Drawing.class,bindings = DrawingBinding.class) Predicate predicate
            , @RequestParam(name = "search",required = false) String search
            , @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "size", required = false) Integer size
            , @RequestParam(value = "sort", required = false) String sort) {
        if (Objects.nonNull(search)) {
            predicate = DrawingBinding.createSearchQuery(search);
        }
        return this.drawingService.findAllDrawing(predicate, this.page.getPage(page, size, sort));
    }


    @GetMapping(value=Constant.FREE_URL+"/drawings/{id}", produces = "application/json")
    public Mono<Drawing> getDrawingById(@PathVariable(name="id") String id){
        return this.drawingService.findDrawingById(id);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(value=Constant.BASE_URL+"/drawings", produces = "application/json")
    public Mono<Drawing> addDrawing(@RequestBody Drawing drawing){
        return this.drawingService.addDrawing(drawing);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping(value=Constant.BASE_URL+"/drawings/{id}")
    public Mono<Drawing> updateDrawing(@PathVariable(name="id") String id, @RequestBody Drawing drawing){
        return this.drawingService.updateDrawing(id, drawing);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping(value=Constant.BASE_URL+"/drawings/{id}")
    public Mono<Void> deleteDrawing(@PathVariable(name="id") String id){
        return this.drawingService.deleteDrawing(id);
    }
}
