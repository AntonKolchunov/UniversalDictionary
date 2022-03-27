package ru.kolchunov.sberver2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolchunov.sberver2.models.StructureDictionary;

@Repository
public interface StructureDictionaryRepository extends JpaRepository<StructureDictionary, Long> {
}
