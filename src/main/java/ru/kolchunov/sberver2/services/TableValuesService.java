package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.requests.InsertDictRequest;
import ru.kolchunov.sberver2.requests.SearchDictRequest;
import ru.kolchunov.sberver2.responses.SearchDictResponse;

/**
 * Service for values functionality
 */
public interface TableValuesService {

    /**
     * Insert new values int the Table of values
     *
     * @param insertDictRequest {@link InsertDictRequest}
     */
    void save(InsertDictRequest insertDictRequest);

    /**
     * Search rows by fields
     *
     * @param searchDictRequest {@link SearchDictRequest}
     */
    SearchDictResponse searchByFields(SearchDictRequest searchDictRequest);

    /**
     * Delete row by id
     *
     * @param id Id row
     */
    void delete(Long id);


    /*    void updateValues(UpdateDictValuesReq updateDictValuesReq);*/

}
