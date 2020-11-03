package pl.edu.pjwstk.jaz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.given;
//test containers (w build.gradle) - narzędzie, które emuluje driver, ze jak sie probujemy polaczyc z baza, to ono samo za nas uruchamia kontener na jakims porcie losowym i podlacza baze danych do aplikacji springowej
//fajne, bo na nasz test jest uruchamiana osobna baza danych, po serii testow jest proces sprzatania, kontener i dane z testu sie niszcza
@RunWith(SpringRunner.class)
@IntegrationTest
public class ExampleTest {
    @Test
    public void should_respond_to_readiness_request() {
        var response = given()
                .when()
                .get("/api/is-ready")
                .then()
                .statusCode(200);
    }
}
