package ma.ensa.meryem.taikokgestionabsences;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AfficherEtudiants extends AppCompatActivity {

    public List<Etudiant> listeEtudiant;
    DBHelper db = new DBHelper(this);
    private ListView listeEtudiantListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etudiant);
        listeEtudiantListView = (ListView) findViewById(R.id.ListEtud);
        retournerListEtudiants();
    }

    public void retournerListEtudiants() {
        listeEtudiant = db.retournerListeEtudiant();

        EtudiantAdapter adapter = new EtudiantAdapter(this,listeEtudiant);

        listeEtudiantListView.setAdapter(adapter);

    }





}
