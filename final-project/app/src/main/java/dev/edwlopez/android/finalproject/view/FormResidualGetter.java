package dev.edwlopez.android.finalproject.view;

import static dev.edwlopez.android.finalproject.R.*;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import dev.edwlopez.android.finalproject.R;
import dev.edwlopez.android.finalproject.credential.UserCredential;
import dev.edwlopez.android.finalproject.data.AbstractRestAPI;
import dev.edwlopez.android.finalproject.data.UserResidualCategoryAPI;
import dev.edwlopez.android.finalproject.data.UserResidualMagnitudeAPI;
import dev.edwlopez.android.finalproject.data.UserResidualRegisterAPI;
import dev.edwlopez.android.finalproject.data.entity.AuthFlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.AuthUser;
import dev.edwlopez.android.finalproject.data.entity.FlattenUserResidualRegister;
import dev.edwlopez.android.finalproject.data.entity.ResidualCategory;
import dev.edwlopez.android.finalproject.data.entity.ResidualMagnitude;
import dev.edwlopez.android.finalproject.data.entity.collection.ListResidualMagnitude;
import android.content.res.Resources;

public class FormResidualGetter extends AppCompatActivity {
    private Button submitButton, clearButton;
    private Spinner categoriesSelector, magnitudesSelector;
    private EditText inputQuantity, inputDescription;
    private AtomicBoolean isChargeCategoryData = new AtomicBoolean(false), isChargeMagnitudeData = new AtomicBoolean(false);
    private AtomicBoolean haveCategoriesData = new AtomicBoolean(false), haveMagnitudeData = new AtomicBoolean(false);
    private Boolean flag_submit = false;

    // Getting bundle
    private UserCredential credential;
    //private final UserCredential credential = data.getParcelable("user_credential");

    // Services
    private final UserResidualCategoryAPI categoryAPI = UserResidualCategoryAPI.getInstance();
    private final UserResidualMagnitudeAPI magnitudeAPI = UserResidualMagnitudeAPI.getInstance();
    private final UserResidualRegisterAPI registerAPI = UserResidualRegisterAPI.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_residual_getter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get bundle data
        credential = (UserCredential) this.getIntent().getParcelableExtra("credential");

        // Get all needed refs
        submitButton = findViewById(R.id.submit_button);
        clearButton = findViewById(R.id.clear_button);
        categoriesSelector = findViewById(R.id.input_categories);
        magnitudesSelector = findViewById(R.id.input_magnitude);
        inputQuantity = findViewById(R.id.input_quantity);
        inputDescription = findViewById(R.id.input_description);

        // Start the logic

        // Get all categories and magnitudes from database and putting in each spinner
        initAll();

    }

    private void setActionsToFormButtons () {
        clearButton.setOnClickListener((v) -> {
            // Clear data of form
            categoriesSelector.setSelection(0);
            magnitudesSelector.setSelection(0);
            inputQuantity.setText("");
            inputDescription.setText("");
        });
    }

    // Errors listeners
    private void errorListeners () {
        inputQuantity.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && inputQuantity.getError() != null) {
                inputQuantity.setError(null);
            }
        });

        // Get all data of fields and post to the API
        submitButton.setOnClickListener((v) -> {
            String category = categoriesSelector.getSelectedItem().toString();
            String magnitude = magnitudesSelector.getSelectedItem().toString();
            String description = inputDescription.getText().toString();

            String rawQuantity = inputQuantity.getText().toString();
            Double quantity = 0d;

            // Verify content
            if (rawQuantity == null) {
                inputQuantity.setError("This field must be has quantity.");
                return;
            }

            quantity = Double.parseDouble(rawQuantity);

            // Build the DTO
            final var authRegister = new AuthFlattenUserResidualRegister();
            var authUser = credential.toAuthEntity();
            var register = new FlattenUserResidualRegister();

            // Set data and complete the main DTO
            register.setDescription(description);
            register.setCategory(category);
            register.setMagnitude(magnitude);
            register.setQuantity(quantity);

            authRegister.setRegisterDTO(register);
            authRegister.setAuthUserDTO(authUser);

            // Send to database and await response
            registerAPI.addNewRegister(this::onSuccessReceiptRegister, this::onFailureReceiptRegister, authRegister);
        });
    }

    // Methods to manage send to database
    private void onSuccessReceiptRegister (FlattenUserResidualRegister register) {
        Toast.makeText(this, "The register was created correctly. Id = " + register.getId(), Toast.LENGTH_SHORT).show();
    }

    private void onFailureReceiptRegister (Throwable t) {
        Log.e("FormResidualGetter", t.getMessage(), t);
    }


    // Init all
    private void initAll () {
        emergencyChargeCategoryData();
        emergencyChargeMagnitudeData();
        initGetCategories();
        initGetMagnitudes();

        // Set error to fields
        errorListeners();

        setActionsToFormButtons();
    }
    // END

    // Spinner method

    private void setDataToCategorySpinner (List<ResidualCategory> categories) { // onSuccess method for category spinner
        List<String> ls = categories.stream().map(ResidualCategory::getName).collect(Collectors.toList());
        ArrayAdapter<String> data = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, ls);
        haveCategoriesData.set(true);
        isChargeCategoryData.set(false);

        categoriesSelector.setAdapter(data);
    }

    private void setDataToMagnitudeSpinner (List<ResidualMagnitude> magnitudes) { // onSuccess method for magnitudes spinner
        ArrayAdapter<String> data = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, magnitudes.stream().map(ResidualMagnitude::getName).collect(Collectors.toList()));
        haveMagnitudeData.set(true);
        isChargeMagnitudeData.set(false);


        magnitudesSelector.setAdapter(data);
    }

    private void onFailureCategorySpinnerData (Throwable t, String message) { // onFailure category data
        ArrayAdapter<String> data = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{});

        isChargeCategoryData.set(false);
        categoriesSelector.setAdapter(data);

        Log.e("FormResidualGetter", t.getMessage(), t);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void onFailureMagnitudeSpinnerData (Throwable t, String message) { // onFailure category data
        ArrayAdapter<String> data = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{});

        isChargeCategoryData.set(false);
        magnitudesSelector.setAdapter(data);

        Log.e("FormResidualGetter", t.getMessage(), t);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Get categories
    private void initGetCategories () {
        if (haveCategoriesData.get()) return;

        try {
            categoryAPI.getAllCategories(this::setDataToCategorySpinner, this::onFailureCategorySpinnerData);
            isChargeCategoryData.set(true);
        } catch (IOException e) {
            isChargeCategoryData.set(false);
            throw new RuntimeException(e);
        }
    }

    // Get magnitudes
    private void initGetMagnitudes () {
        if (haveMagnitudeData.get()) return;

        try {
            magnitudeAPI.getAllMagnitudes(this::setDataToMagnitudeSpinner, this::onFailureMagnitudeSpinnerData);
            isChargeMagnitudeData.set(true);
        } catch (IOException e) {
            isChargeMagnitudeData.set(false);
            throw new RuntimeException(e);
        }
    }

    // Category spinner listeners
    private void emergencyChargeCategoryData () {
        // Put listener to charge data new
        categoriesSelector.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                if (haveCategoriesData.get()) {
                    return;
                }

                if (!isChargeCategoryData.get()) {
                    isChargeCategoryData.set(true);
                    initGetCategories();

                }
            }
        });
    }

    // magnitude spinner listeners
    private void emergencyChargeMagnitudeData () {
        // Put listener to charge data new
        magnitudesSelector.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) return;

            if (haveMagnitudeData.get()) {
                return;
            }

            if (!isChargeMagnitudeData.get()) {
                isChargeCategoryData.set(true);
                initGetMagnitudes();
            }
        });
    }
}