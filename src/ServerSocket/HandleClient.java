package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleClient extends  Thread{
    Socket s;

    public HandleClient(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        //ecriture

        try {
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println("Envoyer votre ID");
            pw.flush();
            //lecture
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String id = br.readLine();
            System.out.println(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
