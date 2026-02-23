package IHM;

import java.util.ArrayList;

public class DataProfil {
    public static ArrayList<Profil> data = new ArrayList<>();

    public static void addProfil(Profil p) {
        data.add(p);
    }

    public static ArrayList<Profil> getData() {
        return data;
    }

    public static Profil getProfilByPseudo(String pseudo) {
        for (Profil p : data) {
            if (p.getPseudo().equals(pseudo)) {
                return p;
            }
        }
        return null;
    }
}