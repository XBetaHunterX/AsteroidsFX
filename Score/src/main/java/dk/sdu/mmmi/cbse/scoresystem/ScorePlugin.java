package dk.sdu.mmmi.cbse.scoresystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class ScorePlugin implements IGamePluginService {
    private Entity score;

    public ScorePlugin() {
    }
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        score = createScore(gameData);
        world.addEntity(score);
    }

    private Entity createScore(GameData gameData) {
        Entity GameScore = new Score();
        GameScore.setHealth(10);
        GameScore.setRadius(0);
        GameScore.setPolygonCoordinates(0, 0);
        GameScore.setX(Integer.MAX_VALUE);
        GameScore.setY(Integer.MAX_VALUE);
        return GameScore;
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
