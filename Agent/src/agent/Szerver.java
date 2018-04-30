package agent;

import java.util.*;
import java.io.*;
import java.net.*;

public class Szerver {
    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(args[0]);
        
        try (
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();
            Scanner sc = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream());
        ) {
            String be = sc.nextLine();
            System.out.println(be);
            pw.println(args[0]);
            pw.flush();
        }
	}
}