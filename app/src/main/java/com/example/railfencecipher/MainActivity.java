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

public class MainActivity extends AppCompatActivity {

    Button btnEncrypt, btnDecrypt, btnClear, btnCopy,btnShare;
    EditText input, output, keyinput;
    String txt, key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEncrypt = findViewById(R.id.encrypt);
        btnDecrypt = findViewById(R.id.decrypt);
        btnClear = findViewById(R.id.clear);
        btnCopy = findViewById(R.id.copy);
        btnShare = findViewById(R.id.share);

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        keyinput = findViewById(R.id.keyinput);

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
                Toast.makeText( MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
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
                try {
                    output.setText(Decryption(txt, depth).toUpperCase());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    int depth;
    String Encryption(String plainText,int depth)throws Exception
    {
        int r=depth,len=plainText.length();
        int c=len/depth;
        char mat[][]=new char[r][c];
        int k=0;

        String cipherText="";

        for(int i=0;i< c;i++)
        {
            for(int j=0;j< r;j++)
            {
                if(k!=len)
                    mat[j][i]=plainText.charAt(k++);
                else
                    mat[j][i]='X';
            }
        }
        for(int i=0;i< r;i++)
        {
            for(int j=0;j< c;j++)
            {
                cipherText+=mat[i][j];
            }
        }
        return cipherText;
    }


    String Decryption(String cipherText,int depth)throws Exception
    {
        int r=depth,len=cipherText.length();
        int c=len/depth;
        char mat[][]=new char[r][c];
        int k=0;

        String plainText="";


        for(int i=0;i< r;i++)
        {
            for(int j=0;j< c;j++)
            {
                mat[i][j]=cipherText.charAt(k++);
            }
        }
        for(int i=0;i< c;i++)
        {
            for(int j=0;j< r;j++)
            {
                plainText+=mat[j][i];
            }
        }
        return plainText;
    }

}