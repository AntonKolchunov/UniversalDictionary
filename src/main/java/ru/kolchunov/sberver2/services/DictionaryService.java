package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.requests.CreateDictRequest;
import ru.kolchunov.sberver2.requests.UpdateDictRequest;

/**
 * Service for dictionary functionality
 */
public interface DictionaryService {

    /**
     * Create new dictionary and his structure
     *
     * @param createDictRequest {@link CreateDictRequest}
     */
    void saveNewStructure(CreateDictRequest createDictRequest);

    /**
     * Delete all structure of the dictionary
     *
     * @param id Id dictionary
     */
    void deleteAllStructure(Long id);

    /**
     * Delete structure by id of the field
     *
     * @param id Id field
     */
    void deleteFieldStructure(Long id);

    /**
     * Return structure of the dictionary by id
     *
     * @param id Id dictionary
     */
    CreateDictRequest getAllStructureById(Long id);

    /**
     * Update structure of the dictionary
     *
     * @param updateDictRequest {@link UpdateDictRequest}
     */
    void updateStructure(UpdateDictRequest updateDictRequest);
}
