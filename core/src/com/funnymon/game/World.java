package com.funnymon.game;

import java.util.ArrayList;
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

    public World() {
        targetQue = new TargetQue();
    }

    public void update(float deltaTime) {
        if (gameOver) {
            return;
        }

        targetQue.move(deltaTime);

        if (shot) {
            shot = false;
            bullets.add(new Bullet());
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (bullet.y > 820) {
                gameOver = true;
                updateScore();
                Shootex.myRequestHandler.showAds(true);
            }
            if (targetQue.checkCollision(bullet)) {
                score += targetQue.success;
                bullet.visible = false;
            }
            if (bullet.visible) {
                bullet.update(deltaTime);
            } else
                bullets.remove(i);
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
