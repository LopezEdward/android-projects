package dev.edwlopez.android.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.os.BundleCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dev.edwlopez.android.finalproject.credential.UserCredential;
import dev.edwlopez.android.finalproject.data.UserRestAPI;
import dev.edwlopez.android.finalproject.data.entity.User;
import dev.edwlopez.android.finalproject.view.ActionsUserActivity;
import dev.edwlopez.android.finalproject.view.FormResidualGetter;
import dev.edwlopez.android.finalproject.view.RegisterUserActivity;
import dev.edwlopez.android.finalproject.util.EditTextTools;

public class MainActivity extends AppCompatActivity {
    private final UserRestAPI client1 = UserRestAPI.getInstance();
    private UserCredential credential, tmp;
    private EditText inputUsername, inputPassword;
    private Button buttonLogin, buttonRegister;
    private View layoutMain, containerLoadBar;
    private ProgressBar progressBar;
    private Map<Integer, TextView> idOfTextView = new HashMap<>();
    private boolean isSpinActived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get all elements
        inputUsername = this.findViewById(R.id.inputUsername);
        inputPassword = this.findViewById(R.id.inputPassword);
        buttonLogin = this.findViewById(R.id.loginButton);
        buttonRegister = this.findViewById(R.id.registerButton);
        layoutMain = this.findViewById(R.id.main);
        containerLoadBar = this.findViewById(R.id.container_loadBar);
        progressBar = this.findViewById(R.id.loadDataBar);

        // Start all
        initAll();
    }

    private void setClickListenerToButtons () {
        // To register button
        this.buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterUserActivity.class);
            startActivity(intent);
        });

        // To login button
        this.buttonLogin.setOnClickListener(v -> {
            // Verify data in fields
            boolean verification = EditTextTools.verifyDataInputs(new EditText[]{inputUsername, inputPassword});

            if (!verification) return;

            // Present the loaded bar
            activeLoadBar(null);

            String username = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            User usr = new User(null, username, password, null);

            tmp = UserCredential.fromEntity(usr);

            // Logic with Database
            try {
                initGetUser(username);
            } catch (IOException e) {
                failureGetCredentials(e);
            }
        });
    }

    // Method to add data to maps
    private void initMaps () {
        idOfTextView.put(R.id.labelUsername, this.findViewById(R.id.labelUsername));
        idOfTextView.put(R.id.labelPassword, this.findViewById(R.id.labelPassword));
    }

    // Method to initialize all (needed) methods
    private void initAll () {
        initMaps();
        setClickListenerToButtons();
    }

    // Provide a simple manager for credentials
    private final void receivedCredentials(User user) {
        // Verify actual credential object
        // if (credential != null && credential.assertUsername(user.getUsername())) return;
        if (credential != null) credential.destroy();
        credential = UserCredential.fromEntity(user);

        // If credential has null values, return username error
        if (credential.isNull()) {
            disableLoadBar("The username doesn't exists!");
            return;
        }

        // Comparated the actual credential object with the temporal credential object
        if (!tmp.isEqual(credential)) {
            disableLoadBar("The credentials are incorrect!");
            return;
        }

        disableLoadBar(null);

        // Bundle data (I HATE GOOGLEDAS ASDDASDASD)
        Bundle bundle = new Bundle();
        bundle.putParcelable("user_credential", credential);

        // Invoke method to continue interaction of user
        changeActivity(ActionsUserActivity.class, bundle);
    }

    private final void initGetUser (String username) throws IOException {
        this.client1.getUserCredential(this::receivedCredentials, this::failureGetCredentials, username);
    }

    private final void failureGetCredentials (Throwable t) {
        Log.e("MainActivity", t.getMessage(), t);

        disableLoadBar("Error to load user data!");
    }

    private final void changeActivity (Class<?> anotherActivity, Bundle extra) {
        Intent intent = new Intent(this, anotherActivity);

        if (extra != null) intent.putExtra("dev.edwlopez.android.finalproject.MainActivity", extra);

        startActivity(intent);
    }

    private void disableLoadBar (String message) {
        containerLoadBar.setVisibility(View.GONE);
        isSpinActived = false;

        if (message != null) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    private void activeLoadBar (String message) {
        containerLoadBar.setVisibility(View.VISIBLE);
        isSpinActived = true;

        if (message != null) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}