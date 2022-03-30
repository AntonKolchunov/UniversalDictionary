package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kolchunov.sberver2.SberVer2Application;
import ru.kolchunov.sberver2.hibernate.HibernateUtil;
import ru.kolchunov.sberver2.models.Dictionary;
import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.repositories.DictionaryRepository;
import ru.kolchunov.sberver2.repositories.StructureDictionaryRepository;
import ru.kolchunov.sberver2.repositories.ValuesRepository;
import ru.kolchunov.sberver2.requests.CreateDictRequest;
import ru.kolchunov.sberver2.requests.UpdateDictRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Slf4j
@EnableTransactionManagement
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    DictionaryRepository dictionaryRepository;
    @Autowired
    StructureDictionaryRepository structureDictionaryRepository;
    @Autowired
    ValuesRepository valuesRepository;

    /**
     * Create new dictionary and his structure
     *
     * @param createDictRequest {@link CreateDictRequest}
     */
    @Override
    @Transactional
    public void saveNewStructure(CreateDictRequest createDictRequest) {
        log.info("IN DictionaryServiceImpl saveNewStructure {}", createDictRequest);

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

    }

    /**
     * Delete all structure of the dictionary
     *
     * @param idDictionary Id dictionary
     */
    @Override
    @Transactional
    public void deleteAllStructure(Long idDictionary) {
        log.info("IN DictionaryServiceImpl deleteAllStructure {}", idDictionary);

        //Session session = SessionFactoryConfig.getCurrentSessionFromConfig();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("select str.id from StructureDictionary str where idDictionary = " + idDictionary);
        List<Long> IdFields = query.list();

        IdFields.stream()
                    .forEach(x -> structureDictionaryRepository.deleteById(x));
        dictionaryRepository.deleteById(idDictionary);
    }

    /**
     * Delete structure by id of the field
     *
     * @param idField Id field
     */
    @Override
    @Transactional
    public void deleteFieldStructure(Long idField) {
        log.info("IN DictionaryServiceImpl deleteFieldStructure {}", idField);

        //Session session = SessionFactoryConfig.getCurrentSessionFromConfig();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("select v.id from Values v where v.idField = " + idField);
        List<Long> IdValues = query.list();

        IdValues.stream()
                    .forEach(x -> valuesRepository.deleteById(x));
        structureDictionaryRepository.deleteById(idField);
    }

    /**
     * Return structure of the dictionary by id
     *
     * @param idDictionary Id dictionary
     */
    @Override
    @Transactional
    public CreateDictRequest getAllStructureById(Long idDictionary) {
        log.info("IN DictionaryServiceImpl getAllStructureById {}", idDictionary);

        //Session session = SessionFactoryConfig.getCurrentSessionFromConfig();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        CreateDictRequest createDictRequest = new CreateDictRequest();
        Query query = session.createQuery("from Dictionary d where d.id = " + idDictionary);
        List<Dictionary> dictionaries = query.list();

        if (!dictionaries.isEmpty()) {
            createDictRequest.setCode(dictionaries.get(0).getCode());
            createDictRequest.setName(dictionaries.get(0).getName());

            query = session.createQuery("select str.name, str.dataType from StructureDictionary str where str.idDictionary = " + idDictionary);
            createDictRequest.setFieldsStructure(query.list());
        }
        return createDictRequest;
    }

    /**
     * Update structure of the dictionary
     *
     * @param updateDictRequest {@link UpdateDictRequest}
     */
    @Override
    @Transactional
    public void updateStructure(UpdateDictRequest updateDictRequest) {
        log.info("IN DictionaryServiceImpl updateStructure {}", updateDictRequest);

        Dictionary dictionary = dictionaryRepository.getById(updateDictRequest.getId());
        dictionary.setCode(updateDictRequest.getCode());
        dictionary.setName(updateDictRequest.getName());
        dictionaryRepository.save(dictionary);

        for (UpdateDictRequest.FieldStructure fieldStructure : updateDictRequest.getFieldsStructure()) {
            StructureDictionary structureDictionary = structureDictionaryRepository.findById(fieldStructure.getId()).orElse(null);
            if (structureDictionary == null) {
                structureDictionary = new StructureDictionary();
            }
            structureDictionary.setName(fieldStructure.getName());
            structureDictionary.setDataType(fieldStructure.getType());
            structureDictionaryRepository.save(structureDictionary);
        }
    }

}
