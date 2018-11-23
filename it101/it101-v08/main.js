// let i = 0;

// while (i < 10) {
// 	console.log(i);
// 	i++;
// }

// const array = [10, 11, 12, 13, 15];
// let max = array[0];
// for (let j = 0; j < array.length; j++) {
// 	if (array[j] > max) max = array[j];
// }
// console.log(max);

// for (let i = 1; i <= 100; i++) {
// 	if (i % 7 == 0) console.log(i);
// }

// let fact = 1;
// for (let i of Array(10).keys()) if (i != 0) fact *= i;
// console.log(fact);

for (let i = 0; i < 7; i++) {
	console.log(
		Array(i)
			.fill('k')
			.join('')
	);
}
