package com.hoarewu.learningcornor.course.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by danielrosowski on 14.07.16.
 */
public class Levels {

    private static List<Level> levels = new ArrayList<Level>(
        Arrays.asList(
                new Level(1, 10),
                new Level(2, 20),
                new Level(3, 50),
                new Level(4, 100),
                new Level(5, 1000)));

    private static int levelNo = 3;
    public static int maxLevel = 5;

    public static void setLevelNo(int level) {
        if ((level > 0) & (level <= maxLevel))
            levelNo = level;
    }

    public static int getLevelNo() {
        return levelNo;
    }

    public static Level getCurrentLevel() {
        return levels.get(levelNo - 1);
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
