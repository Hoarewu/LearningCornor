package com.hoarewu.learningcornor.course.math.problem;

public interface MathProblem {

	Tuple getNumbers();

	boolean checkAnswer(int answer);

	String render();
}