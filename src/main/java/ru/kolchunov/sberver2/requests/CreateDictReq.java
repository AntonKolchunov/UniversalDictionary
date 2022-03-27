package ru.kolchunov.sberver2.requests;

import lombok.Data;
import ru.kolchunov.sberver2.models.DataTypes;
import java.util.List;

@Data
public class CreateDictReq {
    private Long code;
    private String name;
    private List<FieldStructure> fieldStructureList;

    @Data
    public static class FieldStructure{
        private String name;
        private DataTypes type;
    }
}
