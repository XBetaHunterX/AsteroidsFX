package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CollisionControlSystemTest {

    private CollisionControlSystem collisionControlSystem;
    private CollisionPlugin collisionPlugin;
    private World world;
    private GameData gameData;

    @BeforeEach
    public void setup() {
        collisionControlSystem = new CollisionControlSystem();
        collisionPlugin = new CollisionPlugin();
        world = new World();
        gameData = new GameData();

        // Initialize the plugin to add collision entities or other setup as needed
        collisionPlugin.start(gameData, world);
    }

    @Test
    public void testNoCollisionOccurs() {
        Entity entity1 = new Entity();
        entity1.setX(0);
        entity1.setY(0);
        entity1.setRadius(1.0);
        entity1.setHealth(10);
        entity1.setDamageTimer(0);

        Entity entity2 = new Entity();
        // Placed far apart to avoid collision
        entity2.setX(10.0);
        entity2.setY(10.0);
        entity2.setRadius(1.0);

        world.addEntity(entity1);
        world.addEntity(entity2);

        collisionControlSystem.process(gameData, world);

        assertEquals(10, entity1.getHealth(), "Entity1 should not have lost health.");
        assertEquals(0, entity1.getDamageTimer(), "Entity1's damage timer should not be set.");
    }

    @Test
    public void testCollisionOccurs() {
        Entity entity1 = new Entity();
        entity1.setX(0);
        entity1.setY(0);
        entity1.setRadius(1.0);
        entity1.setHealth(10);
        entity1.setDamageTimer(0);

        Entity entity2 = new Entity();
        // Close enough to collide
        entity2.setX(1.0);
        entity2.setY(1.0);
        entity2.setRadius(1.0);
        entity2.setHealth(10);

        world.addEntity(entity1);
        world.addEntity(entity2);

        collisionControlSystem.process(gameData, world);

        assertEquals(9, entity1.getHealth(), "Entity1 should have lost health due to collision.");
        assertTrue(entity1.getDamageTimer() > 0, "Entity1 should have a damage timer set due to collision.");
    }

    @Test
    public void testEntityWithDamageTimer() {
        Entity entity1 = new Entity();
        entity1.setX(0);
        entity1.setY(0);
        entity1.setRadius(1.0);
        entity1.setHealth(10);
        entity1.setDamageTimer(60); // Entity starts with a damage timer

        Entity entity2 = new Entity();
        entity2.setX(1.0);
        entity2.setY(1.0);
        entity2.setRadius(1.0);
        entity2.setHealth(10);
        entity2.setDamageTimer(60); // Entity starts with a damage timer

        world.addEntity(entity1);
        world.addEntity(entity2);

        collisionControlSystem.process(gameData, world);

        assertEquals(10, entity1.getHealth(), "Entity should not lose health while it has a damage timer.");
    }

    @Test
    public void testIsCollision() {
        // Setup
        // Access the private method
        Method isCollisionMethod = null;
        try {
            isCollisionMethod = CollisionControlSystem.class.getDeclaredMethod("isCollision", Entity.class, Entity.class);

            // Make it accessible
            isCollisionMethod.setAccessible(true);

            Entity entity1 = new Entity();
            entity1.setX(0);
            entity1.setY(0);
            entity1.setRadius(1.0);

            Entity entity2 = new Entity();
            // Close enough to collide
            entity2.setX(1.0);
            entity2.setY(1.0);
            entity2.setRadius(1.0);

            Entity entity3 = new Entity();
            entity3.setX(0);
            entity3.setY(0);
            entity3.setRadius(1.0);

            Entity entity4 = new Entity();
            // CDistant enough to not collide
            entity4.setX(10.0);
            entity4.setY(10.0);
            entity4.setRadius(1.0);

            assertTrue((Boolean) isCollisionMethod.invoke(collisionControlSystem, entity1, entity2), "Method should have returned true as entities should collide.");
            assertFalse((Boolean) isCollisionMethod.invoke(collisionControlSystem, entity3, entity4), "Method should have returned false as entities should not collide.");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCalculateDistanceSquared() {
        // Setup
        // Access the private method
        Method calculateDistanceSquaredMethod = null;
        try {
            calculateDistanceSquaredMethod = CollisionControlSystem.class.getDeclaredMethod("calculateDistanceSquared", Entity.class, Entity.class);

            // Make it accessible
            calculateDistanceSquaredMethod.setAccessible(true);

            Entity entity1 = new Entity();
            entity1.setX(0);
            entity1.setY(0);

            Entity entity2 = new Entity();
            entity2.setX(3.0);
            entity2.setY(4.0);

            // Distance squared between (0,0) and (3,4) is 3^2 + 4^2 = 25
            assertEquals(25.0, calculateDistanceSquaredMethod.invoke(collisionControlSystem, entity1, entity2));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
