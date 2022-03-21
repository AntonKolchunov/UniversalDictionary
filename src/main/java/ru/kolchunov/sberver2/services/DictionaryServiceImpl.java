package ru.kolchunov.sberver2.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolchunov.sberver2.models.Dictionary;
import ru.kolchunov.sberver2.repositories.DictionaryRepository;

import java.util.List;

@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService{

    @Autowired
    DictionaryRepository dictionaryRepository;

    @Override
    public Dictionary getById(Long id) {
        log.info("IN DictionaryServiceImpl getById {}", id);
        return dictionaryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Dictionary dictionary) {
        log.info("IN DictionaryServiceImpl save {}", dictionary);
        dictionaryRepository.save(dictionary);
    }

    @Override
    public void delete(Long id) {
        log.info("IN DictionaryServiceImpl delete {}", id);
        dictionaryRepository.deleteById(id);
    }

    @Override
    public List<Dictionary> getAll() {
        log.info("IN DictionaryServiceImpl getAll ");
        return dictionaryRepository.findAll();
    }
}
