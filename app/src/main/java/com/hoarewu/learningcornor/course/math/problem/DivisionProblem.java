package com.hoarewu.learningcornor.course.math.problem;

import java.util.Random;

public class DivisionProblem implements MathProblem {

	private Tuple dividends;

	public DivisionProblem(int bound) {
		Random rand = new Random();
		int dividendRight = rand.nextInt(bound);
		if (dividendRight == 0) {
			dividendRight++;
		}
		int dividendLeft = dividendRight * rand.nextInt(bound);
		dividends = new Tuple(dividendLeft, dividendRight);
	}

	@Override
	public Tuple getNumbers() {
		return dividends;
	}

	@Override
	public boolean checkAnswer(int answer) {
		int quotient = dividends.left / dividends.right;
		return answer == quotient;
	}
	
	@Override
	public String render() {
		return String.format("%d : %d = ", dividends.left, dividends.right);
	}
	
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DivisionProblem that = (DivisionProblem) o;

        return dividends != null ? dividends.equals(that.dividends) : that.dividends == null;

    }

    @Override
    public int hashCode() {
        return dividends != null ? dividends.hashCode() : 0;
    }	
}
