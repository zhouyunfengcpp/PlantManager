package doublee.plantmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity{

    TextView QA1;
    TextView QA2;
    TextView QA3;
    Button Back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpvo);
        Intent intent = getIntent();

        QA1 = (TextView) findViewById(R.id.qa1);
        QA2 = (TextView) findViewById(R.id.qa2);
        QA3 = (TextView) findViewById(R.id.qa3);
        Back = (Button) findViewById(R.id.back);

        showHelp();

        onBack();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("HelpActivity", "onStartOK");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("HelpActivity", "onStopOK");
    }

    public void showHelp(){
        String QA1Str = "Q:这个怎么用？\nA:这个要这么用。";
        String QA2Str = "Q:这个怎么用？\nA:这个要这么用。";
        String QA3Str = "Q:这个怎么用？\nA:这个要这么用。";

        QA1.setText(QA1Str);
        QA2.setText(QA2Str);
        QA3.setText(QA3Str);
    }

    public void onBack(){
        Back = (Button) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
