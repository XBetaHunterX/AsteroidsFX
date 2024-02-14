import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    exports dk.sdu.mmmi.cbse.collisionsystem;
    requires Common;
    provides IGamePluginService with dk.sdu.mmmi.cbse.collisionsystem.CollisionPlugin;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionControlSystem;
}