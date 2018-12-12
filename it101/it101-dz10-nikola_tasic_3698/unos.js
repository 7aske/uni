const attendanceTotal = document.querySelector("#attendanceTotal");
const testTotal = document.querySelector("#testTotal");
const homeworkTotal = document.querySelector("#homeworkTotal");
const projectTotal = document.querySelector("#projectTotal");

const attendance = document.querySelectorAll("input[name='attendance']");
const test = document.querySelectorAll("input[name='test']");
const homework = document.querySelectorAll("input[name='homework']");

const storage = {
	attendances: [],
	tests: [],
	homeworks: [],
	project: 0
};

attendance.forEach(a =>
	a.addEventListener("change", () => {
		attendanceTotal.value = updateAttendance();
	})
);
test.forEach(t =>
	t.addEventListener("change", () => {
		testTotal.value = updateTest();
	})
);
test.forEach(t => {
	t.addEventListener("change", e => {
		if (2.5 < parseFloat(t.value)) t.value = 2.5;
		else if (0 > parseFloat(t.value)) t.value = 0;
		else if (isNaN(t.value)) t.value = 0;
	});
});
homework.forEach(h =>
	h.addEventListener("change", () => {
		homeworkTotal.value = updateHomework();
	})
);
homework.forEach(h => {
	h.addEventListener("change", e => {
		if (1.5 < parseFloat(h.value)) h.value = 1.5;
		else if (0 > parseFloat(h.value)) h.value = 0;
		else if (isNaN(h.value)) h.value = 0;
	});
});

projectTotal.addEventListener("change", e => {
	if (25 < parseFloat(projectTotal.value)) projectTotal.value = 25;
	else if (0 > parseFloat(projectTotal.value)) projectTotal.value = 0;
	else if (isNaN(projectTotal.value)) projectTotal.value = 0;
});
projectTotal.addEventListener("change", e => {
	updateProject();
});
function updateAttendance() {
	let total = 0;
	let result = [];
	attendance.forEach(a => {
		result.push(a.checked);
		if (a.checked) total++;
	});
	storage.attendances = result;
	localStorage.setItem("storage", JSON.stringify(storage));
	return total;
}

function updateTest() {
	let total = 0;
	let result = [];
	test.forEach(t => {
		if (!isNaN(t.value)) {
			result.push(t.value);
			total += parseFloat(t.value);
		}
	});
	storage.tests = result;
	localStorage.setItem("storage", JSON.stringify(storage));
	return total;
}

function updateHomework() {
	let total = 0;
	let result = [];
	homework.forEach(h => {
		if (!isNaN(h.value)) {
			result.push(h.value);
			total += parseFloat(h.value);
		}
	});
	storage.homeworks = result;
	localStorage.setItem("storage", JSON.stringify(storage));
	return total;
}

function updateProject() {
	storage.project = parseFloat(projectTotal.value);
	localStorage.setItem("storage", JSON.stringify(storage));
}
function init() {
	let s = JSON.parse(localStorage.getItem("storage"));
	if (s == null) {
		s = storage;
	}
	s.attendances.forEach((v, i) => {
		attendance[i].checked = v;
	});
	s.tests.forEach((v, i) => {
		test[i].value = v;
	});
	s.homeworks.forEach((v, i) => {
		homework[i].value = v;
	});
	projectTotal.value = s.project;
	homeworkTotal.value = updateHomework();
	attendanceTotal.value = updateAttendance();
	testTotal.value = updateTest();
}
init();
