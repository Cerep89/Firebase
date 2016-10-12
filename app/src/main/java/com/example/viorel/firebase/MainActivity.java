package com.example.viorel.firebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Declararea obectelor din main xml
    private EditText editEmail;
    private EditText editPass;
    private Button buttonLogare;
    private TextView textInregistrare;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        firebaseAuth = FirebaseAuth.getInstance();
        editEmail = (EditText) findViewById(R.id.textEmail);
        editPass = (EditText) findViewById(R.id.textpass);
        buttonLogare = (Button) findViewById(R.id.buttonLogare);
        textInregistrare = (TextView) findViewById(R.id.textRegister);
        progressDialog = new ProgressDialog(this);

        buttonLogare.setOnClickListener(this);
        textInregistrare.setOnClickListener(this);

    }
private  void logare(){
    String mail = editEmail.getText().toString().trim();
    String password = editPass.getText().toString().trim();
    // Conditii daca cimpurile sunt goale
if (TextUtils.isEmpty(mail)){
    Toast.makeText(this, "Notati va rog Emai-ul", Toast.LENGTH_LONG).show();
    // Stoparea functiei
    return;

}else  if (TextUtils.isEmpty(password)){
    Toast.makeText(this, "Notati va rog Parola", Toast.LENGTH_LONG).show();
}
    progressDialog.setMessage("Inregistrarea utilizatorului......");
    progressDialog.show();
    firebaseAuth.createUserWithEmailAndPassword(mail,password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Daca inregistrarea a parcus cu succes
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Inregistrarea aparcurs cu succes", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(MainActivity.this, "Inregistrarea nu a avut loc incercati din nou", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}



    @Override
    public void onClick(View v) {
        // Darea actiunii buttonului de logare si textului de inregistrare
if (v == buttonLogare){
    logare();
}else if (v == textInregistrare){

}
    }
}
