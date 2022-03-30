package ru.kolchunov.sberver2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolchunov.sberver2.models.Dictionary;

/**
 * Repository interface for {@link Dictionary}
 */
@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> { ;
}
