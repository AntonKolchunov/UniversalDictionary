package ru.kolchunov.sberver2.requests;

import lombok.Data;
import ru.kolchunov.sberver2.models.SearchCondition;

import java.util.List;

/**
 * DTO for searching by fields in the dictionary
 */
@Data
public class SearchDictReq {
    /**
     * Id dictionary
     */
    private Long idDict;
    /**
     * List ot the paramets for searching
     */
    private List<Parametrs> parametrsList;

    @Data
    public static class Parametrs{
        /**
         * Name of the field
         */
        private String name;
        /**
         * Search value of the field
         */
        private String value;
        /**
         * Search condition
         */
        private SearchCondition condition;
    }

}
