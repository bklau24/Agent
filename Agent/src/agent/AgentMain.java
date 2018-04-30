package agent;

import java.util.*;

public class AgentMain {
    public static void main(String[] args) throws Exception {
		
        //int t1 = 5000;
	//int t2 = 8000;
        //Random rand = new Random();
                
        List<Agent> agents = new LinkedList<>();
        String filename;
        
	System.out.println("Az elso ugynokseg ugynokeinek szama: ");
	Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
            if ( n > 10){
		System.out.println("Az ugynokok szama max 10 lehet.");
		return; //javítani kell rajta
            }
		
	System.out.println("A masodik ugynokseg ugynokeinek szama: ");
	int m = sc.nextInt();
            if ( m > 10){
		System.out.println("Az ugynokok szama max 10 lehet.");
		return;
            }
                
        for(int i = 1; i <= n; i++){
            filename = "agent1-" + i + ".txt";
            Agent agent = new Agent(1, i, filename);
            System.out.println(agent.getSecret());
            System.out.println(agent.getNames());
            agents.add(agent);
        }
        
        for(int i = 1; i <= m; i++){
            filename = "agent2-" + i + ".txt";
            Agent agent = new Agent(2, i, filename);
            agents.add(agent);
        }
                
               
	/*
            for(int i = 0; i < n; i++ ){
                int t = rand.nextInt((t2 - t1) + 1) + t1;
                new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println("Elso ugynok");
			int port = rand.nextInt(20100 + (20000 - 20100) + 1);
			int tmp = rand.nextInt(20100 + (20000 - 20100) + 1);
			while (port == tmp){
                            tmp = rand.nextInt(20100 + (20000 - 20100) + 1);
                        }
			int port2 = tmp;
                        Szerver.main(new String[] {Integer.toString(port2)});
                        Kliens.main(new String[] {Integer.toString(port)});
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception ex) {
                        Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }.start();
                Thread.sleep(t);
            }
            
            for(int i = 0; i < m; i++ ){
                int t = rand.nextInt((t2 - t1) + 1) + t1;
                new Thread() {
                @Override
                public void run() {
                    try {
                       System.out.println("Masodik ugynok");
			int port = rand.nextInt(20100 + (20000 - 20100) + 1);
			int tmp = rand.nextInt(20100 + (20000 - 20100) + 1);
			while (port == tmp){
                            tmp = rand.nextInt(20100 + (20000 - 20100) + 1);
                        }
			int port2 = tmp;
                        Szerver.main(new String[] {Integer.toString(port2)});
                        Kliens.main(new String[] {Integer.toString(port)});
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception ex) {
                        Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }.start();
                Thread.sleep(t);
            }*/
    }	
}