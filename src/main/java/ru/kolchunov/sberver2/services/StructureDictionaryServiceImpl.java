package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolchunov.sberver2.models.Dictionary;
import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.models.StructureDictionaryPK;
import ru.kolchunov.sberver2.repositories.DictionaryRepository;
import ru.kolchunov.sberver2.repositories.StructureDictionaryRepository;

import java.util.List;

@Slf4j
@Service
public class StructureDictionaryServiceImpl implements StructureDictionaryService{

    @Autowired
    StructureDictionaryRepository structureDictionaryRepository;
    /*@Autowired
    DictionaryRepository dictionaryRepository;*/

    @Override
    public StructureDictionary getById(StructureDictionaryPK structureDictionaryPK) {
        log.info("IN StructureDictionaryServiceImpl getById {}", structureDictionaryPK);
        return structureDictionaryRepository.getById(structureDictionaryPK);
    }

    @Override
    public void save(StructureDictionary structureDictionary) {
        /*Dictionary dictionary = dictionaryRepository.getById(structureDictionary.getIdDictionary());
        structureDictionary.setDictionary(dictionary);*/
        log.info("IN StructureDictionaryServiceImpl save {}", structureDictionary);
        structureDictionaryRepository.save(structureDictionary);
    }

    @Override
    public void delete(StructureDictionaryPK structureDictionaryPK) {
        log.info("IN StructureDictionaryServiceImpl delete {}", structureDictionaryPK);
        structureDictionaryRepository.deleteById(structureDictionaryPK);
    }

    @Override
    public List<StructureDictionary> getAll() {
        log.info("IN StructureDictionaryServiceImpl getAll");
        return structureDictionaryRepository.findAll();
    }
}
