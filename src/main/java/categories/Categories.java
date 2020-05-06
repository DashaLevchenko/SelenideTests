package categories;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Categories {

    private String topMenu = "#block_top_menu";

    public void clickOnCategoryElement(String category) {
        $(topMenu)
        .$(By.tagName("ul"))
        .$$(By.tagName("li"))
         .filterBy(Condition.text(category.toUpperCase()))
         .get(0).findElement(By.tagName("a")).click();

    }
}
