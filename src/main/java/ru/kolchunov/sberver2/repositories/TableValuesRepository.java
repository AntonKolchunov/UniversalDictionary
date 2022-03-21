package ru.kolchunov.sberver2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolchunov.sberver2.models.TableValues;
import ru.kolchunov.sberver2.models.TableValuesPK;

@Repository
public interface TableValuesRepository extends JpaRepository<TableValues, TableValuesPK> {
}