package com.funnymon.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by tarik on 3/13/17.
 */

public class Target {
    public int x,y;
    public boolean isVisible;
    boolean inRange, isShot;
    Rectangle r;
    int current;

    public Target(int x, boolean isVisible) {
        this.x = x;
        this.y = 705;
        this.isVisible = isVisible;
        inRange = false;
        isShot = false;
        r = new Rectangle(0, 0, 0, 0);
        current = 0;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
