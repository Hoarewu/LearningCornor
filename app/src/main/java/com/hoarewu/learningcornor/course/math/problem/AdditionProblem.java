package com.hoarewu.learningcornor.course.math.problem;

import java.util.Random;

/**
 * Created by danielrosowski on 13.07.16.
 */
public class AdditionProblem implements MathProblem {

    private Tuple summands;

    public AdditionProblem(int bound) {
        Random rand = new Random();
        int summandLeft = rand.nextInt(bound);
        int summandRight = rand.nextInt(bound);
        summands = new Tuple(summandLeft, summandRight);
    }

    /* (non-Javadoc)
	 * @see com.rosowski.mathpet.MathProblem#getNumbers()
	 */
    @Override
	public Tuple getNumbers() {
        return summands;
    }

    /* (non-Javadoc)
	 * @see com.rosowski.mathpet.MathProblem#checkAnswer(int)
	 */
    @Override
	public boolean checkAnswer(int answer) {
        int sum = summands.left + summands.right;
        return answer == sum;
    }

    @Override
    public String render() {
    	return String.format("%d + %d = ", summands.left, summands.right);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdditionProblem that = (AdditionProblem) o;

        return summands != null ? summands.equals(that.summands) : that.summands == null;

    }

    @Override
    public int hashCode() {
        return summands != null ? summands.hashCode() : 0;
    }
}
