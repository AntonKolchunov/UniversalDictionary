package ru.kolchunov.sberver2.requests;

import lombok.Data;
import ru.kolchunov.sberver2.models.SearchCondition;

import java.util.List;
@Data
public class SearchDictReq {
    private Long idDict;
    private List<Parametrs> parametrsList;

    @Data
    public static class Parametrs{
        private String name;
        private String value;
        private SearchCondition condition;
    }

}
