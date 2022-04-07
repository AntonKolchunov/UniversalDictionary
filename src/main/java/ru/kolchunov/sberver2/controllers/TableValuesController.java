package ru.kolchunov.sberver2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolchunov.sberver2.exceptions.BadRequestException;
import ru.kolchunov.sberver2.requests.InsertDictRequest;
import ru.kolchunov.sberver2.requests.SearchDictRequest;
import ru.kolchunov.sberver2.responses.FieldValue;
import ru.kolchunov.sberver2.services.TableValuesService;

import java.util.List;

/**
 * Controller for values functionality
 */
@RestController
@RequestMapping("/api/v1/tablevalues/")
public class TableValuesController {
    @Autowired
    private TableValuesService tableValuesService;

    /**
     * Insert new values int the Table of values
     *
     * @param insertDictRequest {@link InsertDictRequest}
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FieldValue>> saveTableValues(@RequestBody InsertDictRequest insertDictRequest) {
        if (insertDictRequest == null) {
            throw new BadRequestException("Empty request received");
        }

        return new ResponseEntity<>(this.tableValuesService.insert(insertDictRequest),
                new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * Search rows by fields
     *
     * @param searchDictRequest {@link SearchDictRequest}
     */
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FieldValue>> searchByFields(@RequestBody SearchDictRequest searchDictRequest) {

        if (searchDictRequest == null) {
            throw new BadRequestException("Empty request received");
        }

        return new ResponseEntity<>(tableValuesService.search(searchDictRequest),
                new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete row by id
     *
     * @param id Id row
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FieldValue>> delete(@PathVariable("id") Long id){
        if (id == null) {
            throw new BadRequestException("Empty id received");
        }

        this.tableValuesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}