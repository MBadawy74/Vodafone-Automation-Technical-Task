package StepDef;

import Helpers.DriverActions;
import Helpers.DriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FlowStepDef {

    private String mobilePhone;
    private String passwordUser;
    private String searchItem;

    public FlowStepDef() throws IOException {
        String loginJsonContent = new String(Files.readAllBytes(Paths.get("src/main/resources/DataFile.json")));

        JSONArray jsonArray = new JSONArray(loginJsonContent);

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        mobilePhone = jsonObject.getString("MobilePhone");
        passwordUser = jsonObject.getString("Password");

        String searchJsonContent = new String(Files.readAllBytes(Paths.get("src/main/resources/SearchData.json")));
        JSONArray searchJsonArray = new JSONArray(searchJsonContent);
        JSONObject sJsonObject = searchJsonArray.getJSONObject(0);
        searchItem = sJsonObject.getString("search");


    }

    By loginIconInHope = By.xpath("//*[@id='userProfileMenu']");
    By mobileNumber = By.xpath("//*[@tabindex='1']");
    By password = By.xpath("//*[@tabindex='2']");
    By button = By.xpath("//*[@tabindex='4']");

    By cookies = By.xpath("//*[@id='onetrust-accept-btn-handler']");
    By firstItem = By.xpath("//*[@src='https://eshop.vodafone.com.eg/ecommerce/api/asset/content/iPhone13black_big.png?contextRequest=%7B%22forceCatalogForFetch%22:false,%22applicationId%22:%2201H5FECVAV4YWT0NGQKXEN1T51%22,%22tenantId%22:%225DF1363059675161A85F576D%22%7D']");
    By secondItem = By.xpath("//*[@src='https://eshop.vodafone.com.eg/ecommerce/api/asset/content/9559942281.png?contextRequest=%7B%22forceCatalogForFetch%22:false,%22applicationId%22:%2201H5FECVAV4YWT0NGQKXEN1T51%22,%22tenantId%22:%225DF1363059675161A85F576D%22%7D']");
    By addToCart = By.xpath("//*[@class='add-to-cart']");
    By getBackToHome = By.xpath("//*[@src='assets/icon-center/vf_icon.svg']");
    By searchIcon = By.xpath("//*[@title='Search for:']");
    By cartIcon = By.xpath("//*[@src='assets/icon-center/shopping-trolley.svg']");

    @Test
    @Given("User logged to the web site")
    public void user_logged_to_the_store() {


        WebDriverManager.edgedriver().setup();
        DriverHelper.getInstance().get("https://eshop.vodafone.com.eg/ar/");


        DriverActions.getInstance().click(loginIconInHope);
        DriverActions.getInstance().sendKeys(mobileNumber, mobilePhone);
        DriverActions.getInstance().sendKeys(password, passwordUser);
        DriverActions.getInstance().click(button);

        throw new io.cucumber.java.PendingException();
    }

    @Test
    @When("user select products to the cart")
    public void user_select_products_to_the_cart() throws InterruptedException {

        DriverHelper.getInstance().get("https://eshop.vodafone.com.eg/ar/");
        DriverHelper.getInstance().wait(3000);
        DriverActions.getInstance().click(cookies);
        DriverActions.getInstance().click(firstItem);
        DriverActions.getInstance().click(addToCart);
        DriverActions.getInstance().click(getBackToHome);
        DriverActions.getInstance().click(secondItem);
        DriverActions.getInstance().click(addToCart);
        DriverActions.getInstance().click(getBackToHome);
        throw new io.cucumber.java.PendingException();
    }

    @Test
    @And("user search for product to add")
    public void user_search_for_product_to_add() throws InterruptedException {

        DriverHelper.getInstance().get("https://eshop.vodafone.com.eg/ar/");
        DriverHelper.getInstance().wait(3000);
        DriverActions.getInstance().click(searchIcon);
        DriverActions.getInstance().sendKeys(searchIcon, searchItem);
        DriverActions.getInstance().click(addToCart);
        DriverActions.getInstance().click(getBackToHome);

        throw new io.cucumber.java.PendingException();
    }

    @Then("open the cart to check the order")
    public void open_the_cart_to_check_the_order() throws InterruptedException {
        DriverHelper.getInstance().get("https://eshop.vodafone.com.eg/ar/");
        DriverHelper.getInstance().wait(3000);

        DriverActions.getInstance().click(cartIcon);

        throw new io.cucumber.java.PendingException();
    }


}
