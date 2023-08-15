package com.kaizerg;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Tags({
        @Tag("smoke"),
        @Tag("web")
})
@Test
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebSmokeTest {
}
