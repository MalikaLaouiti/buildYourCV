package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HandleClient extends  Thread{
    Socket s;

    public HandleClient(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {


        try {
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println("Envoyer votre ID");
            pw.flush();
            //lecture
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String id = br.readLine();
            System.out.println(id);

            CustomSocket custom=new CustomSocket(id,s);
            SocketManager.list_Of_Socket.add(custom);

            new Read(br,id).start();

//            Scanner sc=new Scanner(System.in);
//            Write writemsg=new Write(pw,sc);
//            writemsg.run();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
