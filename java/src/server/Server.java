package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket client = null;
        BufferedReader buff = null;


        serverSocket = new ServerSocket(4888);

        while (true) {
            client = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(client);
            clients.add(clientHandler);
            pool.execute(clientHandler);
        }

//        System.out.println("koniec pracy servera");

    }
}
