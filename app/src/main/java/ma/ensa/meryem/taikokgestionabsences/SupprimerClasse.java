package ma.ensa.meryem.taikokgestionabsences;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SupprimerClasse extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    private Button ButtonSupprimerClasse;
    private EditText idClasse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_classe);
        ButtonSupprimerClasse = (Button) findViewById(R.id.SupprimerCl);
        idClasse = (EditText) findViewById(R.id.idClass);

        ButtonSupprimerClasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimer();
                Intent intent = new Intent(getApplicationContext(), Menu1.class);
                startActivity(intent);
            }
        });

    }

    public void supprimer(){
        String IDClasse = idClasse.getText().toString();
        int resultatClasse = db.supprimerClasse(IDClasse);
        if(resultatClasse == 1) {
            Toast.makeText(SupprimerClasse.this, "Classe Supprimée ! ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(SupprimerClasse.this, "N'existe pas une classe avec cet ID ! ", Toast.LENGTH_LONG).show();
        }
    }



}
