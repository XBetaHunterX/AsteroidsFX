package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    private Entity asteriod;

    public AsteroidPlugin() {

    }
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        asteriod = createAsteroid(gameData);
        world.addEntity(asteriod);
    }

    private Entity createAsteroid(GameData gameData) {
        Asteroid asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(
                -Math.random() * asteroid.radius + 5, -Math.random() * asteroid.radius + 5, // Top left
                0, -Math.random() * asteroid.radius + 5, // Y-axis negative
                Math.random() * asteroid.radius + 5, -Math.random() * asteroid.radius + 5, // Top right
                Math.random() * asteroid.radius + 5, 0, // X-axis positive
                Math.random() * asteroid.radius + 5, Math.random() * asteroid.radius + 5, // Lower right
                0, Math.random() * asteroid.radius + 5, // Y-axis positive
                -Math.random() * asteroid.radius, Math.random() * asteroid.radius + 5, // Lower left
                -Math.random() * asteroid.radius + 5, 0 // X-axis negative
        );
        asteroid.setX(gameData.getDisplayHeight()/2);
        asteroid.setY(gameData.getDisplayWidth()/2);

        Entity entity = asteroid;
        return entity;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Asteroid.class) {
                world.removeEntity(e);
            }
        }
    }
}
