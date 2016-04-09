package fr.xebia.dd;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.assertj.core.api.Assertions.assertThat;

public class GameOverTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void should_end_game_north() {
        Dungeon dungeon = new Dungeon("" +
                "#E#\n" +
                "#P#\n" +
                "###"
        ).createPlayer("player");

        dungeon.up();

        assertThat(dungeon.isGameOver()).isTrue();
        assertThat(systemOutRule.getLog().split("\n")).containsExactly("Player moved up", "Game is over");
    }

    @Test
    public void should_end_game_south() {
        Dungeon dungeon = new Dungeon("" +
                "###\n" +
                "#P#\n" +
                "#E#"
        ).createPlayer("player");

        dungeon.down();

        assertThat(dungeon.isGameOver()).isTrue();
        assertThat(systemOutRule.getLog().split("\n")).containsExactly("Player moved down", "Game is over");
    }

    @Test
    public void should_end_game_east() {
        Dungeon dungeon = new Dungeon("" +
                "###\n" +
                "#PE\n" +
                "###"
        ).createPlayer("player");

        dungeon.right();

        assertThat(dungeon.isGameOver()).isTrue();
        assertThat(systemOutRule.getLog().split("\n")).containsExactly("Player moved right", "Game is over");
    }

    @Test
    public void should_end_game_west() {
        Dungeon dungeon = new Dungeon("" +
                "###\n" +
                "EP#\n" +
                "###"
        ).createPlayer("player");

        dungeon.left();

        assertThat(dungeon.isGameOver()).isTrue();
        assertThat(systemOutRule.getLog().split("\n")).containsExactly("Player moved left", "Game is over");
    }

    @Test
    public void should_not_move_when_player_is_stuck_to_the_wall() {
        Dungeon dungeon = new Dungeon("" +
                "###\n" +
                "EP#\n" +
                "###"
        ).createPlayer("player");

        dungeon.up();

        assertThat(systemOutRule.getLog()).isEmpty();
    }

    @Test
    public void should_not_move_when_game_is_over() {
        Dungeon dungeon = new Dungeon("" +
                "#E#\n" +
                "#P#\n" +
                "###"
        ).createPlayer("player").up();

        dungeon.up();

        assertThat(dungeon.isGameOver()).isTrue();
        assertThat(systemOutRule.getLog().split("\n")).containsExactly("Player moved up", "Game is over");
    }

    @Test
    public void should_works_with_big_dungeon() {
        Dungeon dungeon = new Dungeon("" +
                "###########\n" +
                "#         #\n" +
                "#       P #\n" +
                "#         #\n" +
                "E         #\n" +
                "#         #\n" +
                "###########"
        ).createPlayer("player");

        dungeon
                .up()
                .up() // can't goes up
                .right().down().left().down()
                .left().left().left().left().left().left().left()
                .left() // can't goes left
                .down()
                .left(); // exit

        assertThat(dungeon.isGameOver()).isTrue();
        assertThat(systemOutRule.getLog().split("\n")).containsExactly(
                "Player moved up",
                "Player moved right", "Player moved down", "Player moved left", "Player moved down",
                "Player moved left", "Player moved left", "Player moved left", "Player moved left", "Player moved left", "Player moved left", "Player moved left",
                "Player moved down",
                "Player moved left",
                "Game is over");
    }

}
