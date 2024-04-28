import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredTestCases {

    Response response = RestAssured.get("https://api.coindesk.com/v1/bpi/currentprice.json");

    String globalURI = "https://api.coindesk.com/v1/bpi/currentprice.json";

   FetchingData fetchingData;


    @Test
    public void validateResponse(){
        Assert.assertEquals(response.statusCode(),200);

    }

    @Test
    public void validateOnTime(){

    LocalDateTime responseDateTime = LocalDateTime.parse(response.jsonPath().getString("time.updated"), DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss z"));
    LocalDateTime expectedDateTime = responseDateTime.atZone(ZoneId.of("UTC-2")).toLocalDateTime();
    given()
            .when()
            .get(globalURI)
            .then()
            .assertThat()
            .body("time.updated", equalTo(response.jsonPath().getString("time.updated")));


}

@Test
public void validateOnDisclaimer(){

given()
        .when()
        .get(globalURI)
        .then()
        .assertThat()
        .body("disclaimer",equalTo("This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org"));

}
@Test
    public void chartName(){

        given()
                .when()
                .get(globalURI)
                .then()
                .assertThat()
                .body("chartName",equalTo("Bitcoin"));

    }




    @Test
    public void bpiUSD(){
        given()
                .when()
                .get("https://api.coindesk.com/v1/bpi/currentprice.json")
                .then()
                .assertThat()
                .body("bpi.USD.code",equalTo("USD"))
                .and()
                .body("bpi.USD.symbol",equalTo("&#36;"))
                .and()
                .body("bpi.USD.description",equalTo("United States Dollar"))
                .and()
                .body("bpi.USD.rate_float",equalTo(fetchingData.fetchBitcoinFloatRate()))
                .and()
                .body("bpi.USD.rate",equalTo(fetchingData.fetchBitcoinRate()));


    }
    @Test
    public void bpiGBP(){

        given()
                .when()
                .get(globalURI)
                .then()
                .assertThat()
                .body("bpi.GBP.code",equalTo("GBP"))
                .and()
                .body("bpi.GBP.symbol",equalTo("&pound;"))
                .and()
                .body("bpi.GBP.description",equalTo("British Pound Sterling"));


    }
    @Test
    public void bpiEUR(){

        given()
                .when()
                .get(globalURI)
                .then()
                .assertThat()
                .body("bpi.EUR.code",equalTo("EUR"))
                .and()
                .body("bpi.EUR.symbol",equalTo("&euro;"))
                .and()
                .body("bpi.EUR.description",equalTo("Euro"));


    }




}
