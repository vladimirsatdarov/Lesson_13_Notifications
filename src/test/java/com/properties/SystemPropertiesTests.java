package com.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class SystemPropertiesTests {



    @Test
    void systemPropertiesTest() {
        String browser = System.getProperty("browser");

        System.out.println(browser); // null
    }

    @Test
    void systemProperties1Test() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser");

        System.out.println(browser); // chrome
    }

    @Test
    void systemProperties2Test() {
        String browser = System.getProperty("browser", "mozilla");

        System.out.println(browser); // mozilla
    }

    @Test
    void systemProperties3Test() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser", "mozilla");

        System.out.println(browser); // chrome
    }

    @Test
    @Tag("property")
    void systemProperties4Test() {
        String browser = System.getProperty("browser", "mozilla");

        System.out.println(browser);
        // gradle property_test
        // mozilla

        // gradle property_test -Dbrowser=opera
        // opera
    }

    @Test
    @Tag("hello")
    void systemProperties5Test() {
        String name = System.getProperty("name", "default student");
        String message = format("Hello, %s!", name);

        System.out.println(message);
        // gradle hello_test
        // Hello, default student!

        // gradle hello_test -Dname=Alex Egorov
        // BUILD FAILED: Task 'Egorov' not found in root project 'demoqa-tests-19'.

        // gradle hello_test -Dname="Alex Egorov"
        // gradle hello_test "-Dname=Alex Egorov"
        // Hello, Alex Egorov!

    }
}
