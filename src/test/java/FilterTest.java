import categories.Categories;
import com.codeborne.selenide.SelenideElement;
import constants.FilterGroup;
import constants.URL;
import filteres.FilterProducts;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilterTest {
    FilterProducts filterProducts = new FilterProducts();

    @BeforeAll
    static void clickOnCategory() {
        open(URL.BASE_URL);
        new Categories().clickOnCategoryElement("dresses");
    }

    @AfterAll
    static void closeBrowser() {
        closeWindow();
    }

    @Test
    @Order(2)
    void filterProductByColor() {
        SelenideElement color = filterProducts.getFilterProductsBy("Orange", FilterGroup.COLOR);
        color.click();

        assertFalse(filterProducts.getLoadingElement().isDisplayed());

        int expectedProductNumber = filterProducts.getNumberProductsWereFiltered(color);
        int actualProductNumber = filterProducts.getProductsContainer().size();
        assertEquals(expectedProductNumber, actualProductNumber);

        filterProducts.getColorContainer().forEach(selenideElement ->
                assertFalse(filterProducts.getListOfColorLinks(selenideElement).isEmpty()));
    }

    @Test
    @Order(1)
    void filterProductBySize() {
        SelenideElement size = filterProducts.getFilterProductsBy("S", FilterGroup.SIZE);
        size.click();

        assertFalse(filterProducts.getLoadingElement().isDisplayed());

        int expectedProductNumber = filterProducts.getNumberProductsWereFiltered(size);
        int actualProductNumber = filterProducts.getProductsContainer().size();
        assertEquals(expectedProductNumber, actualProductNumber);
    }
}
