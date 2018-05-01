package agent;

import java.util.*;

public class AgentMain {

    public static void main(String[] args) throws Exception {
                
        List<AgentThread> agents = new LinkedList<>();
        String filename;
        final int t1 = 5000;
        final int t2 = 8000;
        
	System.out.println("Az elso ugynokseg ugynokeinek szama: ");
	Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
            while ( n > 10){
		System.out.println("Az ugynokok szama max 10 lehet. Adjon meg új számot!");
		sc = new Scanner(System.in);
                n = sc.nextInt();
            }
		
	System.out.println("A masodik ugynokseg ugynokeinek szama: ");
	int m = sc.nextInt();
            while ( m > 10){
		System.out.println("Az ugynokok szama max 10 lehet. Adjon meg új számot!");
		sc = new Scanner(System.in);
                m = sc.nextInt();
            }
                
        for(int i = 1; i <= n; i++){        //az első ügynökség ügynökeinek létrehozása (szálak)
            filename = "agent1-" + i + ".txt";
            AgentThread agent = new AgentThread(1, i, filename, t1, t2);
            agent.start();
            agents.add(agent);
        }
        
        for(int i = 1; i <= m; i++){        //a második ügynökség ügynökeinek létrehozása (szálak)
            filename = "agent2-" + i + ".txt";
            AgentThread agent = new AgentThread(2, i, filename, t1, t2);
            agent.start();
            agents.add(agent);
        }
        
        for(AgentThread agent : agents){
            agent.join();
        }
    }	
}