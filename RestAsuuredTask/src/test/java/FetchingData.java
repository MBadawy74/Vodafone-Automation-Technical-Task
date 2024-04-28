import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FetchingData {
    String currenciesUpdatedURI = "https://openexchangerates.org/api/latest.json?app_id=2a0ceabe7f8b4645b25782087ca99b80";


    public float fetchBitcoinFloatRate() {
        RestAssured.baseURI = "https://api.coindesk.com/v1/bpi/currentprice";
        Response response = given()
                .get("/USD.json")
                .then()
                .extract()
                .response();
        response.then().statusCode(200);
        String bitcoinPrice = response.jsonPath().getString("bpi.USD.rate_float");
        System.out.println("Current Bitcoin Prive: " + bitcoinPrice);
        return Float.parseFloat(bitcoinPrice);
    }

    public String fetchBitcoinRate() {
        RestAssured.baseURI = "https://api.coindesk.com/v1/bpi/currentprice";
        Response response = given()
                .get("/USD.json")
                .then()
                .extract()
                .response();
        response.then().statusCode(200);
        String bitcoinRate = response.jsonPath().getString("bpi.USD.rate");
        System.out.println("Current Bitcoin Rate: " + bitcoinRate);
        return  bitcoinRate;
    }


    public float fetchingEuroPrice(){

        Response response= given()
                .when()
                .get(currenciesUpdatedURI)
                .then()
                .extract()
                .response();
        float priceOfEUR = response.jsonPath().getFloat("rates.EUR");
        System.out.println("Current Euro Price: " + priceOfEUR);
        return priceOfEUR;
    }

    public float fetchingGBPPrice(){

        Response response = given()
                .when()
                .get(currenciesUpdatedURI)
                .then()
                .extract()
                .response();
        float priceOfGBP = response.jsonPath().getFloat("rates.GBP");
        System.out.println("Current GBP Price: " + priceOfGBP);
        return priceOfGBP;
    }


    public float convertEurtoBitcoin() {

        float floatRateEUR = fetchingEuroPrice() * fetchBitcoinFloatRate();
        System.out.println("Euro to Bitcoin Price: " + floatRateEUR);
        return floatRateEUR;
    }

    public float convertGBPBitcoin(){

        float floatRateGBP = fetchingGBPPrice() * fetchBitcoinFloatRate();
        System.out.println("GBP to Bitcoin Price: " + floatRateGBP);
        return floatRateGBP;
    }




}
