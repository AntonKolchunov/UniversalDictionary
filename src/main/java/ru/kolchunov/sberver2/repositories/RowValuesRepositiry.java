package ru.kolchunov.sberver2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolchunov.sberver2.models.RowValues;

@Repository
public interface RowValuesRepositiry extends JpaRepository<RowValues, Long> {
}
