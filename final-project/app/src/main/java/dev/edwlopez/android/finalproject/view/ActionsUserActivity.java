package dev.edwlopez.android.finalproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.edwlopez.android.finalproject.MainActivity;
import dev.edwlopez.android.finalproject.R;
import dev.edwlopez.android.finalproject.credential.UserCredential;

public class ActionsUserActivity extends AppCompatActivity {
    private Button registerResidualButton, queryRegisterButton, generateReportButton, logoutButton;
    private UserCredential credential;
    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actions_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get bundle
        data = this.getIntent().getBundleExtra("dev.edwlopez.android.finalproject.MainActivity");
        credential = (UserCredential) data.getParcelable("user_credential");

        // Get all refs
        registerResidualButton = findViewById(R.id.actions_register_button);
        queryRegisterButton = findViewById(R.id.actions_query_buttons);
        generateReportButton = findViewById(R.id.actions_generator_report);
        logoutButton = findViewById(R.id.actions_logout);

        // Add click listener and actions to all buttons

        // Destroy current credentials and return to first view
        logoutButton.setOnClickListener((view) -> {
            credential.destroy();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        // Change to form to register residuals
        registerResidualButton.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), FormResidualGetter.class);
            intent.putExtra("dev.edwlopez.android.finalproject.ActionsUserActivity", data);

            startActivity(intent);
        });

        // Change to form to view lasted user register
        queryRegisterButton.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), ViewResidualRegisterUserActivity.class);
            intent.putExtra("dev.edwlopez.android.finalproject.ActionsUserActivity", data);

            startActivity(intent);
        });

        // Send a request to API Server to make a report of user's recollection of residuals

    }
}