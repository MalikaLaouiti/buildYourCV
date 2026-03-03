package Threads;

public class Test {
    public static void main(String[] args) {
        //1 thread par defaut pour main
        System.out.println("Debut PP");
        Traitement ta=new Traitement("A");
        Traitement tb=new Traitement("B");

        //creation des thread par start(): 3 thread
        ta.start();//run charge programme dans pile , start cree une pile et charge run pour executer
        tb.start();

        try {
            ta.join();
            tb.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Fin PP");
        //mort de main et fonction A B fonctionne en arriere plan : 2 threads
    }
}
