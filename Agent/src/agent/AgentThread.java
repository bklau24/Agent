package agent;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgentThread extends Thread {
    
    private final int team;
    private final int number;
    private final List<String> names = new LinkedList<>();
    private String secretWord;
    private final String filename;
    private final Random rand = new Random();
    private final int t1;
    private final int t2;
    private final int t;
    
    
    
    public AgentThread(int n, int nb, String fname, int min, int max) throws FileNotFoundException, InterruptedException{
        this.filename = fname;
        this.team = n;
        this.number = nb;
        this.t1 = min;
        this.t2 = max;
        this.t = rand.nextInt((t2 - t1) +1) + t1;
        int port = rand.nextInt((20100 - 20000) +1) + 20000;
        readData();
        
        Szerver sz = new Szerver(this);

            Thread server = new Thread(this) {
                @Override
                public void run() {
                    try {
                        //int port = rand.nextInt((20100 - 20000) +1) + 20000;
                        Szerver.main(new String[] {Integer.toString(port), Integer.toString(t1), Integer.toString(t2)});
                    } catch (Exception ex) {}
                }
            };
            
        Kliens k = new Kliens(this);
            
            Thread client = new Thread(this){
                @Override
                public void run() {
                    try {
                        int port2 = rand.nextInt((20100 - 20000) +1) + 20000;
                        while(port2 == port){
                            port2 = rand.nextInt((20100 - 20000) +1) + 20000;
                        }
                        Kliens.main(new String[] {Integer.toString(port2), Integer.toString(t1), Integer.toString(t2)});
                    } catch (Exception ex) {}
                }
            };
        
            server.start();
            client.start();
        
    }
    
    private void readData() throws FileNotFoundException {
	Scanner scanner = new Scanner(new File(filename));
	while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            cutLines(line);
	}
	scanner.close();
    }
    
    public void cutLines(String line){ 
        String[] lineParts = line.split("\\s+");
	if(lineParts.length == 1){
            this.secretWord = lineParts[0];
        }
        else {
            for (String name : lineParts) {
                names.add(name);
            }
        }
    }
    
    public int getTeam(){
        return this.team;
    }
    
    public List<String> getNames(){
        return this.names;
    }
    
    public String getSecret(){
        return this.secretWord;
    }

    public int getNumber(){
        return this.number;
    }
    
    /*
    @Override
    public void run() {
        
        try {
            new Thread(this) {
                @Override
                public void run() {
                    try {
                        Kliens k = new Kliens();
                        int port = rand.nextInt((20100 - 20000) +1) + 20000;
                        Szerver.main(new String[] {Integer.toString(port), Integer.toString(t1), Integer.toString(t2)});
                    } catch (Exception ex) {}
                }
            }.start();
            
            new Thread(this){
                @Override
                public void run() {
                    try {
                        int port = rand.nextInt((20100 - 20000) +1) + 20000;
                        Kliens.main(new String[] {Integer.toString(port), Integer.toString(t1), Integer.toString(t2)});
                    } catch (Exception ex) {}
                }
            }.start();
            Thread.sleep(t);
        } catch (InterruptedException ex) {}
        
    } */
}