package tf.www.echecklisttfamd;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText etempid;
    private TextView tvSecurityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etempid = findViewById(R.id.EmpId);
        tvSecurityID = findViewById(R.id.SecurityId);
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
                startActivity(new Intent(LoginActivity.this, OperatorActivity.class));
                finish();
            }
        });

    }
}
