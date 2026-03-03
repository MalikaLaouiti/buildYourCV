package Threads;

public class Test {
    public static void main(String[] args) {
        System.out.println("Debut PP");
        Traitement ta=new Traitement("A");
        Traitement tb=new Traitement("B");
        ta.start();
        tb.start();
        System.out.println("Fin PP");
    }
}
