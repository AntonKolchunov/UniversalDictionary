package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolchunov.sberver2.hibernate.HibernateUtil;
import ru.kolchunov.sberver2.models.Dictionary;
import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.repositories.DictionaryRepository;
import ru.kolchunov.sberver2.repositories.StructureDictionaryRepository;
import ru.kolchunov.sberver2.repositories.ValuesRepository;
import ru.kolchunov.sberver2.requests.CreateDictReq;
import ru.kolchunov.sberver2.requests.UpdateDictReq;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService{

    @Autowired
    DictionaryRepository dictionaryRepository;
    @Autowired
    StructureDictionaryRepository structureDictionaryRepository;
    @Autowired
    ValuesRepository valuesRepository;

    /**
     * Create new dictionary and his structure
     * @param createDictReq {@link CreateDictReq}
     */
    @Override
    public void saveNewStructure(CreateDictReq createDictReq) {
        log.info("IN DictionaryServiceImpl saveNewStructure {}", createDictReq);

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

    /**
     * Delete all structure of the dictionary
     * @param idDictionary  iD dictionary
     */
    @Override
    public void deleteAllStructure(Long idDictionary) {
        log.info("IN DictionaryServiceImpl deleteAllStructure {}", idDictionary);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select  str.id from StructureDictionary str where idDictionary = "+idDictionary);
        List<Long> listIdFields = query.list();
        session.close();

        listIdFields.stream().forEach(x ->structureDictionaryRepository.deleteById(x));
        dictionaryRepository.deleteById(idDictionary);
    }

    /**
     * Delete structure by id of the field
     * @param idField  Id field
     */
    @Override
    public void deleteFieldStructure(Long idField) {
        log.info("IN DictionaryServiceImpl deleteFieldStructure {}", idField);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select  v.id from Values v where v.idField = "+idField);
        List<Long> listIdValues = query.list();
        session.close();

        listIdValues.stream().forEach(x ->valuesRepository.deleteById(x));
        structureDictionaryRepository.deleteById(idField);
    }

    /**
     * Return structure of the dictionary by id
     * @param idDictionary  Id dictionary
     */
    @Override
    public CreateDictReq getAllStructureById(Long idDictionary) {
        log.info("IN DictionaryServiceImpl getAllStructureById {}", idDictionary);
        CreateDictReq createDictReq = new CreateDictReq();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Dictionary d where d.id = "+idDictionary);
        List<Dictionary> dictionaryList = query.list();

        if (!dictionaryList.isEmpty()){
            createDictReq.setCode(dictionaryList.get(0).getCode());
            createDictReq.setName(dictionaryList.get(0).getName());

            query = session.createQuery("select  str.name , str.dataType from StructureDictionary str where str.idDictionary = "+idDictionary);
            createDictReq.setFieldStructureList(query.list());
        }
        session.close();

        return createDictReq;
    }

    /**
     * Update structure of the dictionary
     * @param updateDictReq  {@link UpdateDictReq}
     */
    @Override
    public void updateStructure(UpdateDictReq updateDictReq) {
        log.info("IN DictionaryServiceImpl updateStructure {}", updateDictReq);


        Dictionary dictionary = dictionaryRepository.getById(updateDictReq.getId());
        dictionary.setCode(updateDictReq.getCode());
        dictionary.setName(updateDictReq.getName());
        dictionaryRepository.save(dictionary);

        for (UpdateDictReq.FieldStructure fieldStructure : updateDictReq.getFieldStructureList()){
            StructureDictionary structureDictionary = structureDictionaryRepository.findById(fieldStructure.getId()).orElse(null);
            if (structureDictionary == null){
                structureDictionary = new StructureDictionary();
            }
            structureDictionary.setName(fieldStructure.getName());
            structureDictionary.setDataType(fieldStructure.getType());

            structureDictionaryRepository.save(structureDictionary);
        }
    }

}
