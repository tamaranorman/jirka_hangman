package Hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class WordDict {
	/*Loads a dictionary and chooses a word from it of desired length*/
	String[] dict;
	public WordDict(File f) {
		/*Loads a dictionary*/
	
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f))) ){
			String line = reader.readLine();
			while (line != null) {
				list.add(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		dict = list.toArray(new String[list.size()]);
	}
	
	public String chooseWord (int minL, int maxL)	{
		while (true) {
			int rand = (int) (Math.random() * dict.length);
			if ((dict[rand].length() <= maxL) && (dict[rand].length()>=minL)) {
				return dict[rand];
			}
			
		}
	}
}

