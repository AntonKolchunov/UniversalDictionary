package ru.kolchunov.sberver2.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kolchunov.sberver2.models.SearchCondition;
import ru.kolchunov.sberver2.requests.InsertDictRequest;
import ru.kolchunov.sberver2.requests.SearchDictRequest;
import ru.kolchunov.sberver2.responses.SearchDictResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class TableValuesServiceTest {

    @Autowired
    TableValuesService tableValuesService;

    @Test
    void save() {
        InsertDictRequest insertDictRequest = new InsertDictRequest();
        insertDictRequest.setIdDict(12L);
        List<InsertDictRequest.FieldValue> fieldsValue = new ArrayList<>();
        fieldsValue.add(new InsertDictRequest.FieldValue("first_long_field", "100001L"));
        fieldsValue.add(new InsertDictRequest.FieldValue("second_boolean_field", "true"));
        fieldsValue.add(new InsertDictRequest.FieldValue("third_string_field", "string_1"));
        fieldsValue.add(new InsertDictRequest.FieldValue("fourth_date_field", "2022.03.30"));
        insertDictRequest.setFieldsValue(fieldsValue);
        tableValuesService.save(insertDictRequest);
    }

    @Test
    void searchByFields() {
        SearchDictRequest searchDictRequest = new SearchDictRequest();
        searchDictRequest.setIdDict(12L);
        List<SearchDictRequest.SearchTerm> searchTerms = new ArrayList<>();
        searchTerms.add(new SearchDictRequest.SearchTerm("first_long_field", "100001L", SearchCondition.EQUAL));
        searchTerms.add(new SearchDictRequest.SearchTerm("third_string_field", "str%", SearchCondition.LIKE));
        searchDictRequest.setSearchTerms(searchTerms);
        SearchDictResponse searchDictResponse = tableValuesService.searchByFields(searchDictRequest);
        System.out.println(searchDictResponse);
    }
}