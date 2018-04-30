package agent;

import java.util.*;
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Kliens {

    public static void main(String[] args) throws RemoteException, FileNotFoundException, IOException, UnknownHostException {
        int port = Integer.parseInt(args[0]);
        
        try (
            Socket s = new Socket("localhost", port);
            Scanner sc = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream());
        ) {
            pw.println(args[0]);
            pw.flush();

			while(sc.hasNextLine()){
				String be = sc.nextLine();
				System.out.print(be);
			}
        }
    }
}