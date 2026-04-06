package ServerSocket;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Read extends Thread{
    BufferedReader br;
    String id;

    public Read(BufferedReader br,String id) {
        this.br = br;
        this.id=id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = br.readLine();
                System.out.println(msg);
                SocketManager.diffuserMessage(id,msg);
            }
        } catch (IOException e) {
            System.out.println("Error Server"+e.getMessage());
        }
    }
}
