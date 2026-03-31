package ClientSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Start Client ..");
        try {
            Socket s=new Socket("127.0.0.1",9004);
            System.out.println("Im connected to the server");

            //lecture
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line=br.readLine();
            System.out.println(line);
            //envoie
            Scanner sc=new Scanner(System.in);
            String id= sc.nextLine();

            PrintWriter pw =new PrintWriter(s.getOutputStream());
            pw.println(id);
            pw.flush();

        }catch (IOException e){
            System.out.println("Error Server"+e.getMessage());
        }

    }
}
