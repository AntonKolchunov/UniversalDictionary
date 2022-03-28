package ru.kolchunov.sberver2.responses;

import lombok.Data;
import ru.kolchunov.sberver2.models.DataTypes;
import java.util.List;

/**
 * DTO for response in the searching values by fields method
 */
@Data
public class SearchDictRes{
    /**
     * Code of the dictionary in external system
     */
    private Long code;
    /**
     * name dictionary
     */
    private String name;
    /**
     * Detailing information by fields
     */
    private List<FieldValue> fieldValueList;

    @Data
    public static class FieldValue{
        /**
         * Id row
         */
        private Long idRow;
        /**
         * Id field
         */
        private Long idField;
        /**
         * Name of the field
         */
        private String name;
        /**
         * Value of the field
         */
        private String value;
        /**
         * Data type of the field
         */
        private DataTypes dataTypes;
    }
}
