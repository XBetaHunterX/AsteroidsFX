package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {
    public double noiseValue = Math.random();
    int health = 1;
}
