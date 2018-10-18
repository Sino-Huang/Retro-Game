package anu.comp2100.teamgg.god_of_hand;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayereggTest {

    @Test
    public void changeEgg() {
        Playeregg playeregg = new Playeregg(null, null, 1, 2, 3, 4, true, 6, 7);
        playeregg.changeEgg(1);
        assertTrue(playeregg.id == 1);
        playeregg.changeEgg(2);
        assertTrue(playeregg.id == 2);
        playeregg.changeEgg(3);
        assertTrue(playeregg.id == 3);
        playeregg.changeEgg(0);
        assertTrue(playeregg.id == 0);

    }

    @Test
    public void pressed() {
        Playeregg playeregg = new Playeregg(null, null, 1, 2, 3, 4, true, 6, 7);
        playeregg.pressed(1);
        assertTrue(playeregg.pointerid == 1);
        assertTrue(playeregg.ispress);
        playeregg.pressed(2); // it will not change as long as ispressed flag is true. (make the program robust)
        assertTrue(playeregg.pointerid == 1);
        assertTrue(playeregg.ispress);
    }

    @Test
    public void unpressed() {
        Playeregg playeregg = new Playeregg(null, null, 1, 2, 3, 4, true, 6, 7);
        playeregg.pressed(1);
        assertTrue(playeregg.pointerid == 1);
        assertTrue(playeregg.ispress);
        playeregg.pressed(2); // it will not change as long as ispressed flag is true. (make the program robust)
        assertTrue(playeregg.pointerid == 1);
        assertTrue(playeregg.ispress);
        playeregg.unpressed(2); // if find the id does not match, then nothing will change
        assertTrue(playeregg.pointerid == 1);
        playeregg.unpressed(1);
        assertTrue(playeregg.pointerid == -1);
        assertTrue(!playeregg.ispress);

    }
}