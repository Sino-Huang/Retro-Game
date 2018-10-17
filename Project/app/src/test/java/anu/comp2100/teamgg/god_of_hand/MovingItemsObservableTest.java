package anu.comp2100.teamgg.god_of_hand;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovingItemsObservableTest {

    @Test
    public void registerObservers() {
        MovingItemsObservable movingItemsObservable = new MovingItemsObservable(1, 2);
        movingItemsObservable.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        movingItemsObservable.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        movingItemsObservable.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        assertTrue(movingItemsObservable.observerList.size() == 3);
        MovingItemsObservable movingItemsObservable2 = new MovingItemsObservable(1, 2);
        movingItemsObservable2.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        movingItemsObservable2.registerObservers(new MovingItem(null, null, 1, 2, 3, 4, 5, 7));
        assertTrue(movingItemsObservable2.observerList.size() == 2);
        MovingItemsObservable movingItemsObservable3 = new MovingItemsObservable(1, 2);
        assertTrue(movingItemsObservable3.observerList.size() == 0);

    }
}