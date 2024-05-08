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
                -Math.random() * asteroid.radius - 10 - 20 * Math.random(), -Math.random() * asteroid.radius - 10 - 20 * Math.random(), // Top left
                0, -Math.random() * asteroid.radius - 10 - 20 * Math.random(), // Y-axis negative
                Math.random() * asteroid.radius + 10 + 20 * Math.random(), -Math.random() * asteroid.radius - 10 - 20 * Math.random(), // Top right
                Math.random() * asteroid.radius + 10 + 20 * Math.random(), 0, // X-axis positive
                Math.random() * asteroid.radius + 10 + 20 * Math.random(), Math.random() * asteroid.radius + 10 + 20 * Math.random(), // Lower right
                0, Math.random() * asteroid.radius + 10 + 20 * Math.random(), // Y-axis positive
                -Math.random() * asteroid.radius - 10 - 20 * Math.random(), Math.random() * asteroid.radius + 10 + 20 * Math.random(), // Lower left
                -Math.random() * asteroid.radius - 10 - 20 * Math.random(), 0 // X-axis negative
        );
        asteroid.setHealth(4);
        asteroid.setRadius(asteroid.radius + 20);
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