const attendanceTotal = document.querySelector("#attendanceTotal");
const testTotal = document.querySelector("#testTotal");
const homeworkTotal = document.querySelector("#homeworkTotal");

const attendance = document.querySelectorAll("input[name='attendance']")
const test = document.querySelectorAll("input[name='test']")
const homework = document.querySelectorAll("input[name='homework']")

attendance.forEach(t => t.addEventListener("change", () => {
    attendanceTotal.value = updateAttendance();
}));
test.forEach(t => t.addEventListener("change", () => {
    testTotal.value = updateTest();
}));
homework.forEach(t => t.addEventListener("change", () => {
    homeworkTotal.value = updateHomework();
}));


function updateAttendance() {

    let total = 0;
    attendance.forEach(a => {
        if (a.checked) total++;
    })
    return total;
}

function updateTest() {
    let total = 0;
    test.forEach(t => {
        if (!isNaN(t.value)) total += parseFloat(t.value);
    })
    return total;
}

function updateHomework() {
    let total = 0;
    homework.forEach(h => {
        if (!isNaN(h.value)) total += parseFloat(h.value);
    })
    return total;
}