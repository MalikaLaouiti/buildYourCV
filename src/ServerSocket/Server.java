package ServerSocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Starting Server ..");
        try {
            ServerSocket server =new ServerSocket(9004);
            System.out.println("Server waiting");
            Socket s=server.accept();
            System.out.println("client connected");

            //ecriture
            PrintWriter pw =new PrintWriter(s.getOutputStream());
            pw.println("Envoyer votre ID");
            pw.flush();
        }catch (IOException e){
            System.out.println("Error Server"+e.getMessage());
        }
        System.out.println("End server processing");
    }

}
