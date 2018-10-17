package anu.comp2100.teamgg.god_of_hand;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void run() {
        Item item = new Item(null, null, 1, 2, 3, 4, 1);
        assertTrue(Math.abs(item.x - 1) < 0.01);
        assertTrue(Math.abs(item.y - 2 )< 0.01);
        assertTrue(Math.abs(item.width - 3) < 0.01);
        assertTrue(Math.abs(item.length - 4 )< 0.01);
        assertTrue(Math.abs(item.x - 1) < 0.01);
        assertEquals(item.col, 1);
    }
}