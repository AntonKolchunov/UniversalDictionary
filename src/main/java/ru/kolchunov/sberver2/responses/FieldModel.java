package ru.kolchunov.sberver2.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.kolchunov.sberver2.models.DataTypes;

@Builder
@ToString
@Getter
public class FieldModel {
    private String name;
    private DataTypes type;
}
