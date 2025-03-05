package dev.edwlopez.android.finalproject.util;

import android.widget.EditText;

import java.util.function.Predicate;

public abstract class EditTextTools {
    private EditTextTools () {};

    public static boolean verifyDataInputs (EditText[] inputs) {
        for (EditText input : inputs) {
            if (input == null) throw new NullPointerException("The inputs must not null!");

            if (!hasData(input.getText().toString())) {
                input.setError("The input must have some text...");
                input.requestFocus();
                //Toast.makeText(getApplicationContext(), "The field " + idOfTextView.get(input.getLabelFor()).getText().toString() + " must have data!", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        return true;
    }

    public static boolean verifyDataInputs (EditText[] inputs, String errorMessage) {
        for (EditText input : inputs) {
            if (input == null) throw new NullPointerException("The inputs must not null!");

            if (!hasData(input.getText().toString())) {
                input.setError(errorMessage);
                input.requestFocus();
                //Toast.makeText(getApplicationContext(), "The field " + idOfTextView.get(input.getLabelFor()).getText().toString() + " must have data!", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        return true;
    }

    public static boolean verifyDataInputs (EditText[] inputs, Predicate<EditText>[] verifications) {
        for (int i = 0; i < inputs.length; i++) {
            boolean result = verifications[i].test(inputs[i]);

            if (!result) {
                inputs[i].setError("The input must have some text...");
                inputs[i].requestFocus();
                //Toast.makeText(getApplicationContext(), "The field " + idOfTextView.get(input.getLabelFor()).getText().toString() + " must have data!", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        return true;
    }

    public static boolean verifyDataInput (EditText input) {
        String res = input.getText().toString();
        boolean result = hasData(res);

        if (!result) {
            input.setError("The input must be has data!");
        }

        return result;
    }

    public static boolean hasData (String rawData) {
        rawData = rawData.trim();

        return !rawData.isBlank() && !rawData.isEmpty();
    }
}
