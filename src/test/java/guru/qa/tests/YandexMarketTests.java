package guru.qa.tests;

import guru.qa.pages.YandexMarketPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

public class YandexMarketTests extends YandexMarketPage {

    YandexMarketPage yandexMarketPage = new YandexMarketPage();

    @BeforeEach
    public void initEach(){
        yandexMarketPage.openPage();
    }

    @Tag("web")
    @ValueSource (
        strings = {"Чайник", "Плита"}
    )
    @ParameterizedTest(name = "Проверка поиска товаров {0}")
    void searchItemTest(String searchQuery) {
        yandexMarketPage.setSearchItem(searchQuery)
                .executeSearch()
                .checkSearchResult(searchQuery);
    }

    @Tag("web")
    @CsvSource (value = {
            "Чайник,     Материал корпуса",
            "Плита,     Тип духовки"
    })
    @ParameterizedTest(name = "При поиске товара {0} присутствует фильтр {1}")
    void searchItemsShouldHaveFiltersTest(String searchQuery, String searchFilter) {
        yandexMarketPage.setSearchItem(searchQuery)
                .executeSearch()
                .checkSearchFilter(searchFilter);
    }

    static Stream<Arguments> searchItemsShouldHaveFilterValuesTest() {
        return Stream.of(
                Arguments.of("Платье", "Длина", List.of("до колена", "макси", "миди", "мини")),
                Arguments.of("Плита", "Тип", List.of("газовая", "комбинированная", "электрическая"))
        );
    }
    @Tag("web")
    @MethodSource
    @ParameterizedTest(name = "При поиске товара {0} в фильтре {1} присутствует значение {2}")
    void searchItemsShouldHaveFilterValuesTest(String searchQuery, String searchFilter, List<String> filterItems) {
        yandexMarketPage.setSearchItem(searchQuery)
                .executeSearch();
    }
    @Tag("web")
    @CsvFileSource(resources = "/searchItemsShouldNotHaveFilters.csv")
    @ParameterizedTest(name = "При поиске товара {0} отсутствует фильтр {1}")
    void searchItemsShouldNotHaveFiltersTest(String searchQuery, String searchFilter) {
        yandexMarketPage.setSearchItem(searchQuery)
                .executeSearch()
                .checkSearchShouldNotHave(searchFilter);
    }
}
