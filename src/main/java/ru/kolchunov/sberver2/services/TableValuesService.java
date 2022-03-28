package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.requests.InsertDictReq;
import ru.kolchunov.sberver2.requests.SearchDictReq;
import ru.kolchunov.sberver2.requests.UpdateDictValuesReq;
import ru.kolchunov.sberver2.responses.SearchDictRes;

/**
 * Service for values functionality
 */
public interface TableValuesService {

    /**
     * Insert new values int the Table of values
     * @param insertDictReq {@link InsertDictReq}
     */
    void save(InsertDictReq insertDictReq);

    /**
     * Search rows by fields
     * @param searchDictReq {@link SearchDictReq}
     */
    SearchDictRes searchByFields(SearchDictReq searchDictReq);

    /**
     * Delete row by id
     * @param id Id row
     */
    void delete(Long id);


/*    void updateValues(UpdateDictValuesReq updateDictValuesReq);*/

}
