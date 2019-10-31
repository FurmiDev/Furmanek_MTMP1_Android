package sk.furmi.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final double velocity = 0;
    public static final double angle = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void startThrow(View view) {

        Intent intent = new Intent(this, ResultTableActivity.class);
        EditText angleText = findViewById(R.id.angle);
        EditText velocityText = findViewById(R.id.velocity);
        Float angle  = Float.parseFloat(angleText.getText().toString());
        Float velocity = Float.parseFloat(velocityText.getText().toString());
        intent.putExtra("angle", angle);
        intent.putExtra("velocity",velocity);
        startActivity(intent);
    }

}
