package doublee.plantmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetActivity extends AppCompatActivity{
    Button Back;
    Button HumidityChange;
    EditText Upper;
    EditText Lower;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setvo);
        Intent intent = getIntent();

        Back = (Button) findViewById(R.id.back);
        HumidityChange = (Button) findViewById(R.id.humidityChange);
        Upper = (EditText) findViewById(R.id.upper);
        Lower = (EditText) findViewById(R.id.lower);

        onChange();

        onBack();
    }

    public void onChange(){
        String upperStr = Upper.getText().toString();
        String lowerStr = Lower.getText().toString();
        if (upperStr!=null||upperStr.equals("")==false||lowerStr!=null||lowerStr.equals("")==false){
            HumidityChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /** 之后需要对硬件和controller进行匹配，发送setting数据
                     *
                     */
                }
            });
        }
    }

    public void onBack(){
        Back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}