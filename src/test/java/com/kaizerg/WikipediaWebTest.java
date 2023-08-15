package com.kaizerg;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WikipediaWebTest {
   static { Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {
        open("https://en.wikipedia.org/wiki/Main_Page");
    }

    @Order(1)
    @CsvSource(value = {
            "Java | Java",
            "JUnit | JUnit"
    },
            delimiter = '|')

    @Tags({
            @Tag("smoke"), // BLOCKER
            @Tag("web")
    })
    @DisplayName("Проверка поиска в wikipedia")
    @ParameterizedTest(name = "В поисковой выдаче wikipedia присутствует заголовок статьи {0} для запроса {1}")
    void successfulSearchTextTest(String post_header, String searchQuery) {
        $("[name=search]")
                .setValue(searchQuery)
                .pressEnter();

        $("[id=firstHeading]").shouldHave(text(post_header));
    }

    @Disabled("JIRASERVER-140259")
    @Order(2)
    @CsvFileSource(resources = "/successfulSearchTextTest.csv")
    @Tags({
            @Tag("smoke"), // BLOCKER
            @Tag("web")
    })
    @DisplayName("Проверка поиска в wikipedia")
    @ParameterizedTest(name = "В поисковой выдаче wikipedia присутствует заголовок статьи из CSV файла {0} для запроса {1}")
    void successfulSearchTextFromCsvTest(String post_header, String searchQuery) {
        $("[name=search]")
                .setValue(searchQuery)
                .pressEnter();

        $("[id=firstHeading]").shouldHave(text(post_header));
    }

    @Order(3)
    @ValueSource(
            strings = {"Java", "JUnit"}
    )

    @Tags({
            @Tag("smoke"), // BLOCKER
            @Tag("web")
    })
    @DisplayName("Проверка поиска в wikipedia")
    @ParameterizedTest(name = "Поисковая выдача wikipedia не пустая для запроса {0}")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("[name=search]")
                .setValue(searchQuery)
                .pressEnter();

        $$("[id=content] div").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
