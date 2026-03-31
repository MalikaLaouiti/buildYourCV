package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Read extends Thread{
    BufferedReader br;

    public Read(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = br.readLine();
                System.out.println(msg);
                SocketManager.diffuserMessage(msg);
            }
        } catch (IOException e) {
            System.out.println("Error Server"+e.getMessage());
        }

    }
}
