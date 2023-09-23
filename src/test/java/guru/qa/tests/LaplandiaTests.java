package guru.qa.tests;

import guru.qa.pages.LaplandiaPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class LaplandiaTests extends LaplandiaPage {

    LaplandiaPage laplandiaPage = new LaplandiaPage();

    @BeforeEach
    public void initEach() {
        laplandiaPage.openPage();
    }

    @Tag("web")
    @ValueSource(
            strings = {"Шоколад", "Печенье"}
    )

    @ParameterizedTest(name = "Проверка поиска товара {0}")
    void searchItemTest(String searchQuery) {
        laplandiaPage.setSearchItem(searchQuery)
                .executeSearch()
                .checkSearchResult(searchQuery);
    }

    @Tag("web")
    @CsvSource(value = {
            "РОЖДЕСТВО,     Рождественские сладости",
            "ОДЕЖДА И АКСЕССУАРЫ,     Аксессуары"
    })

    @ParameterizedTest(name = "В каталоге {0} присутствует подкаталог {1}")
    void searchItemsShouldHaveFiltersTest(String catalogName, String subcatalogName) {
        laplandiaPage.selectCatalog(catalogName)
                .checkSubcatalog(subcatalogName);
    }

    public static Stream<Arguments> searchItemsShouldHaveFilterValuesTest() {
        return Stream.of(
                Arguments.of("Suomi", "JOULU TAVARAT"),
                Arguments.of("English", "CHRISTMAS GOODS")
        );
    }

    @Tag("web")
    @MethodSource("searchItemsShouldHaveFilterValuesTest")
    @ParameterizedTest(name = "При смене языка {0} меняется меню {1}")
    void searchItemsShouldHaveFilterValuesTest(String language, String catalogName) {
        laplandiaPage.changeLanguage(language)
                .checkCatalog(catalogName);
    }


    @Tag("web")
    @CsvFileSource(resources = "/CatalogShouldNotHaveSubcatalog.csv")
    @ParameterizedTest(name = "В каталоге {0} отсутствует подкаталог {1}")
    void searchItemsShouldNotHaveFiltersTest(String catalogName, String subcatalogName) {
        laplandiaPage.selectCatalog(catalogName)
                .checkSubcatalogLacks(subcatalogName);
    }
}
