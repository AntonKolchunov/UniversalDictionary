package ru.kolchunov.sberver2.repositories;

import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.models.Values;
import ru.kolchunov.sberver2.requests.SearchDictRequest;

import java.util.Map;
import java.util.stream.Stream;

public interface SearchValuesRepository {
    Stream<Values> searchByRowId(Long dictionaryId, Long rowId);

    Stream<Values> searchByFields(SearchDictRequest searchDictRequest, Map<String, StructureDictionary> fields);
}
