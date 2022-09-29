package com.sakinr.patika.airportreservationsystem.playground;

import com.sakinr.patika.airportreservationsystem.model.Address;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface Sayable {
    void say(String msg);   // abstract method

    // It can contain any number of Object class methods.
    int hashCode();

    String toString();


}

public class FunctionalInterfaceExample implements Sayable {
    public void say(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        FunctionalInterfaceExample fie = new FunctionalInterfaceExample();
        System.out.println(fie);
        fie.say("Hello there");

        // Test List of Object comparison
        List<Address> addresses1 = new ArrayList<>();
        addresses1.add(new Address("Istanbul", "Sancaktepe"));
        addresses1.add(new Address("Ankara", "Cumhuriyet"));

        List<Address> addresses2 = new ArrayList<>();
        addresses2.add(new Address("Ankara", "Cumhuriyet"));
        addresses2.add(new Address("Istanbul", "Sancaktepe"));

        // order is also needs to be equal
        if (addresses1.equals(addresses2)) {
            System.out.println("Addresses are equal");
        }

    }
}
