package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;
import java.util.HashMap;

public class CollisionControlSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        Collection<Entity> entities = world.getEntities();

        for (Entity entity : entities) {
            if (entity.getDamageTimer() > 0 || entity.getClass().isInstance(Collision.class)) {
                continue; // Skip entities with damage timer or of Collision class
            }

            for (Entity otherEntity : entities) {
                if (entity != otherEntity && isCollision(entity, otherEntity)) {
                    entity.setHealth(entity.getHealth() - 1);
                    entity.setDamageTimer(60);
                    break; // No need to check further collisions for this entity
                }
            }
        }
    }

    private boolean isCollision(Entity entity, Entity otherEntity) {
        double distanceSquared = calculateDistanceSquared(entity, otherEntity);
        double combinedRadiusSquared = Math.pow(entity.getRadius() + otherEntity.getRadius(), 2);
        return distanceSquared <= combinedRadiusSquared;
    }

    private double calculateDistanceSquared(Entity entity, Entity otherEntity) {
        double dx = otherEntity.getX() - entity.getX();
        double dy = otherEntity.getY() - entity.getY();
        return dx * dx + dy * dy;
    }
}
