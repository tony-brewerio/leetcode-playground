package org.playground.leetcode.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class CSVArrayOfStringsConverter implements ArgumentConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final TypeReference<String[]> arrayOfStrings = new TypeReference<>() {
    };

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException("The argument should be a string: " + source);
        }
        try {
            return objectMapper.readValue((String) source, arrayOfStrings);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert", e);
        }
    }
}