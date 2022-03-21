package ru.kolchunov.sberver2.services;

import ru.kolchunov.sberver2.models.Dictionary;

import java.util.List;

public interface DictionaryService {
    Dictionary getById(Long id);

    void save(Dictionary dictionary);

    void delete(Long id);

    List<Dictionary> getAll();
}
