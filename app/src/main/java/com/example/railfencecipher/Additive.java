package com.example.railfencecipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Additive extends AppCompatActivity {
    Button btnEncrypt, btnDecrypt, btnClear, btnCopy,btnShare,btnHome;
    EditText input, output, keyinput;
    String txt, key;
    public static final String alph;

    static {
        alph = "abcdefghijklmnopqrstuvwxyz";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_railfence);

        btnEncrypt = findViewById(R.id.encrypt);
        btnDecrypt = findViewById(R.id.decrypt);
        btnClear = findViewById(R.id.clear);
        btnCopy = findViewById(R.id.copy);
        btnShare = findViewById(R.id.share);
        btnHome = findViewById(R.id.home);

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        keyinput = findViewById(R.id.keyinput);

        //home
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityHome();
            }
        });

        //clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText("");
                output.setText("");
                keyinput.setText("");
            }
        });

        //copy
        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("decrypted_text", output.getText().toString());
                clipboard.setPrimaryClip(clip);

                clip.getDescription();
                Toast.makeText( Additive.this, "Copied", Toast.LENGTH_SHORT).show();
            }

        });

        //share
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_INTENT, output.getText().toString());
                shareIntent.setType("text/plain");
                shareIntent = Intent.createChooser(shareIntent, "Share via:");
                startActivity(shareIntent);

            }
        });

        //encrypt
        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = keyinput.getText().toString();
                txt = input.getText().toString();
                int depth=Integer.parseInt(key);

                try {
                    output.setText(Encryption(txt, depth).toUpperCase());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //decrypt
        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = keyinput.getText().toString();
                txt = input.getText().toString();
                int depth=Integer.parseInt(key);
//                try {
//                   output.setText(Decryption(txt, depth).toUpperCase());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        });

    }

    private void openActivityHome() {
        Intent intentCal = new Intent(this, MainActivity.class);
        startActivity(intentCal);
    }


    String Encryption(String plainT,int shift)
    {
        plainT = plainT.toLowerCase();
        // converting the text to lowercase
        String cipherT = "";
        // initializing empty string to add alphabets iteratively
        for (int i = 0; i < plainT.length(); i++) {
            int mappingV = alph.indexOf(plainT.charAt(i));
            // value of each alphabet in integers like for A=0, B=1 ...
            int enVal = (shift + mappingV) % 26;
            char Val = alph.charAt(enVal); // the character to be replaced
            cipherT = cipherT + Val; // adding to ciphertext
        }
        return cipherT;
    }


    String Decryption(String cipherT,int shift)
    {
        cipherT = cipherT.toLowerCase();
        // converting the text to lowercase
        String plainT = "";
        // initializing empty string to add alphabets iteratively
        for (int i = 0; i < cipherT.length(); i++) {
            int mappingV = alph.indexOf(cipherT.charAt(i));
            int deVal = (mappingV - shift) % 26;
            if (deVal < 0) // to handle the negative values
            {
                deVal = alph.length() + deVal;
            }
            char Val = alph.charAt(deVal); // the character to be replaced
            plainT = plainT + Val; // adding to plaintext
        }
        return plainT;
    }

}