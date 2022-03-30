package ru.kolchunov.sberver2.requests;

import lombok.Data;
import ru.kolchunov.sberver2.models.DataTypes;

import java.util.List;

/**
 * DTO for updating structure of the  dictionary
 */
@Data
public class UpdateDictRequest {
    /**
     * Id dictionary
     */
    private Long id;
    /**
     * Code of the dictionary in external system
     */
    private Long code;
    /**
     * Name of the dictionary
     */
    private String name;
    /**
     * Structure of the dictionary
     */
    private List<FieldStructure> fieldsStructure;

    @Data
    public static class FieldStructure {
        /**
         * Id field
         */
        private Long id;
        /**
         * Name field
         */
        private String name;
        /**
         * Type field
         */
        private DataTypes type;
    }
}
