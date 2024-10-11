package com.alco.armapi.infrastructure.adapter.persistence.readings;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ReadingsListConverter implements DynamoDBTypeConverter<String, List<Readings>> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convert(List<Readings> readings) {
        try {
            return objectMapper.writeValueAsString(readings);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert readings list to JSON", e);
        }
    }

    @Override
    public List<Readings> unconvert(String readingsJson) {
        try {
            return objectMapper.readValue(readingsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Readings.class));
        } catch (Exception e) {
            throw new RuntimeException("Unable to convert JSON to readings list", e);
        }
    }
}
