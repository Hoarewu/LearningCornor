package com.hoarewu.learningcornor.course.math;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hoarewu.learningcornor.MainActivity;
import com.hoarewu.learningcornor.LearningCornor;
import com.hoarewu.learningcornor.R;
import com.hoarewu.learningcornor.course.math.Levels;
import com.hoarewu.learningcornor.course.math.problem.MathProblem;
import com.hoarewu.learningcornor.course.math.problem.ProblemFactory;
import com.hoarewu.learningcornor.util.ToastRenderer;

public class MathMainActivity extends AppCompatActivity {

    private static final int NUM_ROUNDS = 3;
    private final ToastRenderer toast;
    private ProblemFactory problemFactory;
    private SharedPreferences sharedPref;

    private Levels levels;
    private MathProblem currentProblem;
    private Levels.Level currentLevel;
    private int currentRound = 0; 
    private int correctNum =0;
    private int savedLevel = 0;
    private String problemClass;

    public MathMainActivity() {
        this.toast = new ToastRenderer();
        this.levels = new Levels();
        this.problemFactory = new ProblemFactory();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathproblem);
        this.problemClass = getIntent().getStringExtra(MainActivity.PROBLEM_CLASS);
        sharedPref = getPreferences(Context.MODE_PRIVATE);

        currentRound = sharedPref.getInt(getString(R.string.current_round) + problemClass, 0);
        savedLevel = sharedPref.getInt(getString(R.string.current_level) + problemClass, 0);

        if (currentRound == 0 && savedLevel == 0) {
            gotoNextLevel();
        } else {
            currentLevel = levels.getLevel(savedLevel);
            currentProblem = problemFactory.createFor(problemClass, currentLevel.bound);
            renderProblem();
        }
    }

    private void renderProgress() {
        TextView level = (TextView) findViewById(R.id.currentLevel);
        level.setText(String.format("级别 %d", currentLevel.index));
        TextView progress = (TextView) findViewById(R.id.progress);
        progress.setText(String.format("完成 %d 总 %d", currentRound, NUM_ROUNDS));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        gotoNextLevel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveProgress();
    }

    private void gotoNextLevel() {
        if (!levels.hasNextLevel()) {
            return;
        }
        currentLevel = levels.getNextLevel();
        showNextProblem();
    }

    public void showNextProblem() {

        if (currentRound == NUM_ROUNDS) {
            toast.show(getApplicationContext(), "挑战完成！");
            currentRound = 0;

            final LearningCornor app = (LearningCornor)getApplication();

            //Calculate the energy points
            if (correctNum == NUM_ROUNDS)
                    app.incEnergyPoints(3);
            else if (correctNum == (NUM_ROUNDS - 1)) {
                app.incEnergyPoints(1);
            }

            //Intent intent = new Intent(this, MainActivity.class)
            //Intent intent = new Intent(this, RewardActivity.class);
            // startActivity(intent);
            this.finish();
            return;
        }
        currentRound++;
        currentProblem = problemFactory.createFor(problemClass, currentLevel.bound);
        renderProblem();
    }

    private void saveProgress() {
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("LeveL" + problemClass, currentLevel.index);
        prefEditor.putInt("Round" + problemClass, currentRound);
        prefEditor.commit();
    }

    private void renderProblem() {
        TextView problem = (TextView) findViewById(R.id.problem);
        problem.setText(currentProblem.render());
        EditText answerField = (EditText) findViewById(R.id.answer);
        answerField.setText("");
        renderProgress();
    }

    public void checkAnswer(View view) {
        int answer = getAnswer();
        if (answer == -1) {
            return;
        }
        if (currentProblem.checkAnswer(answer)) {
            toast.show(getApplicationContext(), this.getString(R.string.answer_correct));
            correctNum  +=  1;
            showNextProblem();
        } else {
            toast.show(getApplicationContext(), "错误");
            showNextProblem();
            //renderProblem();
        }
    }

    private int getAnswer() {
        EditText answerField = (EditText) findViewById(R.id.answer);
        Editable answerText = answerField.getText();
        int answer = -1;
        try {
            answer = Integer.valueOf(answerText.toString());
        } catch (NumberFormatException ex) {
            toast.show(getApplicationContext(), this.getString(R.string.answer_missing));
        }

        return answer;
    }
}
