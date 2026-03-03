package Threads;

public class Traitement extends Thread {
    String nom;

    public Traitement(String nom) {
        this.nom = nom;
    }
    public void affiche ()
    {
        int i=0;
        while (i<10){
            i++;
            System.out.println("Treatement numero "+i+"du thread"+nom);

        }
    }
}

