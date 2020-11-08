package ma.ensa.meryem.taikokgestionabsences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MettreAjourClasse extends AppCompatActivity {



    DBHelper db = new DBHelper(this);


    private Button ButtonMettreAJourClasse;
    private EditText id;
    private EditText filiere;
    private EditText niveau;
    private EditText respoonsable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mettre_a_jour_classe);


        ButtonMettreAJourClasse = (Button) findViewById(R.id.MettreAjourClass);
        id = (EditText) findViewById(R.id.IDClasse);
        filiere = (EditText) findViewById(R.id.Filier);
        niveau = (EditText) findViewById(R.id.NiveauClasse);
        respoonsable = (EditText) findViewById(R.id.Resp);

        ButtonMettreAJourClasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mettreAjourClasse();
                Intent intent = new Intent(getApplicationContext(), Menu1.class);
                startActivity(intent);
            }
        });

    }

    public void mettreAjourClasse(){
        String fil = filiere.getText().toString();
        String niv = niveau.getText().toString();
        String res = respoonsable.getText().toString();
        String identifiant = id.getText().toString();

        Boolean resultat = db.mettreAjourClasse(identifiant, fil, niv, res);

    }

}

