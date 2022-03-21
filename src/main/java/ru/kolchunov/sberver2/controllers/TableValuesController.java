package ru.kolchunov.sberver2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolchunov.sberver2.models.Dictionary;
import ru.kolchunov.sberver2.models.TableValues;
import ru.kolchunov.sberver2.models.TableValuesPK;
import ru.kolchunov.sberver2.services.DictionaryService;
import ru.kolchunov.sberver2.services.TableValuesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tablevalues/")
public class TableValuesController {
    @Autowired
    private TableValuesService tableValuesService;

    @RequestMapping(value = {"id"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TableValues> getTableValues(@PathVariable("id") TableValuesPK tableValuesId){
        if (tableValuesId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TableValues tableValues = this.tableValuesService.getById(tableValuesId);
        if (tableValues == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tableValues, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TableValues> saveTableValues(@RequestBody TableValues tableValues){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (tableValues == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.tableValuesService.save(tableValues);
        return new ResponseEntity<>(tableValues, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TableValues> updateTableValues(@RequestBody TableValues tableValues){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (tableValues == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.tableValuesService.save(tableValues);

        return  new ResponseEntity<>(tableValues, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TableValues> deleteTableValues(@PathVariable TableValuesPK tableValuesId){
        TableValues tableValues = this.tableValuesService.getById(tableValuesId);

        if (tableValues == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.tableValuesService.delete(tableValuesId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TableValues>> getAllTableValues(){
        List<TableValues> tableValuesList = this.tableValuesService.getAll();
        if(tableValuesList.isEmpty()){
            return new ResponseEntity<List<TableValues>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<TableValues>>(tableValuesList, HttpStatus.OK);
    }
}
