package com.hoarewu.learningcornor.course.math.problem;

import java.util.Random;

public class SubstractionProblem implements MathProblem {

	private Tuple minuends;

	public SubstractionProblem(int bound) {
		Random rand = new Random();
		int minuendLeft = rand.nextInt(bound);
		int minuendRight = rand.nextInt(bound);
		if (minuendLeft > minuendRight) {
			minuends = new Tuple(minuendLeft, minuendRight);
		} else {
			minuends = new Tuple(minuendRight, minuendLeft);
		}
	}

	@Override
	public Tuple getNumbers() {
		return minuends;
	}

	@Override
	public boolean checkAnswer(int answer) {
		int difference = minuends.left - minuends.right;
		return answer == difference;
	}
	
	@Override
	public String render() {
		return String.format("%d - %d = ", minuends.left, minuends.right);
	}
	
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubstractionProblem that = (SubstractionProblem) o;

        return minuends != null ? minuends.equals(that.minuends) : that.minuends == null;

    }

    @Override
    public int hashCode() {
        return minuends != null ? minuends.hashCode() : 0;
    }
}
