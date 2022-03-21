package ru.kolchunov.sberver2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolchunov.sberver2.models.StructureDictionary;
import ru.kolchunov.sberver2.models.StructureDictionaryPK;

@Repository
public interface StructureDictionaryRepository extends JpaRepository<StructureDictionary, StructureDictionaryPK> {
}
