package at.htlleonding._06.TrashCompactor;

import at.htlleonding.IUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class TrashCompactorUtils implements IUtils<CalculationPair[]> {
	@Override
	public CalculationPair[] parseInput(Path path) throws IOException {
		List<String> lines = Files.readAllLines(path);
		boolean[] isAdditionArray = new boolean[0];
		int[][] numbers = new int[lines.size() - 1][];
		
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			
			String[] split = line.trim().split("\\s+");
			
			if (i < lines.size() - 1) {
				numbers[i] = new int[split.length];
			}
			
			if (i == lines.size() - 1) {
				isAdditionArray = new boolean[split.length];
			}
			
			for (int j = 0; j < split.length; j++) {
				if (split[j].equals("+")) {
					isAdditionArray[j] = true;
				} else if (split[j].equals("*")) {
					isAdditionArray[j] = false;
				} else {
					numbers[i][j] = Integer.parseInt(split[j]);
				}
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
		
		CalculationPair[] calculationPairs = new CalculationPair[numbersOrganized.length];
		
		for (int i = 0; i < calculationPairs.length; i++) {
			calculationPairs[i] = new CalculationPair(numbersOrganized[i], isAdditionArray[i]);
		}
		
		return calculationPairs;
	}
	
	@Override
	public long calculatePartOne(CalculationPair[] values) {
		long sum = 0;
		
		for (CalculationPair value : values) {
			if (value.isAddition()) {
				for (int number : value.numbers()) {
					sum += number;
				}
			} else {
				long product = 1;
				
				for (int number : value.numbers()) {
					product *= number;
				}
				
				sum += product;
			}
		}
		
		return sum;
	}
	
	@Override
	public long calculatePartTwo(CalculationPair[] values) {
		return 0;
	}
}
