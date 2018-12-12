const output = document.querySelector("#output");
const outputg = document.querySelector("#outputg");

// const attendanceTotal = document.querySelector("#attendanceTotal");
// const testTotal = document.querySelector("#testTotal");
// const homeworkTotal = document.querySelector("#homeworkTotal");
// const projectTotal = document.querySelector("#projectTotal");
const t = parseFloat(testTotal.value);
const a = parseFloat(attendanceTotal.value);
const p = parseFloat(projectTotal.value);
const h = parseFloat(homeworkTotal.value);
let total = t + a + h + p;
// if (a >= 10) total += 10;
if (total > 35 && p > 12.5) {
	output.style.color = "green";
	output.innerHTML = `ima uslov za ispit (${total})`;
	outputg.innerHTML = 10 - parseInt((100 - total) / 10);
} else {
	output.style.color = "red";
	output.innerHTML = `nema uslov za ispit (${total})`;
	outputg.innerHTML = `5`;
}
