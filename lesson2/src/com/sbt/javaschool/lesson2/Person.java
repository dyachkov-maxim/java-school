package com.sbt.javaschool.lesson2;

public class Person {
    private final boolean man;
    private final String name;
    private Person spouse;

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
        spouse = null;
    }

    public String getName() {
        return name;
    }

    public boolean marry(Person person) {

        if ((this.man != person.man) && (spouse != person)) {

            divorce();
            person.divorce();

            spouse = person;
            person.spouse = this;

            return true;
        }

        return false;
    }

    public boolean divorce() {
        if (spouse != null) {
            spouse.spouse = null;
            spouse = null;
            return true;
        }

        return false;
    }

    boolean isPair(Person person) {
        return spouse == person;
    }
}
