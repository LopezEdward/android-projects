package com.example.crudproductos;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText edtNombre, edtPrecio, edtStock, edtUrl;
    private Button btnGuardar;
    private DatabaseHelper databaseHelper;
    private EditText edtDescription;

    private final static int MAX_CHARS = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        edtNombre = findViewById(R.id.edtNombre);
        edtPrecio = findViewById(R.id.edtPrecio);
        edtStock = findViewById(R.id.edtStock);
        edtUrl = findViewById(R.id.edtUrl);
        btnGuardar = findViewById(R.id.btnGuardar);
        databaseHelper = new DatabaseHelper(this);
        edtDescription = findViewById(R.id.edtDescription);

        btnGuardar.setOnClickListener(v -> guardarProducto());
        setListener();
    }



    private void setListener () {
        edtDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                edtDescription.setError(null);

                if (count >= MAX_CHARS) {
                    edtDescription.setText(s.toString());
                    edtDescription.setError("The max lenght is " + MAX_CHARS);
                    edtDescription.requestFocus();
                    return;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtDescription.setError(null);

                if (count >= MAX_CHARS) {
                    edtDescription.setError("The max lenght is " + MAX_CHARS);
                    edtDescription.requestFocus();
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                edtDescription.setError(null);

                if (s.length() >= MAX_CHARS) {
                    edtDescription.setText(s.toString());
                    edtDescription.setError("The max lenght is " + MAX_CHARS);
                    edtDescription.requestFocus();
                    return;
                }
            }
        });
    }

    private void guardarProducto() {
        String nombre = edtNombre.getText().toString().trim();
        String precioStr = edtPrecio.getText().toString().trim();
        String stockStr = edtStock.getText().toString().trim();
        String url = edtUrl.getText().toString().trim();

        if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty() || url.isEmpty() ) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio = Double.parseDouble(precioStr);
        int stock = Integer.parseInt(stockStr);

        databaseHelper.insertarProducto(nombre, precio, stock, url);
        Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
