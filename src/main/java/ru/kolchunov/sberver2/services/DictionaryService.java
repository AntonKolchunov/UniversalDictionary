package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.requests.CreateDictRequest;
import ru.kolchunov.sberver2.responses.DictionaryModel;

/**
 * Service for dictionary functionality
 */
public interface DictionaryService {

    /**
     * Create new dictionary and his structure
     *
     * @param createDictRequest {@link CreateDictRequest}
     */
    DictionaryModel create(CreateDictRequest createDictRequest);

    /**
     * Delete all structure of the dictionary
     *
     * @param id Id dictionary
     */
    void delete(Long id);

    /**
     * Return structure of the dictionary by id
     *
     * @param id Id dictionary
     */
    DictionaryModel find(Long id);
}
