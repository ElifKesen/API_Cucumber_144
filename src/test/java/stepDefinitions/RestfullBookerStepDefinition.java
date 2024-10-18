package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.BaseUrl;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestfullBookerStepDefinition extends BaseUrl {

    Response response;

    @Given("Kullanici booking endpointine GET istegi yapar")
    public void kullanici_booking_endpointine_get_istegi_yapar() {
        response=given().spec(spec).when().get("/booking");
        response.prettyPrint();
    }

    @Then("Status kodunun {int} oldugunu dogrular")
    public void status_kodunun_oldugunu_dogrular(Integer statusCode) {
        Assert.assertEquals((int)statusCode,response.getStatusCode());
    }

    @Given("Kullanici ID'si {int} olan booking kaydini getirir")
    public void kullanici_id_si_olan_booking_kaydini_getirir(Integer bookingId) {
        response=given().spec(spec).when().get("/booking/"+bookingId);
        response.prettyPrint();
    }

    @Then("Status kodunun {int} oldugunu ve firstname'in {string} oldugunu dogrular")
    public void status_kodunun_oldugunu_ve_firstname_in_oldugunu_dogrular(Integer statusCode, String firstName) {
        Assert.assertEquals((int)statusCode,response.getStatusCode());
        Assert.assertEquals(firstName,response.jsonPath().getString("firstname"));
    }

    @Given("Kullanici yeni bir booking olusturur")
    public void kullanici_yeni_bir_booking_olusturur() {
        Map<String,Object>bookingData=new HashMap<>();
        bookingData.put("firstname","Ortanca");
        bookingData.put("lastname","Kartopu");
        bookingData.put("totalprice",150);
        bookingData.put("depositpaid",true);

        Map<String,Object>bookingDates=new HashMap<>();
        bookingDates.put("checkin","2024-10-01");
        bookingDates.put("checkout","2024-10-10");

        bookingData.put("bookingdates",bookingDates);
        bookingData.put("additionalneeds","Breakfast");

        //response
        response=given().spec(spec).contentType("application/json").body(bookingData)
                .when().post("/booking");
        response.prettyPrint();
    }
    @Then("Status kodunun {int} oldugunu ve booking ID'nin geldigini dogrular")
    public void status_kodunun_oldugunu_ve_booking_id_nin_geldigini_dogrular(Integer statusCode) {
        Assert.assertEquals((int)statusCode,response.getStatusCode());
        int bookingId=response.jsonPath().getInt("bookingid");
        Assert.assertTrue(bookingId>0);
    }

    @Given("Kullanici ID'si {int} olan booking kaydini gunceller")
    public void kullanici_id_si_olan_booking_kaydini_gunceller(Integer bookingId) {

        Map<String,Object>updateBooking=new HashMap<>();
        updateBooking.put("firstname","Deniz");
        updateBooking.put("lastname","Toprak");
        updateBooking.put("totalprice",200);
        updateBooking.put("depositpaid",false);

        Map<String,Object>bookingDates=new HashMap<>();
        bookingDates.put("checkin","2024-10-01");
        bookingDates.put("checkout","2024-10-10");

        updateBooking.put("bookingdates",bookingDates);
        updateBooking.put("additionalneeds","Dinner");

        response=given().spec(spec).auth().preemptive().basic("admin","password123")
                .contentType("application/json")
                .body(updateBooking)
                .when().put("/booking/"+bookingId);
        response.prettyPrint();

    }
    @Then("Status kodunun {int} oldugunu ve booking kaydinin guncellendigini dogrular")
    public void status_kodunun_oldugunu_ve_booking_kaydinin_guncellendigini_dogrular(Integer statusCode) {
        Assert.assertEquals((int)statusCode,response.getStatusCode());
        Assert.assertEquals("Deniz",response.jsonPath().getString("firstname"));
    }




 /*
    Preemptive Authentication, kimlik doğrulama bilgisini istek gönderilmeden önce
    sunucuya proaktif olarak eklememizi sağlar.
Normalde, bir sunucu önce kullanıcıdan bir istek alır ve ardından "401 Unauthorized" yanıtı ile
kimlik doğrulaması talep eder. Preemptive Authentication ile, sunucunun bu tür bir yanıt
döndürmesini beklemeden, kimlik doğrulama bilgisini istekle birlikte hemen gönderiyoruz.
Yani bu, gereksiz bir 401 hata yanıtı almadan direkt kimlik doğrulama bilgisini sunucuya ileterek işlemi hızlandırır.
     */


}
