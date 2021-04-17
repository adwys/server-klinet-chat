package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;

public class ClientHandler implements Runnable {
    Socket client;
    BufferedReader buff;
    PrintWriter pW;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        buff = new BufferedReader(new InputStreamReader(client.getInputStream()));
        pW = new PrintWriter(client.getOutputStream(),true);
    }

    @Override
    public void run(){
        try {
            while (true){
                String rq = buff.readLine();
                System.out.println("[Client]: " + rq);
                pW.println("otrzymana od servera notyfikacja " + rq + " czas odbioru servera " + Instant.now());
            }
        }catch (Exception e){
            System.out.println("err");
        }finally {
            try {
                buff.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pW.close();
        }

    }



}
