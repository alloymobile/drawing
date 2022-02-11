package com.alloymobile.drawing.integration.client.model;

import lombok.Data;

import java.util.List;

@Data
public class Location {
    private String type;
    private List<Double> coordinates;
}
