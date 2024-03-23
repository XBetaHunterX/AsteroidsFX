package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionPluginTest {

    private CollisionPlugin collisionPlugin;
    private World world;
    private GameData gameData;

    @BeforeEach
    public void setUp() {
        collisionPlugin = new CollisionPlugin();
        world = new World();
        gameData = new GameData();
    }

    @Test
    public void testStartAddsCollisionEntityToWorld() {
        collisionPlugin.start(gameData, world);
        assertEquals(1, world.getEntities().size(), "World should contain one entity after plugin start.");

        // Assert that entity is of type collision
        Entity addedEntity = world.getEntities().iterator().next();
        assertInstanceOf(Collision.class, addedEntity, "The added entity should be of type Collision.");

        // Check that attributes are set correctly
        assertEquals(10, addedEntity.getHealth(), "The added entity should have 10 health");
        assertEquals(0, addedEntity.getRadius(), "The added entity should have a radius of 0");
        assertEquals(Integer.MIN_VALUE, addedEntity.getX(), "The added entity should have an 'X' value of " + Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, addedEntity.getY(), "The added entity should have an 'Y' value of " + Integer.MIN_VALUE);
    }

    @Test
    public void testStopRemovesCollisionEntityFromWorld() {
        collisionPlugin.start(gameData, world);
        collisionPlugin.stop(gameData, world);
        assertTrue(world.getEntities().isEmpty(), "World should have no entities after plugin stop.");
    }
}
