package com.funnymon.game;


import com.badlogic.gdx.math.Rectangle;

/**
 * Created by tarik on 3/13/17.
 */

class Bullet {
    boolean visible;
    int x, y;
    Rectangle r;

    Bullet() {
        this.visible = true;
        this.x = 240;
        this.y = 210;
        r = new Rectangle(0, 0, 0, 0);
    }

    void update(float deltaTime) {
        y += 600 * deltaTime;
        r.set(236, y, 8, 16);
    }
}
