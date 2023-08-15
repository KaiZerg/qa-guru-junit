package com.kaizerg;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class moexSiteLocaleTest {
    static {
        Configuration.pageLoadStrategy = "eager";
    }
    static Stream<Arguments> wikipediaSiteLocaleTest() {
        return Stream.of(
                Arguments.of("Ru", List.of(" Продукты и услуги ", " Биржевая информация ", " Документы ", " Обучение ", " Медиа ", " О компании ")),
                Arguments.of("En", List.of("Markets", "Indices", "Market data", "Listing", "Connectivity", "News and events", "About MOEX", "Investor Relations"))
        );
    }
    @Tags({
            @Tag("smoke"), // BLOCKER
            @Tag("web")
    })
    @MethodSource("wikipediaSiteLocaleTest")
    @DisplayName("Язык навигации страницы меняется при смене локали")
    @ParameterizedTest(name = "Навигация с локализацией {0} содержит значения {1}")
    void wikipediaSiteLocaleTest(String locale, List<String> expectedButtons) {
        open("https://www.moex.com/en");
        $$(".lang-switch span").findBy(text(locale)).click();
        $$(".header__menu-wrapper a").shouldHave(texts(expectedButtons));

    }
}
