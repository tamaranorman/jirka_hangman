package Hangman;

import java.io.File;
import java.util.ArrayList;

public class GameEngine {
	/*Responsible for the actual running of the game.*/
	private WordDict dictionary = new WordDict (new File("words.txt"));
	private String guessWord;
	private String guessedSoFar;
	private int life;
	private char [] usedLetters = {' '};
	private Communicator talk = new Communicator();
	char currentGuess;

	public GameEngine (int difficulty)	{
		//Initialises the game according to the chosen difficulty
		switch (difficulty)	{

		case 1: life = 10;
		guessWord = dictionary.chooseWord(0, 5).toUpperCase();
		break;

		case 2: life = 7;
		guessWord = dictionary.chooseWord(3, 8).toUpperCase();
		break;

		case 3: life = 7;
		guessWord = dictionary.chooseWord(6, 1000).toUpperCase();
		break;

		case 4: life = 4;
		guessWord = dictionary.chooseWord(6, 1000).toUpperCase();
		break;
		
		}
		guessedSoFar = makeGuessWord (guessWord);
	}

	public String makeGuessWord (String guessWord){
		/*Converts the word the user is supposed to guess to _'s*/
		String out = "";
		for (int i=0;i<guessWord.length();i++)	{
			out+="_";
		}
		return out;
	}

	public boolean round ()	{
		/*Manages one round of the game, returns true if game is won,
		 * false if it's lost and calls itself again if neither.
		*/
		printGameState();
		currentGuess = talk.makeAGuess(usedLetters);
		usedLetters = appendChar (usedLetters, currentGuess);
		if (compareGuess(currentGuess))	{
			System.out.println("Well done, this letter is in my word!");
		}
		else {
			System.out.println("Ouch, sorry, this letter is not in my word, you lose a life!");
			life--;
		}
		if (guessedSoFar.indexOf('_')==-1){
			//If there are no more _'s in the word the user is guessing, then they've got the word.
			return true;
		}
		else if (life<1){return false;}
		return round();
	}

	public boolean compareGuess (char guess)	{
		//Compares player's guess with the actual word and returns true if guess is correct
		if (guessWord.indexOf(guess)<0)	{
			return false;
		}
		else	{
			for (int i = 0; i<guessWord.length(); i++)	{
				if (guessWord.charAt(i)==guess)	{
					guessedSoFar = changeCharInPosition (i, guess, guessedSoFar);
				}
			}
			return true;
		}
	}


	public void printGameState()	{
		/*Prints the current game state, i.e. lives, used letters, etc.*/
		//Cheating line for testing purposes System.out.println("My word is: " + this.getWord());
		System.out.println("You currently have " + life + " lives left.");
		System.out.print("You have already tried the following letters: ");
		for (int i=0; i<usedLetters.length;i++)	{
			System.out.print(usedLetters[i] + " ");	
		}
		System.out.println(".");
		System.out.println("The word you're trying to guess currently looks"
				+ " like this: " + guessedSoFar + "(" + guessedSoFar.length() +
				" characters long)");

	}
	public char [] appendChar (char [] arr, char c)	{
		//appends char to a char array
		return (new String(arr)+c).toCharArray();
	}
	public String changeCharInPosition(int position, char ch, String str){
		//changes char to 'ch' at position 'position' in String str.
		char[] charArray = str.toCharArray();
		charArray[position] = ch;
		return new String(charArray);
	}
	public String getWord(){
		return guessWord;
	}
}

