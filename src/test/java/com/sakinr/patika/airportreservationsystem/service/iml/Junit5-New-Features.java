package com.sakinr.patika.airportreservationsystem.service.iml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;



class ParameterizedTestSample {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testIsEven(int number) {
        Assertions.assertTrue(number % 2 == 0);
    }
}


class RepeatedTestSample {

    @RepeatedTest(5)
    public void testRepeatedly(TestReporter testReporter) {
        // Test logic here
        testReporter.publishEntry("Test repetition completed.");
    }
}


@Tag("development")
class TaggingAndFilteringSample {

    @Test
    @Tag("slow")
    void slowTest() {
        // Test logic here
    }

    @Test
    @Tag("fast")
    void fastTest() {
        // Test logic here
    }
}


class TestExceptionExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        // Custom exception handling logic here
        System.out.println("Test failed with exception: " + throwable.getMessage());
        throw throwable; // Re-throw the exception to mark the test as failed
    }
}


@ExtendWith(TestExceptionExtension.class)
class ExtensionSample {

    @Test
    void testWithCustomExtension() {
        // Test logic that may throw an exception
    }
}

