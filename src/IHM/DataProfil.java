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
}