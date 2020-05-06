package filteres;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

public class FilterProducts {

    private String leftColumn = "#left_column";
    private String centerColumn = "#center_column";
    private String colorContainer = ".color-list-container";
    private String productContainer = ".product-container";

    public SelenideElement getFilterProductsBy(String filter, String idAttributeGroup) {
        return $(leftColumn)
                .$(idAttributeGroup)
                .$$("a")
                .filterBy(Condition.text(filter))
                .get(0);
    }

    public List<SelenideElement> getColorContainer() {
        return getCenterColumn()
                .$$(colorContainer);
    }

    public List<SelenideElement> getProductsContainer() {
        return getCenterColumn()
                .$$(productContainer);
    }

    public int getNumberProductsWereFiltered(SelenideElement webElement) {
        String productNumberOnFilter = webElement.$("span").getText();
        return Integer.parseInt(productNumberOnFilter.replace("(", "").replace(")", ""));
    }

    public SelenideElement getLoadingElement() {
        return getCenterColumn().$("p");
    }

    public List<SelenideElement> getListOfColorLinks(SelenideElement selenideElement) {
        return selenideElement.$$("a")
                .stream()
                .filter(webElement1 -> webElement1.getAttribute("href").contains("orange"))
                .collect(Collectors.toList());

    }

    private SelenideElement getCenterColumn() {
        return $(centerColumn);
    }

}
