package ru.kolchunov.sberver2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kolchunov.sberver2.models.Dictionary;
import ru.kolchunov.sberver2.services.DictionaryService;
import ru.kolchunov.sberver2.services.DictionaryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionary/")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dictionary> getDictionary(@PathVariable("id") Long dictionaryId){
        if (dictionaryId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dictionary dictionary = this.dictionaryService.getById(dictionaryId);
        if (dictionary == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dictionary, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dictionary> saveDictionary(@RequestBody Dictionary dictionary){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (dictionary == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.dictionaryService.save(dictionary);
        return new ResponseEntity<>(dictionary, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dictionary> updateDictionary(@RequestBody Dictionary dictionary){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (dictionary == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.dictionaryService.save(dictionary);

        return  new ResponseEntity<>(dictionary, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dictionary> deleteDictionary(@PathVariable Long id){
        Dictionary dictionary = this.dictionaryService.getById(id);

        if (dictionary == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.dictionaryService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dictionary>> getAllDictionary(){
        List<Dictionary> dictionaryList = this.dictionaryService.getAll();
        if(dictionaryList.isEmpty()){
            return new ResponseEntity<List<Dictionary>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Dictionary>>(dictionaryList, HttpStatus.OK);
    }
}
