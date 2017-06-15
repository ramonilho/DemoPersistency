package br.com.ramonilho.demopersistency;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etUser)
    EditText etUser;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.cbStayConnected)
    CheckBox cbStayConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loadSavedInformations();
    }

    public void loadSavedInformations() {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String username = sp.getString("username", "");

        if (!username.equals("")) {
            String password = sp.getString("password", null);

            etUser.setText(username);
            etPassword.setText(password);

            Toast.makeText(this, "Informações anteriores carregadas", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btLogin)
    public void btLogin(View view) {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();

        Log.i("LoginActivity", "btLogin clicked");

        String username = etUser.getText().toString();
        String password = etPassword.getText().toString();
        boolean stayConnected = cbStayConnected.isChecked();

        if (stayConnected) {
            e.putString("username", username);
            e.putString("password", password);
            e.putBoolean("stayConnected", stayConnected);
            e.apply();

            Toast.makeText(this, "Login realizado com sucesso, seus dados foram salvos", Toast.LENGTH_SHORT).show();

        } else {
            e.putString("username", "");
            e.putString("password", "");
            e.putBoolean("stayConnected", false);
            e.apply();
            Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
        }
        e.apply();
    }
}
