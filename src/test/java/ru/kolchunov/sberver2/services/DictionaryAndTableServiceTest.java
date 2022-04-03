package ru.kolchunov.sberver2.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kolchunov.sberver2.models.DataTypes;
import ru.kolchunov.sberver2.models.SearchCondition;
import ru.kolchunov.sberver2.requests.CreateDictRequest;
import ru.kolchunov.sberver2.requests.InsertDictRequest;
import ru.kolchunov.sberver2.requests.SearchDictRequest;
import ru.kolchunov.sberver2.responses.DictionaryModel;
import ru.kolchunov.sberver2.responses.FieldValue;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class DictionaryAndTableServiceTest {
    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private TableValuesService tableValuesService;

    @Test
    void saveNewStructureAndInsertRow() {
        CreateDictRequest createDictRequest = new CreateDictRequest();
        createDictRequest.setCode("TEST_CODE");
        createDictRequest.setName("SOME NAME");
        List<CreateDictRequest.FieldStructure> fieldsStructure = new ArrayList<>();
        fieldsStructure.add(new CreateDictRequest.FieldStructure("first_long_field", DataTypes.LONG));
        fieldsStructure.add(new CreateDictRequest.FieldStructure("second_boolean_field", DataTypes.BOOLEAN));
        fieldsStructure.add(new CreateDictRequest.FieldStructure("third_string_field", DataTypes.STRING));
        fieldsStructure.add(new CreateDictRequest.FieldStructure("fourth_date_field", DataTypes.DATE));
        createDictRequest.setFieldsStructure(fieldsStructure);

        DictionaryModel dictionary = dictionaryService.create(createDictRequest);
        Assertions.assertNotNull(dictionary.getId());
        Assertions.assertEquals("TEST_CODE", dictionary.getCode());
        Assertions.assertEquals("SOME NAME", dictionary.getName());
        Assertions.assertEquals(4, dictionary.getFields().size());

        InsertDictRequest insertDictRequest = new InsertDictRequest();
        insertDictRequest.setIdDict(dictionary.getId());
        List<InsertDictRequest.FieldValue> fieldsValue = new ArrayList<>();
        fieldsValue.add(new InsertDictRequest.FieldValue("first_long_field", "100001L"));
        fieldsValue.add(new InsertDictRequest.FieldValue("second_boolean_field", "true"));
        fieldsValue.add(new InsertDictRequest.FieldValue("third_string_field", "string_1"));
        fieldsValue.add(new InsertDictRequest.FieldValue("fourth_date_field", "2022.03.30"));
        insertDictRequest.setFieldsValue(fieldsValue);

        List<FieldValue> values = tableValuesService.insert(insertDictRequest);

        Assertions.assertNotNull(values);
        Assertions.assertEquals(4, values.size());

        insertDictRequest = new InsertDictRequest();
        insertDictRequest.setIdDict(dictionary.getId());
        fieldsValue = new ArrayList<>();
        fieldsValue.add(new InsertDictRequest.FieldValue("first_long_field", "100002L"));
        fieldsValue.add(new InsertDictRequest.FieldValue("second_boolean_field", "false"));
        fieldsValue.add(new InsertDictRequest.FieldValue("third_string_field", "string_1"));
        fieldsValue.add(new InsertDictRequest.FieldValue("fourth_date_field", "2022.04.03"));
        insertDictRequest.setFieldsValue(fieldsValue);

        values = tableValuesService.insert(insertDictRequest);

        Assertions.assertNotNull(values);
        Assertions.assertEquals(4, values.size());

        SearchDictRequest searchDictRequest = new SearchDictRequest();
        searchDictRequest.setIdDict(dictionary.getId());
        List<SearchDictRequest.SearchTerm> searchTerms = new ArrayList<>();
        searchTerms.add(new SearchDictRequest.SearchTerm("first_long_field", "100001L", SearchCondition.GREATER_OR_EQUAL));
        searchTerms.add(new SearchDictRequest.SearchTerm("third_string_field", "string_1", SearchCondition.EQUAL));
        searchDictRequest.setSearchTerms(searchTerms);

        System.out.println(tableValuesService.search(searchDictRequest));
    }
}
