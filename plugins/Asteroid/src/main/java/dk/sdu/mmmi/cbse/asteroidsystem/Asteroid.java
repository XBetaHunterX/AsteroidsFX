package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {
    int radius = (int)Math.random() * 30;
    double speedX = 1.5 - Math.random() * 3;
    double speedY = 1.5 - Math.random() * 3;
    double rotationSpeed = 1.5 - Math.random() * 3;
    boolean isSplit = false;
}