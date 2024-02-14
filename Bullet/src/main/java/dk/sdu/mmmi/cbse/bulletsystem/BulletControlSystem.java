package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    private float speed = 5;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            Bullet actualBullet = (Bullet) bullet;

            bullet.setX(bullet.getX() + Math.cos(Math.toRadians(bullet.getRotation())) * this.speed);
            bullet.setY(bullet.getY() + Math.sin(Math.toRadians(bullet.getRotation())) * this.speed);

            if (bullet.getX() < 0) {
                bullet.setX(gameData.getDisplayWidth());
            }

            if (bullet.getX() > gameData.getDisplayWidth()) {
                bullet.setX(1);
            }

            if (bullet.getY() < 0) {
                bullet.setY(gameData.getDisplayHeight());
            }

            if (bullet.getY() > gameData.getDisplayHeight()) {
                bullet.setY(1);
            }

            if (actualBullet.timer <= 0) {
                bullet.setPolygonCoordinates(0);
            }
            if (bullet.getHealth() <= 0) {
                bullet.setPolygonCoordinates(0);
            }

            actualBullet.timer--;
        }
    }

    @Override
    public Entity createBullet(Entity e, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setHealth(1);
        bullet.setDamageTimer(0);
        bullet.setRadius((Math.sqrt(Math.pow(2.0, 2.0) + Math.pow(2.0, 2.0)) + 2.0) / 2.0);
        bullet.setPolygonCoordinates(-2, -2, 2, -2, 2, 2, -2, 2);
        bullet.setX(e.getX() + Math.cos(Math.toRadians(e.getRotation())) * 6);
        bullet.setY(e.getY() + Math.sin(Math.toRadians(e.getRotation())) * 6);
        bullet.setRotation(e.getRotation());
        return bullet;
    }

    private void setShape(Entity entity) {
    }

}
