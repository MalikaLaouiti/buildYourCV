package ServerSocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SocketManager {
    public static ArrayList<CustomSocket> list_Of_Socket=new ArrayList<>();


    public static void diffuserMessage(String msg) throws IOException {
        for (int i = 0; i < list_Of_Socket.size(); i++) {
            Socket s=list_Of_Socket.get(i).s;
            try{
                PrintWriter pw =new PrintWriter(s.getOutputStream());
                pw.println(msg);
                pw.flush();
            }catch (IOException e){
                System.out.println("Error Server"+e.getMessage());
            }

        }
    }
}
