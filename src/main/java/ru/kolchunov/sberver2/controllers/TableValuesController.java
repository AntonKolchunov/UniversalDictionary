package ru.kolchunov.sberver2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolchunov.sberver2.exceptions.BadRequestException;
import ru.kolchunov.sberver2.requests.CreateDictRequest;
import ru.kolchunov.sberver2.requests.InsertDictRequest;
import ru.kolchunov.sberver2.requests.SearchDictRequest;
import ru.kolchunov.sberver2.responses.DictionaryModel;
import ru.kolchunov.sberver2.responses.FieldValue;
import ru.kolchunov.sberver2.services.TableValuesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tablevalues/")
public class TableValuesController {
    @Autowired
    private TableValuesService tableValuesService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FieldValue>> saveTableValues(@RequestBody InsertDictRequest insertDictRequest) {
        if (insertDictRequest == null) {
            throw new BadRequestException("Empty request received");
        }

        return new ResponseEntity<>(this.tableValuesService.insert(insertDictRequest),
                new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FieldValue>> searchByFields(@RequestBody SearchDictRequest searchDictRequest) {

        if (searchDictRequest == null) {
            throw new BadRequestException("Empty request received");
        }

        return new ResponseEntity<>(tableValuesService.search(searchDictRequest),
                new HttpHeaders(), HttpStatus.OK);
    }

}