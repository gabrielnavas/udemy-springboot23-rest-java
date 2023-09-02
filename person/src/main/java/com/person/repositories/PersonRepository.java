package com.person.repositories;

import com.person.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PersonRepository {

    private final List<Person> data = new ArrayList<>();

    public boolean add(Person person) {
        data.add(person);
        return true;
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

    public boolean delete(Person person) {
        return data.remove(person);
    }

    public boolean updateById(UUID id, Person person) {
        boolean updated = true;

        int indexPerson = data.indexOf(person);

        if (indexPerson == -1) {
            return updated = false;
        } else {
            Person personUpdated = new Person(
                    id,
                    person.getFirstname(),
                    person.getLastname(),
                    person.getUsername(),
                    person.getPassword(),
                    person.getEmail()
            );

            data.add(indexPerson, personUpdated);
        }

        return updated;
    }

}
