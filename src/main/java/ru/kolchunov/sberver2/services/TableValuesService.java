package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.requests.InsertDictReq;
import ru.kolchunov.sberver2.requests.SearchDictReq;
import ru.kolchunov.sberver2.responses.SearchDictRes;

import java.util.List;

public interface TableValuesService {

    void save(InsertDictReq insertDictReq);

    SearchDictRes searchByFields(SearchDictReq searchDictReq);

/*    StructureTableValuesDTO getById(Long id);

    void save(StructureTableValuesDTO structureTableValuesDTO);

    void delete(Long id);

    List<StructureTableValuesDTO> getAll();

    List<StructureTableValuesDTO> searchByFields(ParametrsForSearchDTO parametrsForSearchDTO);*/
}
