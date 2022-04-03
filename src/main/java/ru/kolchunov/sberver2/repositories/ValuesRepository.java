package ru.kolchunov.sberver2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolchunov.sberver2.models.Values;

/**
 * Repository interface for {@link Values}
 */
@Repository
public interface ValuesRepository extends JpaRepository<Values, Long>, SearchValuesRepository {
    void deleteAllByidRow(Long idRow);
}