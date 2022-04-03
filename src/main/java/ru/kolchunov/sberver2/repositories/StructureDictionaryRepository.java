package ru.kolchunov.sberver2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolchunov.sberver2.models.StructureDictionary;

import java.util.stream.Stream;

/**
 * Repository interface for {@link StructureDictionary}
 */
@Repository
public interface StructureDictionaryRepository extends JpaRepository<StructureDictionary, Long> {
    void deleteAllByIdDictionary(Long idDictionary);

    Stream<StructureDictionary> findAllByIdDictionary(Long idDictionary);
}
