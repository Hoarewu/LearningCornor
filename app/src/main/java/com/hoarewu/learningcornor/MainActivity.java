package com.hoarewu.learningcornor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hoarewu.learningcornor.course.math.MathMainActivity;
import com.hoarewu.learningcornor.course.math.problem.AdditionProblem;
import com.hoarewu.learningcornor.course.math.problem.DivisionProblem;
import com.hoarewu.learningcornor.course.math.problem.MultiplicationProblem;
import com.hoarewu.learningcornor.course.math.problem.SubstractionProblem;
import com.hoarewu.learningcornor.course.math.reward.GalleryActivity;
import com.hoarewu.learningcornor.game.AirBattle.AirBattleActivity;
import com.hoarewu.learningcornor.util.ToastRenderer;

public class MainActivity extends AppCompatActivity {

    public static final String PROBLEM_CLASS = "problem_class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LearningCornor app = (LearningCornor)getApplication();
        final TextView txtview = (TextView) this.findViewById(R.id.textView2);
        txtview.setText(app.getEnergyPoints()+"");
    }

    @Override
    protected void onResume(){
        super.onResume();

        final LearningCornor app = (LearningCornor)getApplication();
        final TextView txtview = (TextView) this.findViewById(R.id.textView2);
        txtview.setText(app.getEnergyPoints()+"");
    }

    public void startPlus(View view) {
        Intent intent = new Intent(this, MathMainActivity.class);
        intent.putExtra(PROBLEM_CLASS, AdditionProblem.class.getName());
        startActivity(intent);
    }

    public void startMinus(View view) {
        Intent intent = new Intent(this, MathMainActivity.class);
        intent.putExtra(PROBLEM_CLASS, SubstractionProblem.class.getName());
        startActivity(intent);
    }

    public void startMultiply(View view) {
        Intent intent = new Intent(this, MathMainActivity.class);
        intent.putExtra(PROBLEM_CLASS, MultiplicationProblem.class.getName());
        startActivity(intent);
    }

    public void startDivide(View view) {
        Intent intent = new Intent(this, MathMainActivity.class);
        intent.putExtra(PROBLEM_CLASS, DivisionProblem.class.getName());
        startActivity(intent);
    }

    public void showGallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    public void startGameAirBattle(View view){
        final ToastRenderer toast = new ToastRenderer();

        final LearningCornor app = (LearningCornor)getApplication();
        if (app.getEnergyPoints() < 2 ) {
            toast.show(getApplicationContext(), "你的学豆不够");
            return;
        }
        app.decEnergyPoints(2);

        Intent intent = new Intent(this, AirBattleActivity.class);
        startActivity(intent);
    }
}
