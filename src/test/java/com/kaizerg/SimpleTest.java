package com.kaizerg;

import org.junit.jupiter.api.*;

public class SimpleTest {
    @Test
    void loginTest() {
        System.out.println("COMMON TEST");
    }

    @Test
    @Tag("web")
    void profileTest() {
        System.out.println("COMMON TEST");
    }

    @Tags({
            @Tag("smoke")
    })
    @DisplayName("Емайл должен отправляться после регистрации пользователя")
    @Test
    void sendEmailTest() {
        System.out.println("SMOKE TEST");
    }

    @WebSmokeTest
    /**
     * Protected constructor allowing subclassing but not direct instantiation.
     *
     * @since 5.3
     */
    void simpleTest() {
        System.out.println("WEB SMOKE TEST");
        Assertions.assertTrue(3 > 2); // Объяснение сложного кода
    }
}
