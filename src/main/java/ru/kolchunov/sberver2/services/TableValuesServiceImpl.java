package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kolchunov.sberver2.hibernate.HibernateUtil;
import ru.kolchunov.sberver2.hibernate.SessionFactoryConfig;
import ru.kolchunov.sberver2.models.*;
import ru.kolchunov.sberver2.models.Dictionary;
import ru.kolchunov.sberver2.repositories.DictionaryRepository;
import ru.kolchunov.sberver2.repositories.RowValuesRepositiry;
import ru.kolchunov.sberver2.repositories.ValuesRepository;
import ru.kolchunov.sberver2.requests.*;
import ru.kolchunov.sberver2.responses.SearchDictResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@EnableTransactionManagement
@Service
public class TableValuesServiceImpl implements TableValuesService {

    @Autowired
    ValuesRepository valuesRepository;
    @Autowired
    RowValuesRepositiry rowValuesRepositiry;
    @Autowired
    DictionaryRepository dictionaryRepository;

    /**
     * Insert new values int the Table of values
     *
     * @param insertDictRequest {@link InsertDictRequest}
     */
    @Override
    @Transactional
    public void save(InsertDictRequest insertDictRequest) {
        log.info("IN TableValuesServiceImpl save {}", insertDictRequest);
        //Session session = SessionFactoryConfig.getCurrentSessionFromConfig();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        RowValues rowValues = new RowValues();
        rowValues.setIdDict(insertDictRequest.getIdDict());
        rowValuesRepositiry.save(rowValues);

        for (InsertDictRequest.FieldValue fieldValue : insertDictRequest.getFieldsValue()) {
            Values values = new Values();

            Query query = session.createQuery("select id from StructureDictionary where idDictionary = :paramIdDict and name = :paramName");
            query.setParameter("paramIdDict", insertDictRequest.getIdDict());
            query.setParameter("paramName", fieldValue.getName());
            List list = query.list();

            values.setIdField((Long) list.get(0));
            values.setValue(fieldValue.getValue());
            values.setIdRow(rowValues.getId());
            valuesRepository.save(values);
        }
        session.getTransaction().commit();
    }

    /**
     * Search rows by fields
     *
     * @param searchDictRequest {@link SearchDictRequest}
     */
    @Override
    @Transactional
    public SearchDictResponse searchByFields(SearchDictRequest searchDictRequest) {
        //Session session = SessionFactoryConfig.getCurrentSessionFromConfig();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        SearchDictResponse searchDictRes = new SearchDictResponse();
        Dictionary dictionary = dictionaryRepository.getById(searchDictRequest.getIdDict());

        searchDictRes.setName(dictionary.getName());
        searchDictRes.setCode(dictionary.getCode());

        List<SearchDictRequest.SearchTerm> searchTerms = searchDictRequest.getSearchTerms();

        Query queryIdFieldToName = session.createQuery("from StructureDictionary str where idDictionary = " + searchDictRequest.getIdDict(), StructureDictionary.class);

        Map<String, Long> mapIdFieldToName = new HashMap<>();
        try {
            mapIdFieldToName = (Map<String, Long>) queryIdFieldToName.stream()
                    .collect(
                            Collectors.toMap(
                                    x -> ((StructureDictionary) x).getName(),
                                    x -> ((StructureDictionary) x).getId()
                            )
                    );
        }
        catch (ClassCastException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Name and Id of the StructureDictionary not cast to fields of the Map<nameField, idField> ");
        }

        StringBuffer stringBufferQuery = new StringBuffer("select v.idRow, v.idField, str.name, v.value, str.dataType from RowValues r " +
                "inner join Values v on r.idDict = " + searchDictRequest.getIdDict() + " and r.id = v.idRow " +
                "inner join StructureDictionary str on str.id = v.idField");

        int countJoin = 0;
        for (SearchDictRequest.SearchTerm searchTerm : searchTerms) {
            stringBufferQuery.append(" inner join Values v" + countJoin + " on v.idRow = v" + countJoin + ".idRow and v" + countJoin + ".idField = '" + mapIdFieldToName.get(searchTerm.getName()) + "'" +
                    " and v" + countJoin + ".value " + conditionSearchToString(searchTerm.getCondition()) + " '" + searchTerm.getValue() + "'");
            countJoin++;
        }

        String selectQuery = stringBufferQuery.toString();
        Query query = session.createQuery(selectQuery);

        List<SearchDictResponse.FieldValue> resultList = query.list();
        searchDictRes.setFieldsValue(resultList);
        return searchDictRes;
    }

    /**
     * Convert enum_condition in condition
     *
     * @param searchCondition Condition for search {@link SearchCondition}
     */
    private String conditionSearchToString(SearchCondition searchCondition) {
        String result = null;
        switch (searchCondition) {
            case LESS:
                result = "<";
                break;
            case LIKE:
                result = "LIKE";
                break;
            case EQUAL:
                result = "=";
                break;
            case GREATER:
                result = ">";
                break;
            case LESS_OR_EQUAL:
                result = "<=";
                break;
            case GREATER_OR_EQUAL:
                result = ">=";
                break;
        };
        return result;
    }

    /**
     * Delete row by id
     *
     * @param idRow Id row
     */
    @Override
    @Transactional
    public void delete(Long idRow) {
        log.info("IN TableValuesServiceImpl delete {}", idRow);
       // Session session = SessionFactoryConfig.getCurrentSessionFromConfig();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Query queryIdValues = session.createQuery("select v.id from Values v where v.idRow = " + idRow);
        List<Long> listId = queryIdValues.list();
        listId.stream()
                .forEach(idValue -> valuesRepository.deleteById(idValue));
        rowValuesRepositiry.deleteById(idRow);
    }


}
