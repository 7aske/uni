// 1. Zadatak
function largerThan10(array) {
	array.forEach(e => {
		if (e > 10) console.log(e, 'is larger than 10');
	});
}
largerThan10([10, 10, 11, 12, 99, 9, 3, 5, 1, 61, 89, 76, 6]);

// 2. Zadatak
function div2notDiv10(end) {
	for (let i = 0; i < end; i++) {
		if (i % 2 == 0 && i % 10 != 0) console.log(i, 'is divisible by 2 but not by 10');
	}
}
div2notDiv10(500);

// 3. Zadatak
function isPrime(num) {
	for (let i = 2; i <= Math.sqrt(num); i++) {
		if (num % i == 0) return false;
	}
	return true;
}
function checkPrimes(num) {
	for (let i = 2; i < num; i++) {
		if (isPrime(i)) console.log(i, 'is prime');
	}
}
checkPrimes(450);

// 4. Zadatak
let fibLimit = 8;
let fibNumbers = [0, 1];
function fib(array, end) {
	if (end != 0) {
		array.push(array[array.length - 2] + array[array.length - 1]);
		end--;
		fib(array, end);
	} else {
		console.log(array);
	}
}
fib(fibNumbers, fibLimit);
