package tf.www.echecklisttfamd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import tf.www.echecklisttfamd.Operator.OperatorActivity;
import tf.www.echecklisttfamd.Technician.TechnicianActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etempid;
    private TextView tvSecurityID;

    private long delay = 1000;

    private TimerTask task = new TimerTask(){
        @Override
        public void run() {
            // TDO Auto-generated method stub
           /* startActivity(new Intent(LoginActivity.this, TechnicianActivity.class));
            finish();*/
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etempid = findViewById(R.id.EmpId);
        tvSecurityID = findViewById(R.id.SecurityId);
        Button technician, operator;
        technician = findViewById(R.id.button2);
        operator = findViewById(R.id.button1);

        Timer timer = new Timer();
        timer.schedule(task,delay);

        etempid.setShowSoftInputOnFocus(false);
        etempid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Snackbar snackbar = Snackbar.make(tvSecurityID, "OK!", Snackbar.LENGTH_LONG);
                //snackbar.show();
                SetEmp(etempid.getText().toString());
                startActivity(new Intent(LoginActivity.this, OperatorActivity.class));
                finish();
            }
        });

        operator.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                SetEmp("123131");
                startActivity(new Intent(LoginActivity.this, OperatorActivity.class));
                finish();
            }
        });

        technician.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                SetTech("2312315");
                startActivity(new Intent(LoginActivity.this, TechnicianActivity.class));
                finish();
            }
        });

    }

    protected void SetEmp(String empid) {

        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("emp", empid);
        editor.commit();

    }

    protected void SetTech(String techid) {

        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("techid", techid);
        editor.commit();

    }
}
