package ClientSocket;


import java.io.PrintWriter;
import java.util.Scanner;

public class Write extends Thread{
    PrintWriter pw;
    Scanner sc;

    public Write(PrintWriter pw, Scanner sc) {
        this.pw = pw;
        this.sc = sc;
    }

    @Override
    public void run() {
        while (true) {
            String msg=sc.nextLine();
            pw.println(msg);
            pw.flush();
        }
    }
}
