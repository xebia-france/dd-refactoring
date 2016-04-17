package fr.xebia.dd;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class FirstItemTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Parameters(name = "{1} should have {0}")
    public static Iterable<String[]> itemForPlayers() {
        return Arrays.asList(new String[][]{
                {"Medal", "Akuku"},
                {"Medal", "Zapuk"},
                {"Boots of Speed", "Kayin"},
                {"Boots of Speed", "Sofia"},
                {"Headgear Armor Item", "Jacob"},
                {"Headgear Armor Item", "Lynn"},
                {"Ring of Protection", "Thor"},
                {"Ring of Protection", "Jennie"},
                {"Ring of Fire Resistance", "Seth"},
                {"Ring of Spell Turning", "Ralph"},
                {"Ring of Spell Turning", "Jess"},
                {"Ring of Spell Turning", "Emily"},
                {"Gauntlets of Ogre Power", "Dan"},
                {"Gauntlets of Ogre Power", "Ann"},
                {"Anklet", "Duke"},
                {"Anklet", "Wendy"},
                {"Brooch", "Axel"},
                {"Brooch", "Zelda"},
                {"Orb", "Aaron"},
                {"Orb", "Rosa"}
        });
    }

    @Parameter
    public String itemName;

    @Parameter(1)
    public String playerName;

    @Test
    public void should_give_an_item_when_player_is_created() {
        new Dungeon("" +
                "###\n" +
                "EP#\n" +
                "###",
                playerName, 10, 20);

        assertThat(systemOutRule.getLog()).startsWith("" +
                "Player " + playerName + " has strength 10 with 20hp " +
                "wearing " + itemName);
    }

}