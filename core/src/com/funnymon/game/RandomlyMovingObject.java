package com.funnymon.game;

import java.util.Random;

/**
 * Created by tarik on 3/19/17.
 */

class RandomlyMovingObject {
    private float originX;
    private float originY;
    private float x;
    private float y;
    private int movementBound = 80;
    private final static float MOVEMENT = 5;
    private Random r;
//    private int ran;
//    private float deltaTime;
    private float calculateDirection;
    private float directionX;
    private float directionY;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    RandomlyMovingObject(float x, float y) {
        this.x = x; // + movementBound/2;
        this.y = y; // + movementBound/2;
        this.originX = x;
        this.originY = y;
        r = new Random();
//        ran = 0;
//        deltaTime = 0f;
        calculateDirection = 0f;
        directionX = 0f;
        directionY = 0f;
    }

    RandomlyMovingObject(float x, float y, int movementBound){
        this.movementBound = movementBound;
        this.x = x; // + movementBound/2;
        this.y = y; // + movementBound/2;
        this.originX = x;
        this.originY = y;
        r = new Random();
//        ran = 0;
//        deltaTime = 0f;
        calculateDirection = 0f;
        directionX = 0f;
        directionY = 0f;
    }

    void move(float deltaTime) {
//        deltaTime = Gdx.graphics.getDeltaTime();
        float step = MOVEMENT*deltaTime;
        calculateDirection += step;
//        ran = r.nextInt(3);
        if (calculateDirection > MOVEMENT) {
            calculateDirection = 0;
            directionX = (r.nextInt(2) == 0) ? -1 : 1;
            directionY = (r.nextInt(2) == 0) ? -1 : 1;
//            directionX *= deltaTime;
//            directionY *= deltaTime;
        }
        this.x += directionX * step;
        this.y += directionY * step;

        if (this.originX > this.x) this.x = this.originX;
        if ((this.originX + movementBound) < this.x) this.x = this.originX + movementBound;
        if (this.originY > this.y) this.y = this.originY;
        if ((this.originY + movementBound) < this.y) this.y = this.originY + movementBound;
    }
}
