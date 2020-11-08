package ma.ensa.meryem.taikokgestionabsences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AfficherAbsences extends AppCompatActivity {

    DBHelper db = new DBHelper(this);
    public static int p;
    public ListView AbsentsListView;
    public List<Etudiant> listeEtudiantAbsents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_absence);

        AbsentsListView = (ListView) findViewById(R.id.absence);


        listeEtudiantAbsents = db.retournerListeEtudiant();

        ListEtudiantAbsentAdapter adapter = new ListEtudiantAbsentAdapter(this,R.layout.activity_row_layout,listeEtudiantAbsents);

        AbsentsListView.setAdapter(adapter);




        AbsentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p = position+1;
                Intent intent = new Intent(getApplicationContext(),ListAbsence.class);
                startActivity(intent);
            }
        });


    }
}
