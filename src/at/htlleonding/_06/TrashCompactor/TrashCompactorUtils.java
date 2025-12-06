package at.htlleonding._06.TrashCompactor;

import at.htlleonding.IUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TrashCompactorUtils implements IUtils<InputPair> {
	@Override
	public InputPair parseInput(Path path) throws IOException {
		List<String> lines = Files.readAllLines(path);
		char[][] chars = new char[lines.size() - 1][];
		
		for (int i = 0; i < lines.size() - 1; i++) {
			chars[i] = lines.get(i).toCharArray();
		}
		
		String[] operations = lines.getLast().trim().split("\\s+");
		boolean[] isAdditionArray = new boolean[operations.length];
		
		for (int i = 0; i < operations.length; i++) {
			isAdditionArray[i] = operations[i].equals("+");
		}
		
		return new InputPair(chars, isAdditionArray);
	}
	
	@Override
	public long calculatePartOne(InputPair values) {
		char[][] chars = values.chars();
		int[][] numbers = new int[chars.length][];
		
		for (int i = 0; i < chars.length; i++) {
			String line = String.valueOf(chars[i]);
			
			String[] split = line.trim().split("\\s+");
			
			numbers[i] = new int[split.length];
			
			for (int j = 0; j < split.length; j++) {
				numbers[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		int[][] numbersOrganized = new int[numbers[0].length][];
		
		for (int i = 0; i < numbersOrganized.length; i++) {
			numbersOrganized[i] = new int[numbers.length];
		}
		
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers[i].length; j++) {
				numbersOrganized[j][i] = numbers[i][j]; //switch from array per line to array per calculation
			}
		}
		
		List<Calculation> calculations = new ArrayList<>();
		
		for (int i = 0; i < numbersOrganized.length; i++) {
			calculations.add(new Calculation(numbersOrganized[i], values.isAdditionArray()[i]));
		}
		
		return doCalculations(calculations);
	}
	
	@Override
	public long calculatePartTwo(InputPair values) {
		char[][] chars = values.chars();
		int maxLength = maxStringLength(chars);
		
		String[][] strings = new String[maxLength][];
		strings[0] = new String[maxLength];
		
		int index = 0;
		int currentIndex = 0;
		
		for (int i = 0; i < maxLength; i++) { //i for columns, should stay in same column and then go vertically
			String current = "";
			
			for (char[] charArray : chars) { //j iterates through the rows
				if (i < charArray.length) {
					//noinspection StringConcatenationInLoop
					current += charArray[i];
				}
			}
			
			current = current.trim();
			
			if (current.isEmpty()) {
				index++;
				strings[index] = new String[maxLength];
			} else {
				strings[index][currentIndex] = current;
				currentIndex++;
			}
		}
		
		List<Calculation> calculations = new ArrayList<>();
		strings = removeNullArrays(strings);
		
		for (int i = 0; i < strings.length; i++) {
			String[] stringNumbers = strings[i];
			
			int notNullCount = 0;
			
			for (String string : stringNumbers) {
				if (string != null) {
					notNullCount++;
				}
			}
			
			int[] numbers = new int[notNullCount];
			int numbersIndex = 0;
			
			for (String stringNumber : stringNumbers) {
				if (stringNumber != null) {
					numbers[numbersIndex] = Integer.parseInt(stringNumber);
					numbersIndex++;
				}
			}
			
			calculations.add(new Calculation(numbers, values.isAdditionArray()[i]));
		}
		
		return doCalculations(calculations);
	}
	
	private int maxStringLength(char[][] chars) {
		int max = 0;
		
		for (char[] charArray : chars) {
			max = Math.max(max, charArray.length);
		}
		
		return max;
	}
	
	private String[][] removeNullArrays(String[][] strings) {
		int notNullCount = 0;
		
		for (String[] string : strings) {
			if (string != null) {
				notNullCount++;
			}
		}
		
		String[][] stringsWithoutNulls = new String[notNullCount][];
		int index = 0;
		
		for (String[] string : strings) {
			if (string != null) {
				stringsWithoutNulls[index] = string;
				
				index++;
			}
		}
		
		return stringsWithoutNulls;
	}
	
	private long doCalculations(List<Calculation> calculations) {
		long sum = 0;
		
		for (Calculation calculation : calculations) {
			if (calculation.isAddition()) {
				for (int number : calculation.numbers()) {
					sum += number;
				}
			} else {
				long product = 1;
				
				for (int number : calculation.numbers()) {
					product *= number;
				}
				
				sum += product;
			}
		}
		
		return sum;
	}
}
