package ma.ensa.meryem.taikokgestionabsences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class choisirFichierClasse extends AppCompatActivity {


    DBHelper db = new DBHelper(this);
    private Button chargerFichier;
    private Button AfficherEtudiants;
    private Button AfficherAbsences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        chargerFichier = (Button) findViewById(R.id.ChargementFichier);
        AfficherEtudiants = (Button) findViewById(R.id.bottomListAbsence);
        AfficherAbsences = (Button) findViewById(R.id.bottomMarquerAbsence);


        chargerFichier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.ajouterEtudiants(choisirFichier());
            }
        });


        AfficherAbsences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AfficherEtudiants.class);
                startActivity(intent);
            }
        });

        AfficherEtudiants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AfficherAbsences.class);
                startActivity(intent);
            }
        });


    }

    private List<Etudiant> choisirFichier() {
        List<Etudiant> ListeEtudiants = new ArrayList<>();

        try
        {
            InputStream inputStreamstream = getAssets().open("listeEtudiants.txt");

            InputStreamReader inputStreamReader = new InputStreamReader(inputStreamstream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String ligneRead;
            String[] champs;
            while (( ligneRead = bufferedReader.readLine()) != null)
            {
                stringBuilder = new StringBuilder();
                stringBuilder.append(ligneRead);
                champs = stringBuilder.toString().split(" ");
                int id = Integer.parseInt(champs[0]);
                ListeEtudiants.add(new Etudiant(id,champs[1],champs[2],champs[3]));


            }
            Toast.makeText(choisirFichierClasse.this,"Liste chargée" , Toast.LENGTH_LONG).show();
            inputStreamstream.close();
        }
        catch (Exception e)
        {
            Toast.makeText(choisirFichierClasse.this,"Erreur dans le chargement du fichier", Toast.LENGTH_LONG).show();
        }

        return ListeEtudiants;
    }

}
