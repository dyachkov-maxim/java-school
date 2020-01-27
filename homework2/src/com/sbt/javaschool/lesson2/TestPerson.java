package com.sbt.javaschool.lesson2;

import org.junit.Assert;
import org.junit.Test;

public class TestPerson {

    @Test
    public void test0() {
        Person personJohn = new Person(true, "John");
        Person personPol = new Person(true, "Pol");
        Person personElizabet = new Person(false, "Elizabet");

        Assert.assertFalse(personJohn.isPair(personPol));
        Assert.assertFalse(personJohn.isPair(personElizabet));
        Assert.assertFalse(personJohn.isPair(personJohn));

        Assert.assertFalse(personPol.isPair(personJohn));
        Assert.assertFalse(personPol.isPair(personElizabet));
        Assert.assertFalse(personPol.isPair(personPol));

        Assert.assertFalse(personElizabet.isPair(personJohn));
        Assert.assertFalse(personElizabet.isPair(personPol));
        Assert.assertFalse(personElizabet.isPair(personElizabet));
    }

    @Test
    public void test1() {
        Person personJohn = new Person(true, "John");
        Person personElizabet = new Person(false, "Elizabet");

        Assert.assertFalse(personJohn.isPair(personElizabet));

        personJohn.marry(personElizabet);
        Assert.assertTrue(personJohn.isPair(personElizabet));
        Assert.assertTrue(personElizabet.isPair(personJohn));
    }

    @Test
    public void test2() {
        Person personJohn = new Person(true, "John");
        Person personPol = new Person(true, "Pol");
        Person personElizabet = new Person(false, "Elizabet");

        personJohn.marry(personElizabet);
        personPol.marry(personElizabet);

        Assert.assertTrue(personPol.isPair(personElizabet));
        Assert.assertFalse(personJohn.isPair(personElizabet));
    }

    @Test
    public void test3() {
        Person personJohn = new Person(true, "John");
        Person personPol = new Person(true, "Pol");
        Person personElizabet = new Person(false, "Elizabet");

        personJohn.marry(personElizabet);
        personPol.marry(personElizabet);
        personJohn.marry(personPol);

        Assert.assertFalse(personPol.isPair(personJohn));
        Assert.assertFalse(personJohn.isPair(personPol));
        Assert.assertTrue(personPol.isPair(personElizabet));
        Assert.assertFalse(personJohn.isPair(personElizabet));
    }

    @Test
    public void test4() {
        Person personJohn = new Person(true, "John");
        boolean expected = personJohn.marry(personJohn);

        Assert.assertFalse(personJohn.isPair(personJohn));
        Assert.assertFalse(expected);
    }
}
