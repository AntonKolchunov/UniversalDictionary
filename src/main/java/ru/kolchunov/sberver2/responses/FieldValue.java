package ru.kolchunov.sberver2.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.kolchunov.sberver2.models.DataTypes;

/**
 * DTO for response in the searching values by fields method
 */
@Builder
@ToString
@Getter
public class FieldValue {
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
