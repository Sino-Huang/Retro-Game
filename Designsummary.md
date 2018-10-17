# Design summary
## Date
15/10/2018


1. The design is a classical object-oriented design. 
2. It uses interfaces and abstract classes (e.g. Item.java) to achieve dependency inversion and low coupling. 
3. Moreover, it uses Facade design pattern in data loading and saving section. It helps to provide a simplified Application Programming Interface (API) to a complex module and also helps to reduece dependency between classes.
4. The LoadAndSaveFacade.java also uses Factory design pattern. The static method in the LoadAndSaveFacade class is reponsible for constructing the object. As such, GameView class does not need to use "new" operator to create LoadAndSaveFacade object. This helps to reduce dependency. 
5. Additionally, the project uses Observer design pattern to control multiple moving items in the GameView (i.e. MovingItemsObservable class). The advantage of using Observer design pattern in this situation is that different items that implement observer interface can all be notified of an event
6. Progammers is able to easily create/design new types of props or stones to the project as long as the new item implements the "item interface" provided in the project. 
7. The design maintains a relatively low number of classes, meanwhile each class's function are highly cohesive. Having fewer rather than more classes is often preferred by modellers unless the extra classes are really necessary. 