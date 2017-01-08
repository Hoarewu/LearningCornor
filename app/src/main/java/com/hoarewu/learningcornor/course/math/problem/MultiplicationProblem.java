package com.hoarewu.learningcornor.course.math.problem;

import java.util.Random;

public class MultiplicationProblem implements MathProblem {

    private Tuple factors;

    public MultiplicationProblem(int bound) {
        Random rand = new Random();
        int factorLeft = rand.nextInt(bound);
        int factorRight = rand.nextInt(bound);
        factors = new Tuple(factorLeft, factorRight);
    }
    
	@Override
	public Tuple getNumbers() {
		return factors;
	}

	@Override
	public boolean checkAnswer(int answer) {
		int product = factors.left * factors.right;
		return product == answer;
	}
	
	@Override
	public String render() {
		return String.format("%d * %d = ", factors.left, factors.right);
	}
	
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiplicationProblem that = (MultiplicationProblem) o;

        return factors != null ? factors.equals(that.factors) : that.factors == null;

    }

    @Override
    public int hashCode() {
        return factors != null ? factors.hashCode() : 0;
    }
}
