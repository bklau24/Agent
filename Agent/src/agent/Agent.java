package agent;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Agent {
    
    private final int team;
    private final int number;
    private final List<String> names = new LinkedList<>();
    private String secretWord;
    private final String filename;
    
    public Agent(int n, int nb, String fname) throws FileNotFoundException{
        this.filename = fname;
        this.team = n;
        this.number = nb;
        readData();
    }
    
    private void readData() throws FileNotFoundException {
	Scanner scanner = new Scanner(new File(filename));
	while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            cutLines(line);
	}
	scanner.close();
    }
    
    public void cutLines(String line){
        String[] lineParts = line.split("\\s+");
	if(lineParts.length == 1){
            secretWord = lineParts[0];
        }
        else {
            for(int i = 0; i < lineParts.length; i++){
                String name = lineParts[i];
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
}