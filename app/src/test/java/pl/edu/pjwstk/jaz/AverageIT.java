package pl.edu.pjwstk.jaz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//test containers (w build.gradle) - narzędzie, które emuluje driver, ze jak sie probujemy polaczyc z baza, to ono samo za nas uruchamia kontener na jakims porcie losowym i podlacza baze danych do aplikacji springowej
//fajne, bo na nasz test jest uruchamiana osobna baza danych, po serii testow jest proces sprzatania, kontener i dane z testu sie niszcza
@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageIT {
    @Test
    public void when_no_params_supplied_should_print_a_message() {
        // @formatter:off
        given()
                .when()
                .get("/api/average")
                .then()
                .body("message", equalTo("Please put parameters."));
        // @formatter:on
    }

    @Test
    public void should_remove_trailing_zero() {
        // @formatter:off
        given()
                .when()
                .params("numbers", "1,2")
                .get("/api/average")
                .then()
                .body("message", equalTo("Average equals: 1.5"));
        // @formatter:on
    }

    /*
    http://localhost:8080/api/average?numbers=4,3,1,7,5 -> "Average equals: 4"
    http://localhost:8080/api/average?numbers=2,1 -> "Average equals: 1.5"
    http://localhost:8080/api/average?numbers=2,1,1 -> "Average equals: 1.33"
    http://localhost:8080/api/average -> "Please put parameters."
     */

    @Test
    public void should_calculate_avarage()
    {
        given()
                .param("numbers", "4,3,1,7,5")
                .when()
                .get("/api/average")
                .then()
                .body("message", equalTo("Average equals: 4"));
    }

    @Test
    public void should_not_remove()
    {
        given()
                .param("numbers", 2,1,1)
                .when()
                .get("/api/average")
                .then()
                .body("message", equalTo("Average equals: 1.33"));
    }

    @Test
    public void should_not_remove2()
    {
        given()
                .param("numbers", "2,1,1")
                .when()
                .get("/api/average")
                .then()
                .body("message", equalTo("Average equals: 1.33"));
    }

}
