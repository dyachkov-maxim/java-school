package com.sbt.javaschool.lesson2;

public class Main {
    public static void main(String[] args) {
        Person personJohn = new Person(true, "John");
        Person personPol = new Person(true, "Pol");
        Person personElizabet = new Person(false, "Elizabet");

        System.out.println("----------------------------------------------------------------");
        printRelations(personJohn, personElizabet);
        triesToMarry(personJohn, personElizabet);
        personJohn.marry(personElizabet);
        printRelations(personJohn, personElizabet);

        System.out.println("----------------------------------------------------------------");
        triesToMarry(personPol, personElizabet);
        personPol.marry(personElizabet);
        printRelations(personJohn, personElizabet);
        printRelations(personPol, personElizabet);
        printRelations(personElizabet, personJohn);

        System.out.println("----------------------------------------------------------------");
        triesToMarry(personPol, personJohn);
        personPol.marry(personJohn);
        printRelations(personPol, personJohn);
        printRelations(personPol, personElizabet);
    }

    public static void printRelations(Person p1, Person p2) {
        System.out.println(String.format("%s isPair %s? %s", p1.getName(), p2.getName(), p1.isPair(p2) ? "yes" : "no"));
    }

    public static void triesToMarry(Person p1, Person p2) {
        System.out.println(String.format("Tries to marry %s and %s ...", p1.getName(), p2.getName()));
    }
}
