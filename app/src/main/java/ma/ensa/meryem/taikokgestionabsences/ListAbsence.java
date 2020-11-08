package ma.ensa.meryem.taikokgestionabsences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static ma.ensa.meryem.taikokgestionabsences.AfficherAbsences.p;

public class ListAbsence extends AppCompatActivity {

    private Button ButtonSuppressionAbsence,ButtonAjoutObservation;
    DBHelper db = new DBHelper(this);
    private ListView ListViewAbsences,ListViewEntetEtudiant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);
        ButtonSuppressionAbsence =findViewById(R.id.SupprimerAbsence);
        ButtonAjoutObservation=findViewById(R.id.AjoutObservation);
        ListViewAbsences = (ListView) findViewById(R.id.ListAbsPourEtudiant);
        ListViewEntetEtudiant = (ListView) findViewById(R.id.EtudiantConcerne);
        retournerAbsences();
        entetEtudiant(p);

        ButtonSuppressionAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SupprimerAbsence.class);
                startActivity(intent);
            }
        });

        ButtonAjoutObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MettreAjourAbsence.class);
                startActivity(intent);
            }
        });


    }
    private void retournerAbsences() {
        ArrayList<String> listeAbs = db.retournerListeAbsences(p);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listeAbs);
        ListViewAbsences.setAdapter(arrayAdapter);

    }
    private void entetEtudiant(int id) {
        ArrayList<String> ListeEntet = db.afficherEntetEtudiant(id);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ListeEntet);
        ListViewEntetEtudiant.setAdapter(arrayAdapter);


    }







}
