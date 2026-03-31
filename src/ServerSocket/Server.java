package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int nbClient=0;
        System.out.println("Starting Server ..");
        try {
            ServerSocket server =new ServerSocket(9004);
            System.out.println("Server waiting");
            while (nbClient<3) {
                Socket s = server.accept();
                System.out.println("client connected"+nbClient);
                nbClient++;

                HandleClient handler=new HandleClient(s);
                handler.start();
            }
        }catch (IOException e){
            System.out.println("Error Server"+e.getMessage());
        }
        System.out.println("End server processing");
    }

}
