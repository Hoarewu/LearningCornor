package com.hoarewu.learningcornor.course.math;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielrosowski on 14.07.16.
 */
public class Levels {

    private List<Level> levels = new ArrayList<>();
    private int currentLevel = 5;

    public Levels() {
        levels.add(new Level(1, 10));
        levels.add(new Level(2, 10));
        levels.add(new Level(3, 20));
        levels.add(new Level(4, 20));
        levels.add(new Level(5, 50));
        levels.add(new Level(6, 50));
        levels.add(new Level(7, 100));
    }

    public Level getNextLevel() {
        Preconditions.checkState(hasNextLevel(), "There are no more levels!");
        currentLevel++;
        return levels.get(currentLevel);
    }

    public boolean hasNextLevel() {
        return currentLevel + 1 < levels.size();
    }

    public Level getLevel(int levelNo) {
        Preconditions.checkState(levelNo > 0, "Level to get most be a positive number!");
        currentLevel = levelNo - 1;
        return levels.get(currentLevel);
    }

    public static class Level {
        public final int bound;
        public final int index;

        private Level(int index, int bound) {
            this.index = index;
            this.bound = bound;
        }
    }
}
