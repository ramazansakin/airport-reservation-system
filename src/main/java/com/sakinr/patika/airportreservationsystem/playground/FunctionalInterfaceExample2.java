package com.sakinr.patika.airportreservationsystem.playground;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

interface Doable {
    default void doIt() {
        System.out.println("Do it now");
    }
}

@FunctionalInterface
interface Sayable2 extends Doable {
    void say(String msg);   // abstract method
}

public class FunctionalInterfaceExample2 implements Sayable2 {
    public void say(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        FunctionalInterfaceExample2 fie = new FunctionalInterfaceExample2();
        fie.say("Hello there");
        fie.doIt();
    }
}


interface Test {

    static String sayTest() {
        return "Hi from Test - static!";
    }

    default String sayDefault() {
        return "Hi from Test - Default";
    }

}

class SubTest implements Test {

    @Override
    public String sayDefault() {
        return Test.super.sayDefault();
    }

    // cant override static interface method

}

class StaticTest {

    public static String test() {
        return "Sample static method from StaticTest class!";
    }

}

// Note: Static methods can't be overridden !!!
class SubStaticTest extends StaticTest {

    // Instance method 'test()' in 'com.sakinr.patika.airportreservationsystem.playground.SubStaticTest' cannot override
    // static method 'test()' in 'com.sakinr.patika.airportreservationsystem.playground.StaticTest'
//    public String test(){
//        return "Sample static method from StaticTest class!";
//    }

}


enum Color {
    RED, YELLOW, GREEN
}

class TestEnumMap {

    public static void main(String[] args) {
        Map<Color, Object> enumMap = new EnumMap<>(Color.class);

        enumMap.put(Color.GREEN, "Green");

    }

}