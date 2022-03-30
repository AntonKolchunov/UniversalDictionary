package ru.kolchunov.sberver2.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kolchunov.sberver2.models.DataTypes;
import ru.kolchunov.sberver2.requests.CreateDictRequest;
import ru.kolchunov.sberver2.requests.InsertDictRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class DictionaryServiceImplTest {

    @Autowired
    DictionaryServiceImpl dictionaryService;

    @Test
    void saveNewStructure() {
        CreateDictRequest createDictRequest = new CreateDictRequest();
        createDictRequest.setCode(1234L);
        createDictRequest.setName("dictionary_from_unit_test");
        List<CreateDictRequest.FieldStructure> fieldsStructure = new ArrayList<>();
        fieldsStructure.add(new CreateDictRequest.FieldStructure("first_long_field", DataTypes.LONG));
        fieldsStructure.add(new CreateDictRequest.FieldStructure("second_boolean_field", DataTypes.BOOLEAN));
        fieldsStructure.add(new CreateDictRequest.FieldStructure("third_string_field", DataTypes.STRING));
        fieldsStructure.add(new CreateDictRequest.FieldStructure("fourth_date_field", DataTypes.DATE));
        createDictRequest.setFieldsStructure(fieldsStructure);
        dictionaryService.saveNewStructure(createDictRequest);
    }
}