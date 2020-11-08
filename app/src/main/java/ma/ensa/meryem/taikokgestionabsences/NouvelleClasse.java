package ma.ensa.meryem.taikokgestionabsences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NouvelleClasse extends AppCompatActivity {
    DBHelper db = new DBHelper(this);


    private Button buttonNouvelleClasse;
    private EditText filiere;
    private EditText niveau;
    private EditText respoonsable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_classe);

        buttonNouvelleClasse = (Button) findViewById(R.id.EnregistrerClasse);
        filiere = (EditText) findViewById(R.id.FiliereClass);
        niveau = (EditText) findViewById(R.id.NiveauClass);
        respoonsable = (EditText) findViewById(R.id.Responsabl);




        buttonNouvelleClasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjouterNouvelleClasse();
                Intent intent = new Intent(getApplicationContext(), Menu1.class);
                startActivity(intent);

            }
        });
    }



    public void AjouterNouvelleClasse(){

        String fil = filiere.getText().toString();
        String niv = niveau.getText().toString();
        String res = respoonsable.getText().toString();
        Boolean result = db.ajouterClasse(fil, niv, res);
        if(result == true) {
            Toast.makeText(NouvelleClasse.this, "Classe Ajoutée ! ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(NouvelleClasse.this, "Erreur ! ", Toast.LENGTH_LONG).show();
        }
    }


}

