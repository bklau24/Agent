package agent;

import java.util.*;
import java.io.*;
import java.net.*;

public class Kliens{
    
    private static AgentThread agent;
    
    public Kliens(AgentThread agent){
        this.agent = agent;
    }
    
    public static void main(String[] args) throws Exception {
        
        int port = Integer.parseInt(args[0]);
        int t1 = Integer.parseInt(args[1]);
        int t2 = Integer.parseInt(args[2]);
        Random r = new Random();
        Map<String, Integer> agents = new HashMap();
        int otherTeam;
        List<String> secrets = new LinkedList<>();
        int otherNumber;
        Map<String, Integer> wrongNumbers = new HashMap();
        
        final int team = agent.getTeam();
        final int number = agent.getNumber();
        final List<String> names = agent.getNames();
        final String secretWord = agent.getSecret();
        
        System.out.println("Creating socket on port " + port);
        
        while (true) {
            Socket s = new Socket("localhost", port);
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            Scanner sc = new Scanner(s.getInputStream());
            /*
            System.out.println("[client] Agent's number: " + number);
            System.out.println("[client] Agent's secret: " + secretWord);
            System.out.println("[client] Agent's team: " + team);
            for(String name : names){
                System.out.println("[client] Agent's names: " + name);
            } */
            
            System.out.println("[client on port " + port +  "] Agent's secret: " + secretWord);

            //megkapja a szerver álnevét, majd megnézi a mapben, hogy már találkozott-e vele
            //ha igen, akkor tudja, hogy melyik ügynökséghez tartozik, ha nem, akkor tippel
            String serverName = sc.nextLine();
            //System.out.println("[client] server agent name: " + serverName);
            boolean contains = agents.containsKey(serverName);
            if(contains){
                otherTeam = agents.get(serverName);
                pw.println(otherTeam);
                pw.flush();
            }
            else {
                otherTeam = r.nextInt((2 - 1) + 1) + 1;
                pw.println(otherTeam);
                pw.flush();
            }
            
            //ha nem tudta, és helyes a tipp, akkor lementi, hogy az adott név melyik ügynökséghez tartozik
            //ezután a kliens is elküldi az OK szöveget, ha azonos ügynökséghez tartoznak
            //majd mindketten elküldenek egy-egy titkos szót
            String answer = sc.nextLine();
            System.out.println("[client on port " + port + "] " + answer);
            if(answer.equals("OK")){
                agents.put(serverName, otherTeam);
                if(team == otherTeam){
                    pw.println("OK");
                    pw.println(secretWord);
                    pw.flush();
                    String otherSecret = sc.nextLine();
                    secrets.add(otherSecret);
                }
                //ha másik ügynökséghez tartozik, elküldi hogy ???, majd tippel, hogy mi lehet a másik ügynök sorszáma
                //ha már találkozott vele, akkor olyan tippet nem ad, ami biztos rossz
                else {
                    pw.println("???");
                    if(contains){ 
                        otherNumber = r.nextInt((10 - 1) + 1) + 1;
                        pw.println(otherNumber);
                        pw.flush();
                        //ha bontja a kapcsolatot, akkor nem jó a szám, és hozzá kell adni a maphez
                        //ha jó a szám, akkor véletlenszerűen küld egy titkot, de csak olyat, amit még nem árult el
                    }
                    else {
                        otherNumber = r.nextInt((10 - 1) + 1) + 1;
                        pw.println(otherNumber);
                        pw.flush();
                    }
                }
            }
            
            for(String ss : secrets){
                System.out.println("[client on port " + port + "] other's secret: " + ss);
            }
	}
    }
}