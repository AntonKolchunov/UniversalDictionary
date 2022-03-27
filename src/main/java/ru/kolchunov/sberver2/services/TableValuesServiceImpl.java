package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolchunov.sberver2.hibernate.HibernateUtil;
import ru.kolchunov.sberver2.models.*;
import ru.kolchunov.sberver2.repositories.DictionaryRepository;
import ru.kolchunov.sberver2.repositories.RowValuesRepositiry;
import ru.kolchunov.sberver2.repositories.ValuesRepository;
import ru.kolchunov.sberver2.requests.CreateDictReq;
import ru.kolchunov.sberver2.requests.InsertDictReq;
import ru.kolchunov.sberver2.requests.SearchDictReq;
import ru.kolchunov.sberver2.responses.SearchDictRes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TableValuesServiceImpl implements TableValuesService{

    @Autowired
    ValuesRepository valuesRepository;
    @Autowired
    RowValuesRepositiry rowValuesRepositiry;
    @Autowired
    DictionaryRepository dictionaryRepository;

    @Override
    public void save(InsertDictReq insertDictReq) {
        log.info("IN TableValuesServiceImpl save {}", insertDictReq);

        RowValues rowValues = new RowValues();
        rowValues.setIdDict(insertDictReq.getIdDict());
        rowValuesRepositiry.save(rowValues);

        for (InsertDictReq.FieldValue fieldValue : insertDictReq.getFieldValueList()) {

            Values values = new Values();

            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("select id from StructureDictionary where idDictionary = :paramIdDict and name = :paramName");
            query.setParameter("paramIdDict", insertDictReq.getIdDict());
            query.setParameter("paramName", fieldValue.getName());
            List list = query.list();
            session.close();

            values.setIdField((Long) list.get(0));

            values.setValue(fieldValue.getValue());
            values.setIdRow(rowValues.getId());
            valuesRepository.save(values);
        }
    }

        @Override
        public SearchDictRes searchByFields(SearchDictReq searchDictReq) {
            SearchDictRes searchDictRes = new SearchDictRes();
            Dictionary dictionary = dictionaryRepository.getById(searchDictReq.getIdDict());

            searchDictRes.setName(dictionary.getName());
            searchDictRes.setCode(dictionary.getCode());

            List<SearchDictReq.Parametrs> parametrsList = searchDictReq.getParametrsList();

            Session session = HibernateUtil.getSessionFactory().openSession();

            Query queryIdFieldToName = session.createQuery("from StructureDictionary where idDictionary = "+searchDictReq.getIdDict());
            List<StructureDictionary> structureDictionaryList = queryIdFieldToName.list();
            Map<String, Long> mapIdFieldToName = new HashMap<>();

            for (int i=0; i<structureDictionaryList.size(); i++){
                mapIdFieldToName.put(structureDictionaryList.get(i).getName(),structureDictionaryList.get(i).getId());
            }

            StringBuffer stringBufferQuery = new StringBuffer("select v.idRow, v.idField, str.name, v.value, str.dataType from RowValues r " +
                                                              "inner join Values v on r.idDict = "+ searchDictReq.getIdDict() +" and r.id = v.idRow " +
                                                              "inner join StructureDictionary str on str.id = v.idField");

            for (int i=0; i <parametrsList.size(); i++)
            {
                stringBufferQuery.append(" inner join Values v"+i+" on v.idRow = v"+i+".idRow and v"+i+".idField = '"+ mapIdFieldToName.get(parametrsList.get(i).getName())+"'"+
                                                        " and v"+i+".value "+conditionSearchToString(parametrsList.get(i).getCondition())+" '"+parametrsList.get(i).getValue()+ "'");
            }

            String selectQuery = stringBufferQuery.toString();
            Query query = session.createQuery(selectQuery);

            List<SearchDictRes.FieldValue> resultList = query.list();
            searchDictRes.setFieldValueList(resultList);

            session.close();
            return searchDictRes;
        }

    private String conditionSearchToString(SearchCondition searchCondition){
        String result = null;
        switch (searchCondition){
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
            case LESSOREQUAL:
                result = "<=";
                break;
            case GREATEROREQUAL:
                result = ">=";
                break;
        };
        return result;
    }

/*    @Autowired
    ValuesRepository valuesRepository;
    @Autowired
    StructureTableValuesConverter structureTableValuesConverter;


    @Override
    public StructureTableValuesDTO getById(Long id) {
        log.info("IN TableValuesServiceImpl getById {}", id);
        StructureTableValues structureTableValues = valuesRepository.findById(id).orElse(null);
        return structureTableValuesConverter.convertEntityToDTO(structureTableValues);
    }

    @Override
    public void save(StructureTableValuesDTO structureTableValuesDTO) {
        log.info("IN TableValuesServiceImpl save {}", structureTableValuesDTO);
        StructureTableValues structureTableValues = structureTableValuesConverter.dtoToEntity(structureTableValuesDTO);
        valuesRepository.save(structureTableValues);
    }

    @Override
    public void delete(Long id) {
        log.info("IN TableValuesServiceImpl delete {}", id);
        valuesRepository.deleteById(id);
    }

    @Override
    public List<StructureTableValuesDTO> getAll() {
        log.info("IN TableValuesServiceImpl getAll");
        List<StructureTableValues> dictionaryTableList = valuesRepository.findAll();
        return structureTableValuesConverter.entityToDTO(dictionaryTableList);
    }

    @Override
    public List<StructureTableValuesDTO> searchByFields(ParametrsForSearchDTO parametrsForSearchDTO) {

        return null;
    }*/
}
