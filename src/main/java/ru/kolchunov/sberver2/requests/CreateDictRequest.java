package ru.kolchunov.sberver2.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kolchunov.sberver2.models.DataTypes;


import java.util.List;

/**
 * DTO for creating new Dictionary and his structure
 */
@Data
public class CreateDictRequest {
    /**
     * Code of the dictionary in external system
     */
    private String code;
    /**
     * Name of the dictionary
     */
    private String name;
    /**
     * Structure of the dictionary
     */
    private List<FieldStructure> fieldsStructure;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldStructure {
        /**
         * Name of the field
         */
        private String name;
        /**
         * Type of the field
         */
        private DataTypes type;
    }
}
