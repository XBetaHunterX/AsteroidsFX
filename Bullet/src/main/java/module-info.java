import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Bullet {
    exports dk.sdu.mmmi.cbse.bulletsystem;
    exports dk.sdu.mmmi.cbse;
    requires Common;
    requires CommonBullet;
    provides IGamePluginService with dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
    provides BulletSPI with dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
}