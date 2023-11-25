package org.playground.leetcode.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.util.List;

public class CSVListOfIntegersConverter implements ArgumentConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final TypeReference<List<Integer>> listOfIntegers = new TypeReference<>() {
    };

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException("The argument should be a string: " + source);
        }
        try {
            return objectMapper.readValue((String) source, listOfIntegers);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert", e);
        }
    }
}