package com.uzbConverter.UzbekCyrillicConverter.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uzbConverter.UzbekCyrillicConverter.api.modul.Alphabet;

import java.util.List;

public interface AlphabetRepository extends CrudRepository<Alphabet, Integer> {
    Alphabet findByLatin(String latin);
    Alphabet findByCyrillic(String cyrillic);
    List<Alphabet> findAllByCyrillic(String cyrillic);
}
