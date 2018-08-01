/**
 * Plays Greedy Coin game such that the computer never loses.
 * 
 * Preston McIllece's Homework3
 */
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GreedyCoinGame {
	private List <Integer> list = new LinkedList <Integer>();
	private int coin;
	private int humanScore;
	private int computerScore;
	private int previousHumanChoice;
	
	//constructor to create a new game
	public GreedyCoinGame(String file) throws FileNotFoundException {
		
		Scanner inFile = new Scanner(new File(file));

		while (inFile.hasNext()) {
			//reads the coins in
			coin = inFile.nextInt();
			list.add(coin);
			
		}

		inFile.close();
	}

	// prints the coins and their position
	public void printCoins() {
		System.out.println("+++++++++++");
		System.out.println("Coins:    " + list);
		 
		System.out.print("Position: ");
		for (int i = 0; i < list.size(); i++)
		    System.out.print(" " + i + " ");
		
		System.out.println();
		System.out.println();
		
	}
	
	//allows the human to take a turn
	private void playerChoice(int c) {
		humanScore += list.get(c);
		list.remove(c);
		previousHumanChoice = c;
		System.out.println("Nice move!");
	}
	
	//tells the computer what coin to take next
	private void computerChoice() {
		if (previousHumanChoice == 0) {
			computerScore += list.get(0);
			list.remove(0);
			System.out.println("I choose position " + 0);
			System.out.println("+++++++++++");
		}
		else {
			computerScore += list.get(list.size() - 1);
			int size = list.size() -1;
			System.out.println("I choose position " + size);
			System.out.println("+++++++++++");
			list.remove(list.size() - 1);
			
		}
			
	}

	//prompts the user and plays the game
	public void playGame() {
		System.out.println("Let's play the coin game!");
		printCoins();

		// get the keyboard for the silly human
		Scanner keyboard = new Scanner(System.in);
		
		int evenTotal = 0;
		int oddTotal = 0;
		//calculates whether the odd indices or even indices are greater
		for (int i = 0; i < list.size(); i+=2) 
			evenTotal += list.get(i);
		for (int i = 1; i < list.size(); i +=2)
			oddTotal += list.get(i);
	
		
		//initial move. Computer goes first
		if (evenTotal > oddTotal) {
			computerScore += list.get(0);
		    list.remove(0);
		    System.out.println("I choose position " + 0);
		}
		if (evenTotal < oddTotal) {
			computerScore += list.get(list.size() - 1);
			int size = list.size() -1;
			System.out.println("I choose position " + size);
			list.remove(list.size() - 1);
			
		}
		//where the game is actually being played
		while (list.size() > 0) {
			System.out.println("Human: " + humanScore + " Computer: " + computerScore);
			
			printCoins();
			System.out.println("Indicate the position of the coin you choose: ");
			int humanChoice = keyboard.nextInt();
			
			playerChoice(humanChoice);
			printCoins();
			if (list.size() > 0)
				computerChoice();
		}
		
		System.out.println("+++++++++++");
		System.out.println("Game over! Final score You: " + humanScore + " Me: " +
							computerScore);
		
		

		keyboard.close();

	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Pass a file on the command line");
			System.exit(0);
		}

		GreedyCoinGame game = new GreedyCoinGame(args[0]);

		game.playGame();
	}

}
