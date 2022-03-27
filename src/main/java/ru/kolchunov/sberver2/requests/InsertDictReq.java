package ru.kolchunov.sberver2.requests;

import lombok.Data;
import java.util.List;

@Data
public class InsertDictReq {
    private Long idDict;
    private List<FieldValue> fieldValueList;

    @Data
    public static class FieldValue{
        private String name;
        private String value;
    }
}