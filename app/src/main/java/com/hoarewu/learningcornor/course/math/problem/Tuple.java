package com.hoarewu.learningcornor.course.math.problem;

/**
 * Created by danielrosowski on 13.07.16.
 */
public class Tuple {

    public final int left;
    public final int right;

    public Tuple(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (left != tuple.left) return false;
        return right == tuple.right;

    }

    @Override
    public int hashCode() {
        int result = left;
        result = 31 * result + right;
        return result;
    }
}
