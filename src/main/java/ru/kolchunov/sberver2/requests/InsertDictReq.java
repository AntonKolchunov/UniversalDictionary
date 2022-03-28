package ru.kolchunov.sberver2.requests;

import lombok.Data;
import java.util.List;

/**
 * DTO for inserting new rows in dictionary
 */
@Data
public class InsertDictReq {
    /**
     * Id dictionary
     */
    private Long idDict;
    /**
     * List values for inserting
     */
    private List<FieldValue> fieldValueList;

    @Data
    public static class FieldValue{
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