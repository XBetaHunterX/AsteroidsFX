package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class AsteroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        int totalAsteroids = 0;

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            Asteroid actualAsteroid = (Asteroid) asteroid;

            asteroid.setRotation(asteroid.getRotation() + actualAsteroid.rotationSpeed);

            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + actualAsteroid.speedX);
            asteroid.setY(asteroid.getY() + actualAsteroid.speedY);
            asteroid.setRotation(asteroid.getRotation() + actualAsteroid.rotationSpeed);

            // Use for splitting asteriods
//            if (Math.random() * 120.0 < 1.0) {
//                Collection<? extends BulletSPI> bulletSPIs = getBulletSPIs();
//                for (BulletSPI bulletSPI : bulletSPIs) {
//                    world.addEntity(bulletSPI.createBullet(enemy, gameData));
//                }
//            }

            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(1);
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(1);
            }

            totalAsteroids++;
        }

        // Spawn up to 5 enemies:
        int maxAsteroids = 10;
        for (int i = totalAsteroids; i < maxAsteroids; i++) {
            world.addEntity(createAsteroid(new Asteroid(), gameData));
        }
    }

    // Use for splitting asteroids
//    private Collection<? extends BulletSPI> getBulletSPIs() {
//        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
//    }

    public Entity createAsteroid(Entity e, GameData gameData) {
        Asteroid asteroid = (Asteroid) e;
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
        asteroid.setX(gameData.getDisplayHeight() * Math.random());
        asteroid.setY(gameData.getDisplayWidth() * Math.random());

        Entity entity = asteroid;
        return entity;
    }
}
