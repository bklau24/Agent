package agent;

import java.util.*;
import java.io.*;
import java.net.*;

public class Kliens{
    
    private static AgentThread agent;
    private List<String> secrets;
    
    public Kliens(AgentThread agent){
        this.agent = agent;
    }
    
    public static void main(String[] args) throws Exception {
        
        int port = Integer.parseInt(args[0]);
        int t1 = Integer.parseInt(args[1]);
        int t2 = Integer.parseInt(args[2]);
        Random r = new Random();
        
        System.out.println("Creating socket " + "on port " + port);
        
        while (true) {
			Socket s = new Socket("localhost", port);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        
                        System.out.println("[client] Agent's number: " + agent.getNumber());
                        System.out.println("[client] Agent's secret: " + agent.getSecret());
                        System.out.println("[client] Agent's team: " + agent.getTeam());
                        for(String name : agent.getNames()){
                            System.out.println("[client] Agent's names: " + name);
                        }
                        
			BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
			String userInput = userInputBR.readLine();

			out.println(userInput);

			//System.out.println("server says:" + br.readLine());

			if ("exit".equalsIgnoreCase(userInput)) {
				s.close();
				break;
			}
		}

    }
    
}