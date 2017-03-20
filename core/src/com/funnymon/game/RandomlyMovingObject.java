package com.funnymon.game;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by tarik on 3/19/17.
 */

public class RandomlyMovingObject {
    public float originX;
    public float originY;
    public float x;
    public float y;
    public int movementBound = 80;
    public final static float MOVEMENT = 5;
    Random r;
    private int ran;
    public float deltaTime;
    public float calculateDirection;
    public float directionX;
    public float directionY;

    public RandomlyMovingObject(float x, float y) {
        this.x = x; // + movementBound/2;
        this.y = y; // + movementBound/2;
        this.originX = x;
        this.originY = y;
        r = new Random();
        ran = 0;
        deltaTime = 0f;
        calculateDirection = 0f;
        directionX = 0f;
    }

    public RandomlyMovingObject(float x, float y, int movementBound){
        this.movementBound = movementBound;
        this.x = x; // + movementBound/2;
        this.y = y; // + movementBound/2;
        this.originX = x;
        this.originY = y;
        r = new Random();
        ran = 0;
        deltaTime = 0f;
        calculateDirection = 0f;
        directionX = 0f;
    }

    public void move() {
        deltaTime = Gdx.graphics.getDeltaTime();
        calculateDirection += MOVEMENT*deltaTime;
//        ran = r.nextInt(3);
        if (calculateDirection > MOVEMENT) {
            calculateDirection = 0;
            directionX = (r.nextInt(2) == 0) ? -MOVEMENT : MOVEMENT;
            directionY = (r.nextInt(2) == 0) ? -MOVEMENT : MOVEMENT;
            directionX *= deltaTime;
            directionY *= deltaTime;
        }
        this.x += directionX;
        this.y += directionY;

        if (this.originX > this.x) this.x = this.originX;
        if ((this.originX + movementBound) < this.x) this.x = this.originX + movementBound;
        if (this.originY > this.y) this.y = this.originY;
        if ((this.originY + movementBound) < this.y) this.y = this.originY + movementBound;
    }
}
