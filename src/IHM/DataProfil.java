package IHM;

import java.util.ArrayList;

public class DataProfil {
    public static ArrayList<Profil> data = new ArrayList<>();

    public static void addProfil(Profil p) {
        data.add(p);
    }

    public static void updateProfil(Profil p) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getPseudo().equals(p.getPseudo())) {
                data.set(i, p); // Remplacer l'ancien profil par le nouveau
                return;
            }
        }

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