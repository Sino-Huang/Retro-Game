package anu.comp2100.teamgg.god_of_hand;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovingItemsTest {

    @Test
    public void registerObservers() {
        MovingItems movingItems = new MovingItems(1, 2);
        movingItems.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        movingItems.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        movingItems.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        assertTrue(movingItems.observerList.size() == 3);
        MovingItems movingItems2 = new MovingItems(1, 2);
        movingItems2.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        movingItems2.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        assertTrue(movingItems2.observerList.size() == 2);
        MovingItems movingItems3 = new MovingItems(1, 2);
        assertTrue(movingItems3.observerList.size() == 0);

    }
}