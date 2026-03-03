package Threads;

public class Test {
    public static void main(String[] args) {
        System.out.println("Debut PP");
        Traitement ta=new Traitement("A");
        Traitement tb=new Traitement("B");
        ta.run();
        tb.run();
        System.out.println("Fin PP");
    }
}
