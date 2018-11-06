package example01;

public class CPU {
	private String name;
	private Manufacturer manufacturer;
	private int cores;
	private int threads;
	private double clock;

	public CPU(String name, Manufacturer manufacturer, int cores, int threads, double clock) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.cores = cores;
		this.threads = threads;
		this.clock = clock;
	}
	public CPU(String name, Manufacturer manufacturer, int cores, double clock) {
		this(name, manufacturer, cores, cores, clock);
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCores() {
		return cores;
	}

	public void setCores(int cores) {
		this.cores = cores;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public double getClock() {
		return clock;
	}

	public void setClock(double clock) {
		this.clock = clock;
	}

	@Override
	public String toString() {
		return "CPU{" +
				"name='" + name + '\'' +
				", manufacturer=" + manufacturer +
				", cores=" + cores +
				", threads=" + threads +
				", clock=" + clock +
				'}';
	}
}
