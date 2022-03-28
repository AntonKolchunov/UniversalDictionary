package ru.kolchunov.sberver2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolchunov.sberver2.requests.CreateDictReq;
import ru.kolchunov.sberver2.services.DictionaryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionary/")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateDictReq> saveDictionary(@RequestBody CreateDictReq createDictReq){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (createDictReq == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.dictionaryService.saveNewStructure(createDictReq);
        return new ResponseEntity<>(createDictReq, httpHeaders, HttpStatus.CREATED);
    }

/*    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryDTO> getDictionary(@PathVariable("id") Long dictionaryId){
        if (dictionaryId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DictionaryDTO dictionary = this.dictionaryService.getDictionaryById(dictionaryId);
        if (dictionary == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dictionary, HttpStatus.OK);
    }*/


    /*@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryDTO> updateDictionary(@RequestBody DictionaryDTO dictionary){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (dictionary == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.dictionaryService.saveDictionary(dictionary);

        return  new ResponseEntity<>(dictionary, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DictionaryDTO> deleteDictionary(@PathVariable Long id){
        DictionaryDTO dictionary = this.dictionaryService.getDictionaryById(id);

        if (dictionary == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.dictionaryService.deleteDictionary(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DictionaryDTO>> getAllDictionary(){
        List<DictionaryDTO> dictionaryList = this.dictionaryService.getAllDictionary();
        if(dictionaryList.isEmpty()){
            return new ResponseEntity<List<DictionaryDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<DictionaryDTO>>(dictionaryList, HttpStatus.OK);
    }*/
}
