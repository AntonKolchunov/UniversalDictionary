package ru.kolchunov.sberver2.requests;

import lombok.Data;

import java.util.List;

/**
 * DTO for updating row of the values of the dictionary
 */
@Data
public class UpdateDictValuesRequest {
    /**
     * Id row of the values
     */
    private Long idRow;
    /**
     * List values for updating
     */
    private List<FieldValue> fieldsValue;

    @Data
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
