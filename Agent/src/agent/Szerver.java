package agent;

import java.util.*;
import java.io.*;
import java.net.*;

public class Szerver{
    
    private static AgentThread agent;
    private List<String> secrets;
    
    public Szerver(AgentThread agent){
        this.agent = agent;
    }
    
    public static void main(String[] args) throws Exception {
        
        int port = Integer.parseInt(args[0]);
        int t1 = Integer.parseInt(args[1]);
        int t2 = Integer.parseInt(args[2]);
        
        System.out.println("Creating server socket on port " + port);
        
        while(true){
            ServerSocket server = new ServerSocket(port);
            Socket client = server.accept();
            System.out.println("Connected on port " + port);
            Scanner sc = new Scanner(client.getInputStream());
            //PrintWriter pw = new PrintWriter(client.getOutputStream());
            OutputStream os = client.getOutputStream();
            System.out.println("[server] Agent's number: " + agent.getNumber());
            System.out.println("[server] Agent's secret: " + agent.getSecret());
            System.out.println("[server] Agent's team: " + agent.getTeam());
            for(String name : agent.getNames()){
                System.out.println("[server] Agent's names: " + name);
            }
            
            
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("What's you name?");

			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String str = br.readLine();

			pw.println("Hello, " + str);
			pw.close();
			client.close();

			System.out.println("Just said hello to:" + str);
        }
	}
}