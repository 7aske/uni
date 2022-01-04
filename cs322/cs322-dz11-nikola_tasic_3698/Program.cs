using cs322_dz11_nikola_tasic_3698;
using cs322_dz11_nikola_tasic_3698.Logger;

void main() {
	Kurs kurs1 = new("cs322", 10, 10);
	Kurs kurs2 = new("cs101", 10, 32);
	Kurs kurs3 = new("cs323", 10, 6);
	List<Kurs> arr = new() {kurs1, kurs2, kurs3};
	arr.Sort();
	// ujedno i primer zadatka 2
	LoggerExample.Logger logger = LoggerExample.LoggerFactory.GetConsoleLogger(typeof(Program));
	logger.Log("{0}", string.Join(", ", arr));
}

main();