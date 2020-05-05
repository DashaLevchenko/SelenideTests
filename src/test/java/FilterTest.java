import categories.Categories;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.Waiter;
import constants.URL;
import filteres.FilterProducts;
import org.junit.jupiter.api.*;

//import categories.Categories;
import constants.FilterGroup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
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
        WebElement color = filterProducts.getFilterProductsBy("Orange", FilterGroup.COLOR);
        color.click();

        assertFalse(filterProducts.getLoadingElement().isDisplayed());

        int expectedProductNumber = filterProducts.getNumberProductsWereFiltered(color);
        int actualProductNumber = filterProducts.getProductsContainer().size();
        assertEquals(expectedProductNumber, actualProductNumber);

        List<WebElement> elements = filterProducts.getColorContainer();
        elements.forEach((webElement) -> {
            List<WebElement> listOfProductLinks = webElement.findElements(By.tagName("a"))
                    .stream()
                    .filter(webElement1 -> webElement1.getAttribute("href").contains("orange"))
                    .collect(Collectors.toList());
            assertFalse(listOfProductLinks.isEmpty());
        });
    }

    @Test
    @Order(1)
    void filterProductBySize() {
        WebElement size = filterProducts.getFilterProductsBy("S", FilterGroup.SIZE);
        size.click();

        assertFalse(filterProducts.getLoadingElement().isDisplayed());

        int expectedProductNumber = filterProducts.getNumberProductsWereFiltered(size);
        int actualProductNumber = filterProducts.getProductsContainer().size();
        assertEquals(expectedProductNumber, actualProductNumber);
    }
}
