package ru.kolchunov.sberver2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolchunov.sberver2.services.DictionaryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/structuredictionary/")
public class StructureDictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

/*    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryStructureDTO> deleteStructureDictionary(@PathVariable("id_field") Long id_field){
        DictionaryStructureDTO structureDictionary = this.dictionaryService.getStuctureById(id_field);

        if (structureDictionary == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.dictionaryService.deleteStructure(id_field);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    /*@RequestMapping(value = {"id_field"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryStructureDTO> getStructureDictionary(@PathVariable("id_field") Long id_field){
        if (id_field == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DictionaryStructureDTO structureDictionary = this.dictionaryService.getStuctureById(id_field);
        if (structureDictionary == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(structureDictionary, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryStructureDTO> saveStructureDictionary(@RequestBody DictionaryStructureDTO structureDictionary){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (structureDictionary == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.dictionaryService.saveStructure(structureDictionary);
        return new ResponseEntity<>(structureDictionary, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryStructureDTO> updateStructureDictionary(@RequestBody DictionaryStructureDTO structureDictionary){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (structureDictionary == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.dictionaryService.saveStructure(structureDictionary);

        return  new ResponseEntity<>(structureDictionary, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryStructureDTO> deleteStructureDictionary(@PathVariable("id_field") Long id_field){
        DictionaryStructureDTO structureDictionary = this.dictionaryService.getStuctureById(id_field);

        if (structureDictionary == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.dictionaryService.deleteStructure(id_field);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DictionaryStructureDTO>> getAllStructureDictionary(){
        List<DictionaryStructureDTO> structureDictionaryList = this.dictionaryService.getAllStructure();
        if(structureDictionaryList.isEmpty()){
            return new ResponseEntity<List<DictionaryStructureDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<DictionaryStructureDTO>>(structureDictionaryList, HttpStatus.OK);
    }*/
}
