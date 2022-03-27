package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.requests.CreateDictReq;

import java.util.List;

public interface DictionaryService {
    //DictionaryStructureDTO getStuctureById(Long id);

    void saveNewStructure(CreateDictReq createDictReq);

    void deleteStructure(Long id);



    //List<DictionaryStructureDTO> getAllStructure();

}
