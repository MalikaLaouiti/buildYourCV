package ClientSocket;

import java.io.BufferedReader;
import java.io.IOException;

public class Read extends Thread{
    BufferedReader br;

    public Read(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String line = br.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error Server"+e.getMessage());
        }

    }
}
