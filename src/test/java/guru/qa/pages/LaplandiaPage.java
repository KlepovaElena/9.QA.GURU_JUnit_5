package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.tests.TestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LaplandiaPage extends TestBase {

    SelenideElement
            searchInput = $(".h__search-input"),
            searchButton = $(".h__search-submit"),
            catalogHeader = $(".catalog__header"),
            catalogMenu = $(".h__menu-list"),
            catalogList = $(".catalog__cat-list"),
            languageButton = $(".var-wrp__header"),
            languageList = $(".var-wrp__list");

    public LaplandiaPage openPage() {
        open("https://laplandia.fi/");

        return this;
    }

    public LaplandiaPage setSearchItem(String value) {
        searchInput.setValue(value);

        return this;
    }

    public LaplandiaPage executeSearch() {
        searchButton.click();

        return this;
    }

    public LaplandiaPage checkSearchResult(String value) {
        catalogHeader.shouldHave(text(value));

        return this;
    }

    public LaplandiaPage selectCatalog(String value) {
        catalogMenu.hover().$(byText(value)).click();

        return this;
    }

    public LaplandiaPage checkCatalog(String value) {
        catalogMenu.shouldHave(text(value));

        return this;
    }

    public LaplandiaPage checkSubcatalog(String value) {
        catalogList.shouldHave(text(value));

        return this;
    }

    public LaplandiaPage changeLanguage(String value) {
        languageButton.click();
        languageList.$(byText(value)).click();

        return this;
    }

    public LaplandiaPage checkSubcatalogLacks(String value) {
        catalogList.shouldNotHave(text(value));

        return this;
    }

}
