package Hangman;

import java.util.Scanner;

public class Communicator {

	private Scanner scan = new Scanner (System.in);

	public char makeAGuess (char [] usedLetters)	{
		System.out.println("Guess a letter!");
		String inp = scan.nextLine();
		if (!(inp.length()==1))	{
			System.out.println("Your input is invalid, try again");
			return makeAGuess(usedLetters);
		}
		char oP = inp.toUpperCase().charAt(0);
		if (!(new String (usedLetters).indexOf(oP)==-1))	{
			System.out.println("You've used this letter before!");
			return makeAGuess (usedLetters);
		}
		return oP;

	}

	public int getNumber (int minN, int maxN)	{
		String inp = scan.nextLine();
		int oP = -1;
		try	{
			if (inp.length()>Integer.toString(maxN).length() ||inp.length()==0)	{
				System.out.println("Your input is invalid, try again");
				return getNumber(minN, maxN);
			}

			else oP = Integer.parseInt(inp);
			if (minN>oP || maxN<oP)	{
				System.out.println("Your input is invalid, try again");
				return getNumber(minN, maxN);
			}
		}		catch (NumberFormatException e)	{
			System.out.println("Your input is invalid, try again");
		}
		return oP;

	}	
	public char playAgain	()	{
		System.out.println ("Wanna play again? Press Y to continue, N to stop.");
		String inp = scan.nextLine();
		if (!(inp.length()==1))	{
			System.out.println("Your input is invalid, try again");
			return playAgain();
		}
		char oP = inp.toUpperCase().charAt(0);
		if (!(oP=='Y' || oP=='N'))	{
			System.out.println("Your input is invalid, try again");
			return playAgain();
		}
		return oP;
	}

}