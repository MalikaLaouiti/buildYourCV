package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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

        }catch (IOException e){
            System.out.println("Error Server"+e.getMessage());
        }

    }
}
