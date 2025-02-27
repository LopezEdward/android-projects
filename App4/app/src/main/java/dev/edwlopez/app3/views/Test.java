package dev.edwlopez.app3.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.HashMap;
import java.util.Map;

import dev.edwlopez.app3.R;
import dev.edwlopez.app3.enums.District;
import dev.edwlopez.app3.enums.Position;

public class Test extends AppCompatActivity {
    private Spinner spinDistrict;
    private Spinner spinPosition;
    private EditText inputLarge;
    private EditText inputWidth;
    private EditText outputPENValue;
    private EditText outputUSDValue;
    private EditText outputAreaValue;
    private Button buttonCalc;
    private Button buttonClear;
    private Button showPlan;
    private static final Map<String, Double> pricePerSquareMeter = new HashMap<>();
    private static final Map<String, Double> positionPrice = new HashMap<>();
    private static final String[] DISTRICTOS = {"Lima Cercado", "La Victoria", "Lince", "Jesús María", "Pueblo Libre"};
    private static final String[] POSICION = {"Pasaje", "Jirón o Calle", "Avenida", "Frente al Parque"};
    private static final double USD_VALUE = 3.68d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        putValuesConvertionMap();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtain views
        spinDistrict = this.findViewById(R.id.spinner_district);
        spinPosition = this.findViewById(R.id.spinner_position);
        inputLarge = this.findViewById(R.id.input_large);
        inputWidth = this.findViewById(R.id.input_width);
        outputPENValue = this.findViewById(R.id.output_pen_terrain_value);
        outputUSDValue = this.findViewById(R.id.output_us_terrain_value);
        outputAreaValue = this.findViewById(R.id.output_area);
        buttonClear = this.findViewById(R.id.button_clear);
        buttonCalc = this.findViewById(R.id.button_calc);
        showPlan = this.findViewById(R.id.buttonShowPlan);

        // Charge spinners with data!
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, DISTRICTOS);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, POSICION);

        spinDistrict.setAdapter(adapter1);
        spinPosition.setAdapter(adapter2);

        // Set listener
        buttonClear.setOnClickListener(v -> {
            inputLarge.setText(""); inputWidth.setText(""); spinDistrict.setSelection(0); spinPosition.setSelection(0);
            outputAreaValue.setText("");
            outputUSDValue.setText("");
            outputPENValue.setText("");
        });

        buttonCalc.setOnClickListener(v -> {
            initProcess();
        });

        showPlan.setOnClickListener(v -> {
            Intent intent = new Intent(this.getApplicationContext(), Plan.class);
            startActivity(intent);
        });
    }

    private double getFieldData (EditText view) {
        double value = 0d;
        String rawValue = view.getText().toString().trim();

        if (rawValue.isEmpty() || rawValue.isBlank()) throw new IllegalArgumentException("The field is void!");

        value = Double.parseDouble(rawValue);

        return value;
    }

    private String getFieldData (Spinner spin) {
        return spin.getSelectedItem().toString();
    }

    private void initProcess () {
        double large, width;
        // Collect data from inputs of large and width
        try {
            large = getFieldData(inputLarge);
            width = getFieldData(inputWidth);
        } catch (IllegalArgumentException ex) {
            Toast.makeText(this.getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT);
            return;
        }

        // Collect data from spinners
        String district; String position;

        district = getFieldData(spinDistrict);
        position = getFieldData(spinPosition);

        // Calc result
        double terrain_area = (large * width);
        double districtPrice = pricePerSquareMeter.get(district);
        double descount = positionPrice.get(position);
        double pen_value_terrain, usd_value_terrain;

        if (descount > 0) {
            pen_value_terrain = (terrain_area * districtPrice);
            pen_value_terrain = pen_value_terrain + (pen_value_terrain * descount);
            usd_value_terrain = pen_value_terrain / USD_VALUE;
        } else {
            pen_value_terrain = (terrain_area * districtPrice);
            pen_value_terrain = pen_value_terrain - (-descount * pen_value_terrain);
            usd_value_terrain = pen_value_terrain / USD_VALUE;
        }

        // Show output
        outputAreaValue.setText(String.valueOf(terrain_area) + "m^2");
        outputPENValue.setText("S/ " + pen_value_terrain);
        outputUSDValue.setText("$ " + usd_value_terrain);
    }

    private void putValuesConvertionMap() {
        this.pricePerSquareMeter.put("Lima Cercado", 450d);
        this.pricePerSquareMeter.put("La Victoria", 550d);
        this.pricePerSquareMeter.put("Lince", 650d);
        this.pricePerSquareMeter.put("Jesús María", 670d);
        this.pricePerSquareMeter.put("Pueblo Libre", 750d);

        this.positionPrice.put("Pasaje", -0.1);
        positionPrice.put("Jirón o Calle", 0.05);
        positionPrice.put("Avenida", 0.1);
        positionPrice.put("Frente al Parque", 0.18);
    }
}