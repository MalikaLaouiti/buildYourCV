package ServerSocket;

import java.net.Socket;

public class CustomSocket {
    String idClient;
    Socket s;

    public CustomSocket(String idClient, Socket s) {
        this.idClient = idClient;
        this.s = s;
    }
}


