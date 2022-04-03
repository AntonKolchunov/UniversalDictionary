package ru.kolchunov.sberver2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolchunov.sberver2.exceptions.BadRequestException;
import ru.kolchunov.sberver2.requests.CreateDictRequest;
import ru.kolchunov.sberver2.responses.DictionaryModel;
import ru.kolchunov.sberver2.services.DictionaryService;

@RestController
@RequestMapping("/api/v1/dictionary/")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryModel> create(@RequestBody CreateDictRequest createDictRequest) {
        if (createDictRequest == null) {
            throw new BadRequestException("Empty request received");
        }

        return new ResponseEntity<>(this.dictionaryService.create(createDictRequest),
                new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryModel> find(@PathVariable Long id) {
        if (id == null) {
            throw new BadRequestException("Empty id received");
        }

        return new ResponseEntity<>(this.dictionaryService.find(id), new HttpHeaders(), HttpStatus.OK);
    }
}