package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YandexMarketPage {

    SelenideElement
            searchInput = $("#header-search"),
            searchFilters = $("#searchFilters");


    public YandexMarketPage openPage(){
        open("https://market.yandex.ru/");

        return this;
    }

    public YandexMarketPage setSearchItem(String value) {
        searchInput.setValue(value);

        return this;
    }

    public YandexMarketPage executeSearch() {
        searchInput.pressEnter();

        return this;
    }

    public YandexMarketPage checkSearchResult(String value) {
        $("._3RoU0").shouldHave(text(value));

        return this;
    }

    public YandexMarketPage checkSearchFilter(String value) {
        searchFilters.shouldHave(text(value));

        return this;
    }

    public YandexMarketPage checkSearchShouldNotHave(String value) {
        searchFilters.shouldNotHave(text(value));

        return this;
    }
}
