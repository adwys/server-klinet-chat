package server;
import java.io.*;
import java.net.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.*;
import java.util.Map;
import static java.lang.Integer.parseInt;


public class klient {

    public static void main( String[] args ) {
        Socket s;
        BufferedReader input = null;
        BufferedReader keyboard = null;
        PrintWriter out = null;
        try {
            s = new Socket("localhost", 4888);
            input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(s.getOutputStream(), true);
        }catch (Exception e){
            System.out.println("Connection failed");
            System.exit(1);
        }
        try {
            while (true) {
                System.out.print("> ");
                String command = keyboard.readLine();

                out.println(command);

                String serverResponse = input.readLine();
                System.out.println("[SERVER]: " + serverResponse + " czas klienta " + Instant.now());
            }
        }catch (Exception e){
            System.out.println("IO error");
        }

    }

}
