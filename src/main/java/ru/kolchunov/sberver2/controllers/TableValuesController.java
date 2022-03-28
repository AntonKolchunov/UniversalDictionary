package ru.kolchunov.sberver2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolchunov.sberver2.requests.InsertDictReq;
import ru.kolchunov.sberver2.requests.SearchDictReq;
import ru.kolchunov.sberver2.responses.SearchDictRes;
import ru.kolchunov.sberver2.services.TableValuesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tablevalues/")
public class TableValuesController {
    @Autowired
    private TableValuesService tableValuesService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InsertDictReq> saveTableValues(@RequestBody InsertDictReq insertDictReq){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (insertDictReq == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.tableValuesService.save(insertDictReq);
        return new ResponseEntity<>(insertDictReq, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchDictRes> searchByFields(@RequestBody SearchDictReq searchDictReq){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (searchDictReq == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        SearchDictRes searchDictRes = tableValuesService.searchByFields(searchDictReq);
        return new ResponseEntity<>(searchDictRes, httpHeaders, HttpStatus.CREATED);
    }

/*    @RequestMapping(value = {"id"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StructureTableValuesDTO> getTableValues(@PathVariable("id") Long id){
        if (id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StructureTableValuesDTO structureTableValuesDTO = this.tableValuesService.getById(id);
        if (structureTableValuesDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(structureTableValuesDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StructureTableValuesDTO> saveTableValues(@RequestBody StructureTableValuesDTO structureTableValuesDTO){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (structureTableValuesDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.tableValuesService.save(structureTableValuesDTO);
        return new ResponseEntity<>(structureTableValuesDTO, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StructureTableValuesDTO> updateTableValues(@RequestBody StructureTableValuesDTO structureTableValuesDTO){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (structureTableValuesDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.tableValuesService.save(structureTableValuesDTO);

        return  new ResponseEntity<>(structureTableValuesDTO, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StructureTableValuesDTO> deleteTableValues(@PathVariable("id") Long id){
        StructureTableValuesDTO structureTableValuesDTO = this.tableValuesService.getById(id);

        if (structureTableValuesDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.tableValuesService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StructureTableValuesDTO>> getAllTableValues(){
        List<StructureTableValuesDTO> tableValuesList = this.tableValuesService.getAll();
        if(tableValuesList.isEmpty()){
            return new ResponseEntity<List<StructureTableValuesDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<StructureTableValuesDTO>>(tableValuesList, HttpStatus.OK);
    }*/
}
