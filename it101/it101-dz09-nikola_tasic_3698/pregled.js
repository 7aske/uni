const inputs = document.querySelectorAll("input");
const attendance = document.querySelectorAll("input[name='attendance']");
const test = document.querySelectorAll("input[name='test']");
const homework = document.querySelectorAll("input[name='homework']");
const attendanceTotal = document.querySelector("#attendanceTotal");
const testTotal = document.querySelector("#testTotal");
const homeworkTotal = document.querySelector("#homeworkTotal");
inputs.forEach(i => {
	i.disabled = true;
	i.readOnly = true;
});
function init() {
	const storage = JSON.parse(localStorage.getItem("storage"));
	storage.attendances.forEach((v, i) => {
		attendance[i].checked = v;
	});
	storage.tests.forEach((v, i) => {
		test[i].value = v;
	});
	storage.homeworks.forEach((v, i) => {
		homework[i].value = v;
	});
	homeworkTotal.value = updateHomework();
	attendanceTotal.value = updateAttendance();
	testTotal.value = updateTest();
}
function updateAttendance() {
	let total = 0;
	attendance.forEach(a => {
		if (a.checked) total++;
	});
	return total;
}

function updateTest() {
	let total = 0;
	test.forEach(t => {
		if (!isNaN(t.value)) {
			total += parseFloat(t.value);
		}
	});
	return total;
}

function updateHomework() {
	let total = 0;
	homework.forEach(h => {
		if (!isNaN(h.value)) {
			total += parseFloat(h.value);
		}
	});
	return total;
}
init();
