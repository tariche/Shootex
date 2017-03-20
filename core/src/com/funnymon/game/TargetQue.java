package com.funnymon.game;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tarik on 3/13/17.
 */

public class TargetQue {
    List<Target> targets = new ArrayList<Target>(10);
    Random r = new Random();
    int success = 0;

    public TargetQue() {
        for (int i = 0; i < 10; i++) {
            targets.add(new Target(480 + i * 48, r.nextBoolean()));
        }
    }

    public void move(float deltaTime) {
        for (int i = 0; i < 10; i++) {
            Target target = targets.get(i);
            target.x -= 50 * deltaTime;
            target.r.set(target.x, 725, 46, 14);
            if (target.x == 0) {
                target.x = 480;
                target.isShot = false;
                target.isVisible = r.nextBoolean();
            }

            if (target.isVisible && target.x < 300 && !target.isShot) {
                target.inRange = true;
            }

            /*if (target.isShot) {
                ++target.current;
                if (target.current == 4) {
                    target.current = 0;
                    target.isVisible = false;
                    target.isShot = true;
                }
            }*/
        }
    }

    public boolean targetEcsaped() {
        for (int i = 0; i < 10; i++) {
            Target target = targets.get(i);
            if (target.isVisible && (target.x + 48) < 200) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollision(Bullet bullet) {
        for (int i = 0; i < 10; i++) {
            Target target = targets.get(i);
            if (target.inRange) {
                if (target.r.overlaps(bullet.r)) {
                    target.isShot = true;
                    if (Settings.soundEnabled) {
                        Assets.targetSnd.play(1);
                    }
                    target.isVisible = false;
                    target.inRange = false;
                    shot(target, bullet);
                    return true;
                }
            }
        }
        return false;
    }

    private void shot(Target target, Bullet bullet) {
        success = 0;
        int targetX = target.x;
        if (bullet.x > targetX - 1 && bullet.x < targetX + 46 + 1) { // testiraj
            success += 1;
            if (bullet.x > targetX + 8 && bullet.x < targetX + 38) {
                success += 1;
                if (bullet.x > targetX + 16 && bullet.x < targetX + 30) {
                    success += 1;
                }
            }
//            target.isShot = true;
//            target.isVisible = false;
        }
    }
}
