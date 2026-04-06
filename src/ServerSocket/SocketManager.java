package ServerSocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class SocketManager {
    public static ArrayList<CustomSocket> list_Of_Socket=new ArrayList<>();


    public static void diffuserMessage(String id,String msg) {
        if (!msg.startsWith("@")) {
            System.out.println("Format invalide");
            return;
        }
        String[] splitMsg= msg.split(" ",2);
        String destination=splitMsg[0].substring(1);
        msg=splitMsg[1];
        if (destination.equals("all")) {
            for (int i = 0; i < list_Of_Socket.size(); i++) {
                Socket s = list_Of_Socket.get(i).s;
                try {
                    PrintWriter pw = new PrintWriter(s.getOutputStream());
                    pw.println(id + " send: " + msg + "\nat " + new Date().toLocaleString());
                    pw.flush();
                } catch (IOException e) {
                    System.out.println("Error Server" + e.getMessage());
                }
            }
        }
        else {
            for (int i = 0; i < list_Of_Socket.size(); i++) {
                Socket s = list_Of_Socket.get(i).s;
                if (list_Of_Socket.get(i).idClient.equals(destination) ){
                    try {
                        PrintWriter pw = new PrintWriter(s.getOutputStream());
                        pw.println(id + " send: " + msg + "\nat " + new Date().toLocaleString());
                        pw.flush();
                    } catch (IOException e) {
                        System.out.println("Error Server" + e.getMessage());
                    }
                }
            }
        }
    }
}

