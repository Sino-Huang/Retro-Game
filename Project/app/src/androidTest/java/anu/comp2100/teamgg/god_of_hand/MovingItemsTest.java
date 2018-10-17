package anu.comp2100.teamgg.god_of_hand;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovingItemsTest {

    @Test
    public void registerStone() {
        MovingItems movingItems = new MovingItems(120, 120);
        assertTrue(movingItems.screenHeight == 120);
        assertTrue(movingItems.screenWidth == 120);
        movingItems.registerStone(new MovingItem(null, null, 1, 1, 1, 1, 1, 1));
        assertTrue(movingItems.size() == 1);
        assertTrue(movingItems.get(0).ID == 0);
    }

    @Test
    public void run() {
    }
}