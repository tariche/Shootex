package com.funnymon.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tarik on 3/13/17.
 */

public class World {

    TargetQue targetQue;
    List<Bullet> bullets = new ArrayList<Bullet>();
    public boolean gameOver = false;
    public boolean shot = false;
    public int score = 0;
//    public int speedFactor = 0;

    public World() {
        targetQue = new TargetQue();
    }

    public void update(float deltaTime) {
        targetQue.move(deltaTime);

        if (shot) {
            shot = false;
            bullets.add(new Bullet());
        }

        Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            if (bullet.y > 820) {
                gameOver = true;
                updateScore();
                Shootex.myRequestHandler.showAds(true);
            }
            if (targetQue.checkCollision(bullet)) {
                score += targetQue.success;
                bullet.visible = false;
//                if (score/10 > speedFactor) {
//                    targetQue.speedUpTargets();
//                    ++speedFactor;
//                }
            }
            if (bullet.visible) {
                bullet.update(deltaTime);
            } else
                iter.remove();
        }

        if (targetQue.targetEcsaped()) {
            gameOver = true;
            updateScore();
            Shootex.myRequestHandler.showAds(true);
        }

    }

    public void updateScore() {
        if (Settings.highScore < score) {
            Settings.highScore = score;
        }
    }
}
