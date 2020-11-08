package ma.ensa.meryem.taikokgestionabsences;

import java.io.Serializable;

public class Etudiant implements Serializable {

    private int idEtudiant;
    private String CNEEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    private boolean checked=false;



    public Etudiant(int id, String CNE, String nom, String prenom) {
        this.idEtudiant = id;
        this.CNEEtudiant = CNE;
        this.nomEtudiant = nom;
        this.prenomEtudiant = prenom;
    }




    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int id) {
        this.idEtudiant = id;
    }

    public String getCNEEtudiant() {
        return CNEEtudiant;
    }

    public void setCNEEtudiant(String CNE) {
        this.CNEEtudiant = CNE;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNom(String nom) {
        this.nomEtudiant = nom;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    public void setPrenomEtudiant(String prenom) {
        this.prenomEtudiant = prenom;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
