package com.person.controllers.hateoas;

public enum WithRel {
    CREATE_PERSON("create new person"),
    UPDATE_PARTIALS_PERSON("update partials person"),
    GET_PERSON_BY_ID("get person by id"),
    GET_ALL_PERSONS("get all persons"),
    DELETE_PERSON_BY_ID("delete person by id"),
    SELF("self");

    private String description;

    WithRel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
