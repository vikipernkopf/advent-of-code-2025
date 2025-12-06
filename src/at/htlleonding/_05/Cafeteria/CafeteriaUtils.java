package at.htlleonding._05.Cafeteria;

import at.htlleonding.IUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CafeteriaUtils implements IUtils<ArrayPair> {
	@Override
	public ArrayPair parseInput(Path path) throws IOException {
		List<String> lines = Files.readAllLines(path);
		
		List<long[]> ranges = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		List<IndexCouple> indexCouples = new ArrayList<>();
		
		for (String line : lines) {
			if (line.indexOf('-') >= 0) {
				String[] strings = line.split("-");
				
				long[] values = {
					Long.parseLong(strings[0]),
					Long.parseLong(strings[1])
				};
				
				indexCouples.add(new IndexCouple(values[0], true));
				indexCouples.add(new IndexCouple(values[1], false));
				
				ranges.add(values);
			} else if (!line.isBlank()) {
				ids.add(Long.parseLong(line));
			}
		}
		
		indexCouples.sort((a, b) -> {
			int cmp = Long.compare(a.index(), b.index());
			
			if (cmp != 0) {
				return cmp;
			}
			
			return Boolean.compare(b.isStart(), a.isStart());
		});
		
		return new ArrayPair(ranges, ids, indexCouples);
	}

	@Override
	public long calculatePartOne(ArrayPair values) {
		int count = 0;
		
		for (long id : values.ids()) {
			for (long[] range : values.ranges()) {
				if (id >= range[0] && id <= range[1]) {
					count++;
					
					break;
				}
			}
		}
		
		return count;
	}

	@Override
	public long calculatePartTwo(ArrayPair values) {
		int indexCount = 0;
		List<long[]> ranges = new ArrayList<>();
		//List<IndexCouple> couples = removeDuplicates(values.indexCouples());
		
		for (IndexCouple couple : values.indexCouples()) {
			if (indexCount == 0 && couple.isStart()) {
				ranges.add(new long[] { couple.index(), 0 });
			}
			
			if (couple.isStart()) {
				indexCount++;
			} else {
				indexCount--;
			}
			
			if (indexCount == 0 && !couple.isStart()) {
				ranges.getLast()[1] = couple.index();
			}
		}
		
		long count = 0;
		
		for (long[] range : ranges) {
			count += (range[1] - range[0]) + 1;
		}
		
		return count;
	}
	
	/*private List<IndexCouple> removeDuplicates(List<IndexCouple> indexCouples) {
		List<IndexCouple> result = new ArrayList<>();
		
		for (IndexCouple couple : indexCouples) {
			if (!result.isEmpty()
				&& result.getLast().index() == couple.index()
				&& result.getLast().isStart() != couple.isStart()) {
				//cancels out
				result.removeLast();
			} else {
				result.add(couple);
			}
		}
		
		return result;
	}*/
}
