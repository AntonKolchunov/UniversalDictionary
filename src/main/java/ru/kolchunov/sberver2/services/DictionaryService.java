package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.requests.CreateDictReq;
import ru.kolchunov.sberver2.requests.UpdateDictReq;

/**
 * Service for dictionary functionality
 */
public interface DictionaryService {

    /**
     * Create new dictionary and his structure
     * @param createDictReq {@link CreateDictReq}
     */
    void saveNewStructure(CreateDictReq createDictReq);

    /**
     * Delete all structure of the dictionary
     * @param id  Id dictionary
     */
    void deleteAllStructure(Long id);

    /**
     * Delete structure by id of the field
     * @param id  Id field
     */
    void deleteFieldStructure(Long id);

    /**
     * Return structure of the dictionary by id
     * @param id  Id dictionary
     */
    CreateDictReq getAllStructureById(Long id);

    /**
     * Update structure of the dictionary
     * @param updateDictReq  {@link UpdateDictReq}
     */
    void updateStructure(UpdateDictReq updateDictReq);
}
