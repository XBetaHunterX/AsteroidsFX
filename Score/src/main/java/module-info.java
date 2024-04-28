import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Score {
    exports dk.sdu.mmmi.cbse.scoresystem;
    requires Common;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    provides IGamePluginService with dk.sdu.mmmi.cbse.scoresystem.ScorePlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.scoresystem.ScoreControlSystem;
}