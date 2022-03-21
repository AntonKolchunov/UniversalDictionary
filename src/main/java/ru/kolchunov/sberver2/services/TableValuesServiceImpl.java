package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolchunov.sberver2.models.TableValues;
import ru.kolchunov.sberver2.models.TableValuesPK;
import ru.kolchunov.sberver2.repositories.TableValuesRepository;

import java.util.List;

@Slf4j
@Service
public class TableValuesServiceImpl implements TableValuesService{

    @Autowired
    TableValuesRepository tableValuesRepository;

    @Override
    public TableValues getById(TableValuesPK tableValuesPK) {
        log.info("IN TableValuesServiceImpl getById {}", tableValuesPK);
        return tableValuesRepository.getById(tableValuesPK);
    }

    @Override
    public void save(TableValues tableValues) {
        log.info("IN TableValuesServiceImpl save {}", tableValues);
        tableValuesRepository.save(tableValues);
    }

    @Override
    public void delete(TableValuesPK tableValuesPK) {
        log.info("IN TableValuesServiceImpl delete {}", tableValuesPK);
        tableValuesRepository.deleteById(tableValuesPK);
    }

    @Override
    public List<TableValues> getAll() {
        log.info("IN TableValuesServiceImpl getAll");
        return tableValuesRepository.findAll();
    }

/*    @Override
    public List<TableValues> searchByFields(Long id, String... fields) {
        log.info("IN TableValuesServiceImpl searchByFields");

        return tableValuesRepository.findBy();
    }*/
}
