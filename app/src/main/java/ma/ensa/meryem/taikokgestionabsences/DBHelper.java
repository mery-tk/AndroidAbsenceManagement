package ma.ensa.meryem.taikokgestionabsences;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int BD_VER = 10;
    private static final String BD_NOM= " baseDonne-Gestion-Absence";



    private static final String TABLE_ETUDIANTS = "ETUDIANTS";
    private static final String CHAMP_ID = "id";
    private static final String CHAMP_CNE = "cne";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_PRENOM = "prenom";

    private static final String TABLE_ABSENCE = "ABSENCE";
    private static final String CHAMP_ID_ABSENCE = "idAbs";
    private static final String CHAMP_ID_ETUDIANT = "idEtudiant";
    private static final String CHAMP_OBSERVATION="observation";
    private static final String CHAMP_DATE = "date";

    private static final String TABLE_CLASSE="CLASSE";
    private static final String CHAMP_ID_CLASSE="id";
    private static final String CHAMP_FILIERE="filiere";
    private static final String CHAMP_NIVEAU="niveau";
    private static final String CHAMP_RESPONSABLE="responsable";


    private String CREER_TABLE_ETUDIANT = "CREATE TABLE IF NOT EXISTS " + TABLE_ETUDIANTS
            + "("
            + CHAMP_ID + " INTEGER PRIMARY KEY,"
            + CHAMP_CNE + " TEXT, "
            + CHAMP_NOM + " TEXT, "
            + CHAMP_PRENOM + " TEXT "
            + ")";

    private String CREER_TABLE_CLASSE="CREATE TABLE IF NOT EXISTS " + TABLE_CLASSE
            +"("
            + CHAMP_ID_CLASSE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CHAMP_FILIERE + " TEXT, "
            + CHAMP_NIVEAU + " TEXT, "
            + CHAMP_RESPONSABLE + " TEXT "
            + ")";

    private String CREER_TABLE_ABSENCE = "CREATE TABLE IF NOT EXISTS " + TABLE_ABSENCE
            +"("
            +CHAMP_ID_ABSENCE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +CHAMP_ID_ETUDIANT + " INTEGER,"
            +CHAMP_DATE + " DATETIME DEFAULT CURRENT_DATE,"
            + CHAMP_OBSERVATION + " TEXT, "
            +" FOREIGN KEY (" + CHAMP_ID_ETUDIANT + ") REFERENCES "
            + TABLE_ETUDIANTS +" (" + CHAMP_ID + ")"
            + ")";





    public DBHelper(Context context) {
        super(context, BD_NOM, null, BD_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREER_TABLE_ETUDIANT);
        db.execSQL(CREER_TABLE_ABSENCE);
        db.execSQL(CREER_TABLE_CLASSE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_ETUDIANTS  );
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_ETUDIANTS );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSE);

        onCreate(db);
    }
//////1///////////
    public List<Etudiant> retournerListeEtudiant(){
        List<Etudiant> ListeEtudiants = new ArrayList<Etudiant>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_ETUDIANTS,null);
        res.moveToFirst();
        while (res.isAfterLast() == false){

            int idEtudiant = res.getInt(0);
            String cneEtudiant = res.getString(1);
            String nomEtudiant = res.getString(2);
            String prenomEtudiant = res .getString(3);

            ListeEtudiants.add(new Etudiant(idEtudiant, cneEtudiant, nomEtudiant, prenomEtudiant));
            res.moveToNext();
        }
        res.close();
        return ListeEtudiants;
    }
/////////2

    public void ajouterEtudiants(List<Etudiant> etudiants) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for(Etudiant etu : etudiants){

            int idEtudiant = etu.getIdEtudiant();
            String cneEtudiant = etu.getCNEEtudiant();
            String nomEtudiant = etu.getNomEtudiant();
            String prenomEtudiant = etu.getPrenomEtudiant();

            contentValues.put(CHAMP_ID, idEtudiant);
            contentValues.put(CHAMP_CNE, cneEtudiant);
            contentValues.put(CHAMP_NOM, nomEtudiant);
            contentValues.put(CHAMP_PRENOM, prenomEtudiant);

            db.insert(TABLE_ETUDIANTS, null,contentValues);
        }
    }

  ////////////////////3
    public ArrayList afficherEntetEtudiant(int id) {
        ArrayList ListeEtudiant = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curseur = db.rawQuery("SELECT * FROM " + TABLE_ETUDIANTS + " where id = '" + id + "'" , null);
        curseur.moveToFirst();
        while (curseur.isAfterLast() == false) {
            String nomEtudiant = curseur.getString(curseur.getColumnIndex("nom"));
            String prenomEtudiant = curseur.getString(curseur.getColumnIndex("prenom"));
            ListeEtudiant.add("                    " +  nomEtudiant +" "  + prenomEtudiant) ;
            curseur.moveToNext();
        }
        return ListeEtudiant;
    }

 //////////////////////4
    public ArrayList retournerListeClasses(){
        ArrayList Listeclasses = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_CLASSE,null);
        res.moveToFirst();
        while (res.isAfterLast() == false){

            String idClasse = res.getString(0);
            String filiereClasse = res.getString(1);
            String niveauClasse = res.getString(2);
            String responsableClasse = res .getString(3);

            Listeclasses.add(idClasse + "                         " + filiereClasse + "(" + niveauClasse +")               " + responsableClasse);
            res.moveToNext();
        }
        return Listeclasses;
    }

 /////////////////5
    public  Boolean ajouterClasse(String filiere, String niveau,String responsable ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("filiere", filiere);
        contentValues.put("niveau", niveau);
        contentValues.put("responsable", responsable);
        long result = db.insert(TABLE_CLASSE, null,contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }

    }

/////////////////6
    public int supprimerClasse(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CLASSE, "id = ?",new String[] {id});
    }
 ////////////////////////////7

    public Boolean mettreAjourClasse(String id, String filiere, String niveau, String respo) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("filiere", filiere);
        contentValues.put("niveau", niveau);
        contentValues.put("responsable", respo);
        db.update(TABLE_CLASSE, contentValues, "id = ?", new String[] {String.valueOf(id)});
        return true;
    }

///////////////////////////////8


    public ArrayList retournerListeAbsences(int idEtu){
        ArrayList ListeAbsences = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_ABSENCE + " where idEtudiant = '" + idEtu + "'" , null);
        res.moveToFirst();
        while (res.isAfterLast() == false){

            int idAbsence = res.getInt(0);
            String dateAbsence = res.getString(2);
            String obserrvationAbsence=res.getString(3);



            ListeAbsences.add(idAbsence+"                      "+obserrvationAbsence+"                   "+dateAbsence);
            res.moveToNext();
        }
        return ListeAbsences;
    }

 ///////////////////////////9
    public boolean marquerAbsence(Etudiant e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHAMP_ID_ETUDIANT, e.getIdEtudiant());

        long resultat = db.insert(TABLE_ABSENCE, null,contentValues);

        if (resultat == -1) {
            return false;
        }else {
            return true;
        }
    }
////////////////////////10
    public int supprimerAbsence(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("ABSENCE", "idAbs = ?",new String[] {id});
    }
////////////////////////11

    public Boolean AjoutObservation(String id, String Observation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idAbs", id);
        contentValues.put("observation", Observation);
        db.update("ABSENCE", contentValues, "idAbs = ?", new String[] {String.valueOf(id)});
        return true;
    }

  ////////////////////////////////12

    public int CompterNombreAbsence(Etudiant etudiant){
        int calculerNombreAbsence = 0;

            String compterQuery = "SELECT * FROM " + TABLE_ABSENCE + " WHERE "
                    + CHAMP_ID_ETUDIANT +" = "
                    + String.valueOf(etudiant.getIdEtudiant());
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(compterQuery, null);
        calculerNombreAbsence = cursor.getCount();
            cursor.close();
            db.close();


        return calculerNombreAbsence;

    }

}
