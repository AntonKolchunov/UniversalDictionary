package ru.kolchunov.sberver2.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kolchunov.sberver2.models.SearchCondition;

import java.util.List;

/**
 * DTO for searching by fields in the dictionary
 */
@Data
public class SearchDictRequest {
    /**
     * Id dictionary
     */
    private Long idDict;
    /**
     * List ot the paramets for searching
     */
    private List<SearchTerm> searchTerms;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchTerm {
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
