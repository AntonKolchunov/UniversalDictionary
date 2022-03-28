package ru.kolchunov.sberver2.responses;

import lombok.Data;

/**
 * DTO for supporting updating of the values
 */
@Data
public class UpdateDictValuesSupport {
    /**
     * Id value
     */
    private Long id;
    /**
     * Name field
     */
    private String name;
    /**
     * Id field
     */
    private Long idField;
}
