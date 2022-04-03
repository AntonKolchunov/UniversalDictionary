package ru.kolchunov.sberver2.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Getter
public class DictionaryModel {
    private Long id;
    private String code;
    private String name;
    @Singular
    private List<FieldModel> fields;
}
