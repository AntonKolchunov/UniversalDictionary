package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.models.StructureDictionaryPK;

import java.util.List;

public interface StructureDictionaryService {
    StructureDictionary getById(StructureDictionaryPK structureDictionaryPK);

    void save(StructureDictionary structureDictionary);

    void delete(StructureDictionaryPK structureDictionaryPK);

    List<StructureDictionary> getAll();
}
