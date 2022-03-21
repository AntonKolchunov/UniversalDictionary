package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.models.TableValues;
import ru.kolchunov.sberver2.models.TableValuesPK;

import java.util.List;

public interface TableValuesService {
    TableValues getById(TableValuesPK tableValuesPK);

    void save(TableValues tableValues);

    void delete(TableValuesPK tableValuesPK);

    List<TableValues> getAll();

   /* List<TableValues> searchByFields(Long id, String ... fields);*/
}
