package ru.kolchunov.sberver2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.models.StructureDictionaryPK;
import ru.kolchunov.sberver2.services.StructureDictionaryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/structuredictionary/")
public class StructureDictionaryController {
    @Autowired
    private StructureDictionaryService structureDictionaryService;

    @RequestMapping(value = {"id"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StructureDictionary> getStructureDictionary(@PathVariable("id") StructureDictionaryPK structuredictionaryId){
        if (structuredictionaryId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StructureDictionary structureDictionary = this.structureDictionaryService.getById(structuredictionaryId);
        if (structureDictionary == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(structureDictionary, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StructureDictionary> saveStructureDictionary(@RequestBody StructureDictionary structureDictionary){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (structureDictionary == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.structureDictionaryService.save(structureDictionary);
        return new ResponseEntity<>(structureDictionary, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StructureDictionary> updateStructureDictionary(@RequestBody StructureDictionary structureDictionary){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (structureDictionary == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.structureDictionaryService.save(structureDictionary);

        return  new ResponseEntity<>(structureDictionary, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StructureDictionary> deleteStructureDictionary(@PathVariable StructureDictionaryPK structuredictionaryId){
        StructureDictionary structureDictionary = this.structureDictionaryService.getById(structuredictionaryId);

        if (structureDictionary == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.structureDictionaryService.delete(structuredictionaryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StructureDictionary>> getAllStructureDictionary(){
        List<StructureDictionary> structureDictionaryList = this.structureDictionaryService.getAll();
        if(structureDictionaryList.isEmpty()){
            return new ResponseEntity<List<StructureDictionary>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<StructureDictionary>>(structureDictionaryList, HttpStatus.OK);
    }
}
