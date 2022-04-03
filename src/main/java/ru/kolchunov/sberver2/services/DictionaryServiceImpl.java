package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolchunov.sberver2.exceptions.NotFoundException;
import ru.kolchunov.sberver2.models.Dictionary;
import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.repositories.DictionaryRepository;
import ru.kolchunov.sberver2.repositories.StructureDictionaryRepository;
import ru.kolchunov.sberver2.requests.CreateDictRequest;
import ru.kolchunov.sberver2.responses.DictionaryModel;
import ru.kolchunov.sberver2.responses.FieldModel;



@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Autowired
    private StructureDictionaryRepository structureDictionaryRepository;

    /**
     * Create new dictionary and his structure
     *
     * @param createDictRequest {@link CreateDictRequest}
     */
    @Override
    @Transactional
    public DictionaryModel create(CreateDictRequest createDictRequest) {
        log.info("CREATE DICTIONARY {}", createDictRequest);

        Dictionary dictionary = new Dictionary();
        dictionary.setName(createDictRequest.getName());
        dictionary.setCode(createDictRequest.getCode());
        dictionaryRepository.save(dictionary);

        for (CreateDictRequest.FieldStructure dictReq : createDictRequest.getFieldsStructure()) {
            StructureDictionary structureDictionary = new StructureDictionary();
            structureDictionary.setIdDictionary(dictionary.getId());
            structureDictionary.setName(dictReq.getName());
            structureDictionary.setDataType(dictReq.getType());
            structureDictionaryRepository.save(structureDictionary);
        }

        return find(dictionary.getId());
    }

    /**
     * Delete all structure of the dictionary
     *
     * @param idDictionary Id dictionary
     */
    @Override
    @Transactional
    public void delete(Long idDictionary) {
        log.info("DELETE DICTIONARY BY ID {}", idDictionary);

        structureDictionaryRepository.deleteAllByIdDictionary(idDictionary);
        dictionaryRepository.deleteById(idDictionary);
    }

    /**
     * Return structure of the dictionary by id
     *
     * @param idDictionary Id dictionary
     */
    @Override
    @Transactional
    public DictionaryModel find(Long idDictionary) {
        log.info("FIND DICTIONARY BY ID {}", idDictionary);

        DictionaryModel.DictionaryModelBuilder dictionaryBuilder = dictionaryRepository.findById(idDictionary)
                .map(this::dictionaryToBuilder)
                .orElseThrow(() -> new NotFoundException("Dictionary with id " + idDictionary + " not found in database"));
        structureDictionaryRepository.findAllByIdDictionary(idDictionary)
                .map(this::fieldToModel)
                .forEach(dictionaryBuilder::field);

        return dictionaryBuilder.build();
    }

    private FieldModel fieldToModel(StructureDictionary field) {
        return FieldModel.builder()
                .name(field.getName())
                .type(field.getDataType())
                .build();
    }

    private DictionaryModel.DictionaryModelBuilder dictionaryToBuilder(Dictionary dictionary) {
        return DictionaryModel.builder()
                .id(dictionary.getId())
                .code(dictionary.getCode())
                .name(dictionary.getName());
    }
}
