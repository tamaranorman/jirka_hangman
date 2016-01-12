package Hangman;

public class Game {
	static GameEngine currGame;

	public static void main(String[] args) {
		Communicator talk = new Communicator();
		char playAgain='Y';
		System.out.println("Hi! Welcome to the game!");
		while (playAgain == 'Y'){
			System.out.println("Choose your difficulty option from 1 to 4.");	
			currGame = new GameEngine (talk.getNumber(1, 4));
			boolean gameWon = currGame.round();
			if (gameWon == true)	{
				System.out.println("Well done, you've guessed my word! " +
			"It was the word " + currGame.getWord());
			}
			else {
				System.out.println ("What a shame, you've lost this time.");
				System.out.println("My word was: " + currGame.getWord());
			}
			playAgain=talk.playAgain();
		}
		System.out.println("Hope you've had fun, see you next time!");
	}

}
