package ru.kolchunov.sberver2.responses;

import lombok.Data;
import ru.kolchunov.sberver2.models.DataTypes;
import java.util.List;

@Data
public class SearchDictRes{
    private Long code;
    private String name;
    private List<FieldValue> fieldValueList;

    @Data
    public static class FieldValue{
        private Long idRow;
        private Long idField;
        private String name;
        private String value;
        private DataTypes dataTypes;
    }
}
