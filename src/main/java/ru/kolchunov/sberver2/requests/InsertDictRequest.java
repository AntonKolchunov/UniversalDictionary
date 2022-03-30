package ru.kolchunov.sberver2.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for inserting new rows in dictionary
 */
@Data
public class InsertDictRequest {
    /**
     * Id dictionary
     */
    private Long idDict;
    /**
     * List values for inserting
     */
    private List<FieldValue> fieldsValue;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldValue {
        /**
         * Name field
         */
        private String name;
        /**
         * Value field
         */
        private String value;
    }
}