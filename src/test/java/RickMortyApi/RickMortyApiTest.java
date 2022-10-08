package RickMortyApi;

import org.junit.jupiter.api.*;

import static RickMortyApi.RickMortyApiSteps.*;

public class RickMortyApiTest {

    @Test
    @DisplayName("ТК 1. Нахождение информации о персонаже Morty Smith")
    public void Test1() {
        getInfoCharacterByName("Morty Smith");
    }

    @Test
    @DisplayName("ТК 2. Нахождение информации о последнем персонаже последнего эпизода")
    public void Test2() {
        getInfoCharacterByName("Morty Smith");
        getLastCharacterByEpisode(idLastEpisode);
        getInfoLastCharacterById(idLastCharacter);
    }

    @Test
    @DisplayName("ТК 3. Сравнение расы и местонахождения персонажей")
    public void Test3() {
        getInfoCharacterByName("Morty Smith");
        getLastCharacterByEpisode(idLastEpisode);
        getInfoLastCharacterById(idLastCharacter);
        compareCharacters();
    }
}
