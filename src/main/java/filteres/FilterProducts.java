package filteres;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class FilterProducts {

    private By leftColumn = By.id("left_column");
    private By centerColumn = By.id("center_column");
    private By colorContainer = By.xpath("//div[@class='color-list-container']");
    private By productContainer = By.xpath("//div[@class='product-container']");

    public WebElement getFilterProductsBy(String filter, String idAttributeGroup) {
        return  $(leftColumn)
                .$(By.id(idAttributeGroup))
                .$$(By.tagName("a"))
                .filterBy(Condition.text(filter))
                .get(0);
    }

    public List<WebElement> getColorContainer() {
        return getCenterColumn()
                .findElements(colorContainer);
    }

    public List<WebElement> getProductsContainer() {
        return getCenterColumn()
                .findElements(productContainer);
    }

    public int getNumberProductsWereFiltered(WebElement webElement){
        String productNumberOnFilter = webElement.findElement(By.tagName("span")).getText();
        return Integer.parseInt(productNumberOnFilter.replace("(", "").replace(")", ""));
    }

    public WebElement getLoadingElement() {
        return getCenterColumn().findElement(By.tagName("p"));
    }

    private WebElement getCenterColumn(){
        return $(centerColumn);
    }

}
