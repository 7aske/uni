package proteinTracker;

import lombok.Data;

@Data
public class HistoryItem {
	private final int id;
	private final int amount;
	private final Operation operation;
	private final int total;

	public enum Operation {
		ADD, SUB
	}
}
