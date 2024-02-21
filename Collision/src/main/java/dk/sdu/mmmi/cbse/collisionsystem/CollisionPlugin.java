package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class CollisionPlugin implements IGamePluginService {
    private Entity collision;
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        collision = createCollisionChecker(gameData);
        world.addEntity(collision);
    }

    private Entity createCollisionChecker(GameData gameData) {
        Entity collisionChecker = new Collision();
        collisionChecker.setHealth(10);
        collisionChecker.setRadius(Double.MIN_NORMAL);
        collisionChecker.setPolygonCoordinates(0, 0);
        collisionChecker.setX(Integer.MIN_VALUE);
        collisionChecker.setY(Integer.MIN_VALUE);
        return collisionChecker;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(collision);
    }
}
