package agent;

import java.util.*;
import java.io.*;
import java.net.*;

public class Szerver{
    
    private static AgentThread agent;
    
    public Szerver(AgentThread agent){
        this.agent = agent;
    }
    
    public static void main(String[] args) throws Exception {
        
        int port = Integer.parseInt(args[0]);
        int t1 = Integer.parseInt(args[1]);
        int t2 = Integer.parseInt(args[2]); 
        Random r = new Random();
        List<String> secrets = new LinkedList<>();
        
        final int team = agent.getTeam();
        final int number = agent.getNumber();
        final List<String> names = agent.getNames();
        final String secretWord = agent.getSecret();
        
        System.out.println("Creating server socket on port " + port);
        
        while(true){
            ServerSocket server = new ServerSocket(port);
            Socket client = server.accept();
            System.out.println("Connected on port " + port);
            Scanner sc = new Scanner(client.getInputStream());
            PrintWriter pw = new PrintWriter(client.getOutputStream()); 
            /*
            System.out.println("[server] Agent's number: " + number);
            System.out.println("[server] Agent's secret: " + secretWord);
            System.out.println("[server] Agent's team: " + team);
            for(String name : names){
                System.out.println("[server] Agent's names: " + name); 
            } */
            
            System.out.println("[server on port " + port + "] Agent's secret: " + secretWord);
           
            //a szerver elküldi a kliensnek az álnevei közül az egyiket
            int r1 = r.nextInt((3 - 1) + 1) + 1;
            String send = names.get(r1 - 1);
            pw.println(send);
            pw.flush();
            
            //megkapja a kliens tippjét, hogy a szerver melyik ügynökséghez tartozhat
            //ha helyes a tipp, elküldi a kliensnek az OK szöveget, ha nem, bontja a kapcsolatot
            int tip = sc.nextInt();
            System.out.println("[server on port " + port + "] client's tip: " + tip);
            if(tip == team){
                pw.println("OK");
                pw.flush();
            }
            else {
                client.close();
                System.out.println("Connection broken on port " + port + " [Wrong team tip.]");
            }
            
            //ha azonos ügynökséghez tartoznak, akkor mindketten elküldenek egy-egy titkos szöveget, és felveszik
            //az ismert titkaik közé
            String answer = sc.nextLine();
            if(answer.equals("OK")){
                String otherSecret = sc.nextLine();
                secrets.add(otherSecret);
                pw.println(secretWord);
                pw.flush();
            }
            //ha másik ügynökség, akkor a kliens tippel, hogy a szervernek mi a sorszáma, ha téves, bontja a kapcsolatot
            if(answer.equals("???")){
                int clientTip = sc.nextInt();
                if(clientTip == number){
                    //küld egy általa ismert titkot
                }
                else {
                    client.close();
                    System.out.println("Connection broken on port " + port + " [Wrong number tip.]");
                }
            }
            
            for(String s : secrets){
                System.out.println("[server on port " + port + "] other's secret: " + s);
            }
            
        }
    }
}