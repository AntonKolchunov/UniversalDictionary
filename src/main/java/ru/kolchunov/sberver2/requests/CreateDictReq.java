package ru.kolchunov.sberver2.requests;

import lombok.Data;
import ru.kolchunov.sberver2.models.DataTypes;


import java.util.List;

/**
 * DTO for creating new Dictionary and his structure
 */
@Data
public class CreateDictReq {
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
    private List<FieldStructure> fieldStructureList;

    @Data
    public static class FieldStructure{
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
