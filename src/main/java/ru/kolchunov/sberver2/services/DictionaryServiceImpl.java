package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolchunov.sberver2.models.Dictionary;
import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.repositories.DictionaryRepository;
import ru.kolchunov.sberver2.repositories.StructureDictionaryRepository;
import ru.kolchunov.sberver2.requests.CreateDictReq;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService{

    @Autowired
    DictionaryRepository dictionaryRepository;
    @Autowired
    StructureDictionaryRepository structureDictionaryRepository;

    @Override
    public void saveNewStructure(CreateDictReq createDictReq) {
        log.info("IN DictionaryServiceImpl save {}", createDictReq);

        Dictionary dictionary = new Dictionary();
        dictionary.setName(createDictReq.getName());
        dictionary.setCode(createDictReq.getCode());
        dictionaryRepository.save(dictionary);

        for (CreateDictReq.FieldStructure dictReq : createDictReq.getFieldStructureList()){
            StructureDictionary structureDictionary = new StructureDictionary();
            structureDictionary.setIdDictionary(dictionary.getId());
            structureDictionary.setName(dictReq.getName());
            structureDictionary.setDataType(dictReq.getType());
            structureDictionaryRepository.save(structureDictionary);
        }

    }

    @Override
    public void deleteStructure(Long id) {
        log.info("IN DictionaryServiceImpl deleteStructure {}", id);
        structureDictionaryRepository.deleteById(id);
    }



    /*    @Autowired
    StructureDictionaryRepository structureDictionaryRepository;
    @Autowired
    StructureDictionaryConverter structureDictionaryConverter;
    @Autowired
    DictionaryRepository dictionaryRepository;
    @Autowired
    DictionaryConverter dictionaryConverter;


    @Override
    public List<DictionaryStructureDTO> getAllStructure() {
        log.info("IN DictionaryServiceImpl getAllStructure ");
        List<StructureDictionary> dictionaryTableList = structureDictionaryRepository.findAll();
        return structureDictionaryConverter.entityToDTO(dictionaryTableList);
    }

    @Override
    public DictionaryStructureDTO getStuctureById(Long id) {
        log.info("IN DictionaryServiceImpl getStuctureById {}", id);
        StructureDictionary structureDictionary = structureDictionaryRepository.findById(id).orElse(null);
        return structureDictionaryConverter.convertEntityToDTO(structureDictionary);
    }

    @Override
    public void saveStructure(DictionaryStructureDTO dictionaryStructureDTO) {
        log.info("IN DictionaryServiceImpl saveStructure {}", dictionaryStructureDTO);
        StructureDictionary structureDictionary = structureDictionaryConverter.dtoToEntity(dictionaryStructureDTO);
        structureDictionaryRepository.save(structureDictionary);
    }

    @Override
    public void deleteStructure(Long id) {
        log.info("IN DictionaryServiceImpl deleteStructure {}", id);
        structureDictionaryRepository.deleteById(id);
    }

    @Override
    public DictionaryDTO getDictionaryById(Long id) {
        log.info("IN DictionaryServiceImpl getDictionaryById {}", id);
        DictionaryTable dictionaryTable = dictionaryRepository.findById(id).orElse(null);
        return dictionaryConverter.convertEntityToDTO(dictionaryTable);
    }

    @Override
    public void saveDictionary(DictionaryDTO dictionaryDTO) {
        log.info("IN DictionaryServiceImpl saveDictionary {}", dictionaryDTO);
        DictionaryTable dictionaryTable = dictionaryConverter.dtoToEntity(dictionaryDTO);
        dictionaryRepository.save(dictionaryTable);
    }

    @Override
    public void deleteDictionary(Long id) {
        log.info("IN DictionaryServiceImpl deleteDictionary {}", id);
        dictionaryRepository.deleteById(id);
    }

    @Override
    public List<DictionaryDTO> getAllDictionary() {
        log.info("IN DictionaryServiceImpl getAllDictionary {}");
        List<DictionaryTable> dictionaryTableList = dictionaryRepository.findAll();
        return dictionaryConverter.entityToDTO(dictionaryTableList);
    }*/
}
