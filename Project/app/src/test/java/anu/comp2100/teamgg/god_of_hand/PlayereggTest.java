package anu.comp2100.teamgg.god_of_hand;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerEggTest {

    @Test
    public void changeEgg() {
        PlayerEgg playerEgg = new PlayerEgg(null, null, 1, 2, 3, 4, true, 6, 7);
        playerEgg.changeEgg(1);
        assertTrue(playerEgg.id == 1);
        playerEgg.changeEgg(2);
        assertTrue(playerEgg.id == 2);
        playerEgg.changeEgg(3);
        assertTrue(playerEgg.id == 3);
        playerEgg.changeEgg(0);
        assertTrue(playerEgg.id == 0);

    }

    @Test
    public void pressed() {
        PlayerEgg playerEgg = new PlayerEgg(null, null, 1, 2, 3, 4, true, 6, 7);
        playerEgg.pressed(1);
        assertTrue(playerEgg.pointerId == 1);
        assertTrue(playerEgg.isPress);
        playerEgg.pressed(2); // it will not change as long as ispressed flag is true. (make the program robust)
        assertTrue(playerEgg.pointerId == 1);
        assertTrue(playerEgg.isPress);
    }

    @Test
    public void unpressed() {
        PlayerEgg playerEgg = new PlayerEgg(null, null, 1, 2, 3, 4, true, 6, 7);
        playerEgg.pressed(1);
        assertTrue(playerEgg.pointerId == 1);
        assertTrue(playerEgg.isPress);
        playerEgg.pressed(2); // it will not change as long as ispressed flag is true. (make the program robust)
        assertTrue(playerEgg.pointerId == 1);
        assertTrue(playerEgg.isPress);
        playerEgg.unpressed(2); // if find the id does not match, then nothing will change
        assertTrue(playerEgg.pointerId == 1);
        playerEgg.unpressed(1);
        assertTrue(playerEgg.pointerId == -1);
        assertTrue(!playerEgg.isPress);

    }
}