package proteinTracker;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TrackingService {
	private int total;
	private int goal;
	private List<HistoryItem> history = new ArrayList<>();
	private int historyId = 0;

	public boolean isGoalMet() {
		return total >= goal;
	}

	public void addProtein(int amount) {
		total += amount;
		historyId++;
		history.add(new HistoryItem(historyId, amount, HistoryItem.Operation.ADD, total));
	}

	public void removeProtein(int amount) {
		total -= amount;

		if (total < 0) total = 0;
		history.add(new HistoryItem(historyId, amount, HistoryItem.Operation.SUB, total));
	}

	public void setGoal(int goal) {
		if (goal < 0) throw new IllegalArgumentException();
		this.goal = goal;
	}
}
