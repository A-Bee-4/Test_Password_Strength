package com.example.testpasswordstrength;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"+
                    "(?=.*[0-9])"+      // at least one digit
//                    "(?=.*[a-zA-Z)"+  // any letter
                    "(?=.*[A-Z])"+      // at least one uppercase letter
                    "(?=.*[a-z])"+      // at least one lowercase letter
                    "(?=.*[@#$%+^&=])"+ // at least one special character
                    "(?=\\S+$)"+        // no white space
                    ".{8,}"+            // at least 8 character  {8,20} min 8 max 20 character
                    "$" );                // end of String




    private TextInputLayout txt_input_name, txt_input_email, txt_input_password, txt_input_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_input_name = (TextInputLayout) findViewById(R.id.txt_input_name);
        txt_input_email = (TextInputLayout) findViewById(R.id.txt_input_email);
        txt_input_password = (TextInputLayout) findViewById(R.id.txt_input_password);
        txt_input_confirm_password = (TextInputLayout) findViewById(R.id.txt_input_confirm_password);

    }

    private boolean hasValidName() {
        String nameInput = txt_input_name.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            txt_input_name.setError("Please enter your name");
            return false;
        } else {
            txt_input_name.setError(null);
//            txt_input_name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean hasValidEmail() {
        String emailInput = txt_input_email.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            txt_input_email.setError("Please enter your email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            txt_input_email.setError("Please enter a valid email address");
            return false;

        } else if (emailInput.length() > 15) {
            txt_input_email.setError("Email address too long");
            return false;
        } else {
            txt_input_email.setError(null);
            return true;

        }
    }


    private boolean hasValidPassword() {
        String passwordInput = txt_input_password.getEditText().getText().toString();

        if (passwordInput.isEmpty()) {
            txt_input_password.setError("Please enter your password");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            txt_input_password.setError("Password too week");
            return false;
        } else {
            txt_input_password.setError(null);
//            txt_input_password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean confirmPassword() {
        String confirmPasswordInput = txt_input_confirm_password.getEditText().getText().toString();
        String passwordInput = txt_input_password.getEditText().getText().toString();

        if (!confirmPasswordInput.equals(passwordInput)) {
            txt_input_confirm_password.setError("Password does not match");
            return false;
        } else {
            txt_input_confirm_password.setError(null);
            return true;
        }
    }

    public void registerInput(View view) {

        if (!hasValidName() | !hasValidEmail() | !hasValidPassword() | !confirmPassword()) {
            return;
        }
        String input = "Name: " + txt_input_name.getEditText().getText().toString();
        input += "\n";
        input += "Email: " + txt_input_email.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + txt_input_password.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();


    }

}