package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kolchunov.sberver2.exceptions.NotFoundException;
import ru.kolchunov.sberver2.models.*;
import ru.kolchunov.sberver2.repositories.RowValuesRepositiry;
import ru.kolchunov.sberver2.repositories.StructureDictionaryRepository;
import ru.kolchunov.sberver2.repositories.ValuesRepository;
import ru.kolchunov.sberver2.requests.*;
import ru.kolchunov.sberver2.responses.FieldValue;

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
    StructureDictionaryRepository structureDictionaryRepository;

    /**
     * Insert new values int the Table of values
     *
     * @param insertDictRequest {@link InsertDictRequest}
     */
    @Override
    @Transactional
    public List<FieldValue> insert(InsertDictRequest insertDictRequest) {
        log.info("INSERT VALUES {}", insertDictRequest);

        Long dictionaryId = insertDictRequest.getIdDict();
        RowValues rowValues = new RowValues();
        rowValues.setIdDict(insertDictRequest.getIdDict());
        rowValuesRepositiry.save(rowValues);

        Map<String, StructureDictionary> fields = getDictionaryFields(dictionaryId);
        Map<Long, StructureDictionary> fieldsById = new HashMap<>(fields.size());

        for (InsertDictRequest.FieldValue fieldValue : insertDictRequest.getFieldsValue()) {
            String fieldName = fieldValue.getName();
            StructureDictionary field = fields.get(fieldName);
            if (field == null) {
                throw new NotFoundException("Field with name " + fieldName + " not found");
            }
            fieldsById.put(field.getId(), field);

            Values values = new Values();
            values.setIdField(field.getId());
            values.setValue(fieldValue.getValue());
            values.setIdRow(rowValues.getId());
            valuesRepository.save(values);
        }

        return searchRow(dictionaryId, rowValues.getId(), fieldsById);
    }

    /**
     * Search rows by fields
     *
     * @param searchDictRequest {@link SearchDictRequest}
     */
    @Override
    @Transactional
    public List<FieldValue> search(SearchDictRequest searchDictRequest) {
        log.info("SEARCH VALUES {}", searchDictRequest);

        Long dictionaryId = searchDictRequest.getIdDict();
        Map<String, StructureDictionary> fields = getDictionaryFields(dictionaryId);

        Map<Long, StructureDictionary> fieldsById = new HashMap<>(fields.size());
        for (Map.Entry<String, StructureDictionary> entryFields : fields.entrySet()){
            fieldsById.put(entryFields.getValue().getId(), entryFields.getValue());
        }

        return valuesRepository.searchByFields(searchDictRequest, fields)
                .map(value -> valueToFieldValue(value, fieldsById))
                .collect(Collectors.toList());

    }

    /**
     * Delete row by id
     *
     * @param idRow Id row
     */
    @Override
    @Transactional
    public void delete(Long idRow) {
        log.info("DELETE VALUES {}", idRow);

        valuesRepository.deleteAllByidRow(idRow);
        rowValuesRepositiry.deleteById(idRow);
    }

    private Map<String, StructureDictionary> getDictionaryFields(Long idDictionary) {
        return structureDictionaryRepository.findAllByIdDictionary(idDictionary)
                .collect(Collectors.toMap(StructureDictionary::getName, field -> field));
    }

    private List<FieldValue> searchRow(Long dictionaryId, Long rowId, Map<Long, StructureDictionary> fields) {
        return valuesRepository.searchByRowId(dictionaryId, rowId)
                .map(value -> valueToFieldValue(value, fields))
                .collect(Collectors.toList());
    }

    private FieldValue valueToFieldValue(Values value, Map<Long, StructureDictionary> fields) {
        StructureDictionary field = fields.get(value.getIdField());

        return FieldValue.builder()
                .idField(value.getIdField())
                .name(field.getName())
                .dataTypes(field.getDataType())
                .idRow(value.getIdRow())
                .value(value.getValue())
                .build();
    }

    private Map<String, Long> getDictionaryFieldsMappingNameToIdField(Long idDictionary) {
        return structureDictionaryRepository.findAllByIdDictionary(idDictionary)
                .collect(Collectors.toMap(StructureDictionary::getName, StructureDictionary::getId));
    }
}
