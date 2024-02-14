package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService, EnemySPI {

    @Override
    public void process(GameData gameData, World world) {
        int totalEnemies = 0;

        for (Entity enemy : world.getEntities(Enemy.class)) {
            Enemy actualEnemy = (Enemy) enemy;

            enemy.setRotation(enemy.getRotation() + actualEnemy.noiseValue);

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);

            if (Math.random() * 120.0 < 1.0) {
                Collection<? extends BulletSPI> bulletSPIs = getBulletSPIs();
                for (BulletSPI bulletSPI : bulletSPIs) {
                    world.addEntity(bulletSPI.createBullet(enemy, gameData));
                }
            }

            if (enemy.getX() < 0) {
                enemy.setX(gameData.getDisplayWidth());
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(1);
            }

            if (enemy.getY() < 0) {
                enemy.setY(gameData.getDisplayHeight());
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(1);
            }

            totalEnemies++;
            softNoise(actualEnemy);
            enemy.setDamageTimer(enemy.getDamageTimer() - 1);

            if (enemy.getHealth() <= 0) {
                enemy.setPolygonCoordinates(0);
            }
        }

        // Spawn up to 5 enemies:
        int maxEnemies = 3;
        for (int i = totalEnemies; i < maxEnemies; i++) {
            world.addEntity(createEnemy(new Enemy(), gameData));
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public Entity createEnemy(Entity e, GameData gameData) {
        Entity enemyShip = new Enemy();
        enemyShip.setHealth(1);
        enemyShip.setRadius(7.5);
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayHeight() * Math.random());
        enemyShip.setY(gameData.getDisplayWidth() * Math.random());
        return enemyShip;
    }

    private void softNoise(Enemy e) {
        e.noiseValue += 0.5 - Math.random();

        if (e.noiseValue > 5) {
            e.noiseValue = 5;
        }

        if (e.noiseValue < -5) {
            e.noiseValue = -5;
        }
    }
}
