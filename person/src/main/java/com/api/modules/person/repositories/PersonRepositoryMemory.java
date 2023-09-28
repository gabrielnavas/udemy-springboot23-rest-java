package com.api.modules.person.repositories;

import com.api.modules.person.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Component
public class PersonRepositoryMemory {

    private List<Person> data = new ArrayList<>();

    public void save(Person person) {
        data.add(person);
    }

    public void updatePartials(UUID id, Person person) {
        person.setId(id);
        Function<Person, Person> findAndUpdate = personMap -> personMap.getId().compareTo(id) == 0
                ? person
                : personMap;
        data = data.stream()
                .map(findAndUpdate)
                .toList();
    }

    public List<Person> getAll() {
        return data.stream().toList();
    }

    public Optional<Person> getById(UUID id) {
        Person person = null;

        for (Person p : data) {
            if (p.getId().equals(id)) {
                person = p;
                break;
            }
        }

        if (person == null) {
            return Optional.empty();
        }
        return Optional.of(person);
    }

    public Optional<Person> getByEmail(String email) {
        Person person = null;

        for (Person p : data) {
            if (p.getEmail().equals(email)) {
                person = p;
                break;
            }
        }

        if (person == null) {
            return Optional.empty();
        }
        return Optional.of(person);
    }

    public Optional<Person> getByUsername(String username) {
        Person person = null;

        for (Person p : data) {
            if (p.getUsername().equals(username)) {
                person = p;
                break;
            }
        }

        if (person == null) {
            return Optional.empty();
        }
        return Optional.of(person);
    }

    public void delete(Person person) {
        data.remove(person);
    }
}
