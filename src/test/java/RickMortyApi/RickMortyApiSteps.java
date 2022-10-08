package RickMortyApi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RickMortyApiSteps {

    public static String idLastEpisode;
    public static String mortyLocation;
    public static String mortySpecies;
    public static String idLastCharacter;
    public static String lastCharacterLocation;
    public static String lastCharacterSpecies;

    public static RequestSpecification request = given()

            .baseUri("https://rickandmortyapi.com/api/")
            .header("Content-type", "application/json");

    public static void getInfoCharacterByName(String name) {

        Response response1 = given().spec(request)
                .when()
                .get("character/?name=" + name)
                .then()
                .statusCode(200)
                .extract().response();

        String body = response1.body().jsonPath().getJsonObject("results[0]").toString();
        mortyLocation = response1.body().jsonPath().getJsonObject("results[0].location.name").toString();
        mortySpecies = response1.body().jsonPath().getJsonObject("results[0].species").toString();
        List<String> episodes = response1.body().jsonPath().getList("results[0].episode");
        String urlLastEpisode = episodes.get(episodes.size() - 1);
        String[] str = urlLastEpisode.split("/");
        idLastEpisode = str[str.length - 1];

        System.out.println("Информация о Morty Smith: " + body);
        System.out.println("Последний эпизод с Morty Smith: " + urlLastEpisode);
        System.out.println("Местонахождение Morty: " + mortyLocation);
        System.out.println("Раса Morty: " + mortySpecies);
    }

    public static void getLastCharacterByEpisode(String idLastEpisode) {

        Response response = given().spec(request)
                .when()
                .get("episode/" + idLastEpisode)
                .then()
                .statusCode(200)
                .extract().response();

        List<String> allLastEpisodeCharacters = response.body().jsonPath().getList("characters");
        String lastCharacter = allLastEpisodeCharacters.get(allLastEpisodeCharacters.size() - 1);
        String[] str = lastCharacter.split("/");
        idLastCharacter = str[str.length - 1];

        System.out.println("ID последнего персонажа из последнего эпизода: " + idLastCharacter);
    }

    public static void getInfoLastCharacterById(String idLastCharacter) {

        Response response2 = given().spec(request)
                .when()
                .get("character/" + idLastCharacter)
                .then()
                .statusCode(200)
                .extract().response();
        lastCharacterLocation = response2.body().jsonPath().getJsonObject("location.name").toString();
        lastCharacterSpecies = response2.body().jsonPath().getJsonObject("species").toString();
    }

    public static void compareCharacters() {

        System.out.println("Раса последнего персонажа: " + lastCharacterSpecies);
        System.out.println("Раса Морти: " + mortySpecies);
        assertEquals(mortySpecies, lastCharacterSpecies);

        System.out.println("Местонахождение последнего персонажа: " + lastCharacterLocation);
        System.out.println("Местонахождение Морти: " + mortyLocation);
        assertNotEquals(mortyLocation, lastCharacterLocation);
    }
}
