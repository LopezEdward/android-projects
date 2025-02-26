package dev.edwlopez;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static boolean FLAG_INIT = true;
    private final static String[] COURSES = {"Excel", "Power BI", "Python", "Java", "Android Studio"};
    private final static String[] TYPE = {"Normal", "Beca", "Media Beca"};

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

        EditText namesField = this.findViewById(R.id.input_names);
        EditText surnamesField = this.findViewById(R.id.input_surnames);
        Spinner sp1 = this.findViewById(R.id.spin_courses);
        Spinner sp2 = this.findViewById(R.id.spin_types);
        Button calc = (Button) this.findViewById(R.id.button_calc);
        Button buttonNew = (Button) this.findViewById(R.id.button_clean);

        // I don't know who get the layout element of spinner
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, COURSES);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, TYPE);

        sp1.setAdapter(arrayAdapter1);
        sp2.setAdapter(arrayAdapter2);

        // Get EditItem for cost
        EditText showText = this.findViewById(R.id.show_price);
        EditText showResult = this.findViewById(R.id.show_result);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String op = parent.getSelectedItem().toString();

                showText.setText(String.valueOf(getPrice(op)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (FLAG_INIT) { FLAG_INIT = false; return; }
                if (showText.toString().isBlank() || showText.toString().isEmpty()) return;

                String op = parent.getSelectedItem().toString();
                Double firstPrice, result;

                firstPrice = getPrice(sp1.getSelectedItem().toString());
                result = (getDescount(op) != 0) ? firstPrice * getDescount(op) : firstPrice;
                showResult.setText(String.format(result.toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        // Set listener to button
        calc.setOnClickListener(v -> {
            double firstPrice = getPrice(sp1.getSelectedItem().toString());
            double result = (getDescount(sp2.getSelectedItem().toString()) != 0) ? firstPrice * getDescount(sp2.getSelectedItem().toString()) : firstPrice;
            showResult.setText(String.valueOf(result));
        });

        buttonNew.setOnClickListener(v -> {
            // Clear all data of form
            namesField.setText("");
            surnamesField.setText("");

            sp1.setSelection(0);
            sp2.setSelection(0);

            showText.setText("");
            showResult.setText("");
        });
    }

    private double getDescount (String type) {
        double descount = 0;

        switch (type) {
            case "Normal":
                descount = 0;
                break;
            case "Beca":
                descount = 0.25;
                break;
            case "Media Beca":
                descount = 0.5;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return descount;
    }

    private double getPrice (String course) {
        double price = 0;

        switch (course) {
            case "Python":
                price = 200.0;
                break;
            case "Java":
                price = 421.0;
                break;
            case "Power BI":
                price = 120.5;
                break;
            case "Android Studio":
                price = 220.12;
                break;
            case "Excel":
                price = 190.21;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return price;
    }
}