package ru.kolchunov.sberver2.repositories;

import org.springframework.stereotype.Repository;
import ru.kolchunov.sberver2.models.SearchCondition;
import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.models.Values;
import ru.kolchunov.sberver2.requests.SearchDictRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public class SearchValuesRepositoryImpl implements SearchValuesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stream<Values> searchByRowId(Long dictionaryId, Long rowId) {
        return entityManager.createQuery("select v from Values v " +
                        "inner join RowValues r on r.id = v.idRow " +
                        "where v.idRow = :rowId and r.idDict = :dictionaryId", Values.class)
                .setParameter("rowId", rowId)
                .setParameter("dictionaryId", dictionaryId)
                .getResultStream();
    }

    @Override
    public Stream<Values> searchByFields(SearchDictRequest searchDictRequest, Map<String, StructureDictionary> fields) {
        StringBuffer queryString = new StringBuffer("select v.idRow, v.idField, str.name, v.value, str.dataType from RowValues r " +
                "inner join Values v on r.idDict = :dictionaryId  and r.id = v.idRow " +
                "inner join StructureDictionary str on str.id = v.idField");

        int countJoin = 0;
        for (; countJoin < searchDictRequest.getSearchTerms().size(); countJoin++) {
            queryString.append("\\n inner join Values v" + countJoin + " on v.idRow = v" + countJoin + ".idRow " +
                    "and v" + countJoin + ".idField = :p_" + countJoin + "IdField " +
                    "and v" + countJoin + ".value :p_" + countJoin + "Condition :p_" + countJoin + "Value");
        }
        Query query = entityManager.createQuery(queryString.toString())
                .setParameter("dictionaryId", searchDictRequest.getIdDict());

        for (SearchDictRequest.SearchTerm searchTerm : searchDictRequest.getSearchTerms())
        {
            query.setParameter("p_" + countJoin + "IdField", fields.get(searchTerm.getName()));
            query.setParameter("p_" + countJoin + "Condition", conditionSearchToString(searchTerm.getCondition()));
            query.setParameter("p_" + countJoin + "Value", searchTerm.getValue());
            countJoin--;
        }

        return query.getResultStream();
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
        }

        return result;
    }
}
