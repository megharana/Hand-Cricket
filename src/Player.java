import java.util.*;


public class Player {
	
	public int score;     		//Score after each round
	public boolean batting;  	//batting turn
	public char name;    		//Name of team
	public int target;   		//target set for chasing team
	public int run;
	
	public Player(char c){
		score =0;
		batting = false;
		run = 0;
		name = c;
		target = 0;
	}	
	public int getRun() {
		int[] runs = {0, 1, 2, 3, 4, 6};      	//runs Array to provide runs excluding "5"
		Random rand = new Random(); 
		int randRun = rand.nextInt(6);
		return runs[randRun];
	}
	
	public static void match(int rounds, Player currentPlayer, Player nextPlayer) {
		
		Player tempPlayer = new Player('\0'); 	//used to switch between two teams
		for(int round = 1; round <= rounds; round++) {  
			for(int ball = 0; ball < 6; ball++) {
				
				currentPlayer.batting = true;
				currentPlayer.run = currentPlayer.getRun();
				nextPlayer.run = nextPlayer.getRun();
				if(currentPlayer.run != nextPlayer.run)
				
					if(currentPlayer.name == 'A' ) {
						
						currentPlayer.score = currentPlayer.score + currentPlayer.run;
						System.out.println(currentPlayer.name +" throws "+currentPlayer.run+" "+nextPlayer.name+" throws "+nextPlayer.run+" "+ currentPlayer.name +"'s score is "+currentPlayer.score);
						if(currentPlayer.score < currentPlayer.target) {
							System.out.println("target"+currentPlayer.target);
							break;
						}
					}
					else {
						
						currentPlayer.score = currentPlayer.score + nextPlayer.run;
//						System.out.println("A throws "+runs[randRunA]+" B throws "+runs[randRunB]+" B's score is "+currentPlayer.score);
						System.out.println(currentPlayer.name +" throws "+currentPlayer.run+" "+nextPlayer.name+" throws "+nextPlayer.run+" "+ currentPlayer.name +"'s score is "+currentPlayer.score);
						if(currentPlayer.score < currentPlayer.target)
							break;
					}
				
				else if(currentPlayer.run == nextPlayer.run) {
//					System.out.println("A throws "+runs[randRunA]+" B throws "+runs[randRunB]+" "+currentPlayer.name+" is out ");
					System.out.println(currentPlayer.name +" throws "+currentPlayer.run+" "+nextPlayer.name+" throws "+nextPlayer.run+" "+ currentPlayer.name +" is out");
					break;
				}
				
			}
			System.out.println(round+" Round over");
			currentPlayer.target = currentPlayer.score;  //setting current payer score as target for opponent
			tempPlayer = currentPlayer;
			currentPlayer = nextPlayer;
			nextPlayer = tempPlayer;
		 
		}
		//deciding the winner and TIE between teams
		if(currentPlayer.score > nextPlayer.score)       
			System.out.println(currentPlayer.name+" is winner");
		else if(currentPlayer.score < nextPlayer.score)
			System.out.println(nextPlayer.name+" is winner");
		else
			System.out.println("TIE");
	}
	

	public static void main(String[] args) {
		Scanner scanRound = new Scanner(System.in);
		System.out.println("Enter the Number of rounds you need to play\t");
		int rounds = scanRound.nextInt();
		
		Player A = new Player('A');
		Player B = new Player('B');
		try {
			Scanner scanPlayer = new Scanner(System.in);
			System.out.println("Enter the Player who won the toss between A and B\t");
			char playerFirst = scanPlayer.next().charAt(0);
		
			if(playerFirst == 'A'|| playerFirst == 'B') {
				if(playerFirst == 'A') 
					match(rounds, A, B);
				else if(playerFirst == 'B') 
					match(rounds, B, A);
			}
			else
				throw new RuntimeException("Invalid input only char A and B expected");
        
				
			scanPlayer.close();
			scanRound.close();
		}	
		catch(RuntimeException re){
	        System.out.print(re.getMessage());
            //System.out.println();
		}
		
	}

}

