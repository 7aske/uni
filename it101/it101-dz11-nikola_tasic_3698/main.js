const addFieldsButton = document.querySelector("#addFields");
addFieldsButton.addEventListener("click", () => {
    addFields();
})
const executeButton = document.querySelector("#execute");
executeButton.addEventListener("click", () => {
    execute();
})
const operation = document.querySelector("#operation")
const noOfFields = document.querySelector("#noOfFields");
const fieldsContainer = document.querySelector("#fieldsContainer");
const output = document.querySelector("#output");
let fields;

function addFields() {
    const value = parseInt(noOfFields.value);
    fieldsContainer.innerHTML = "";
    for (let i = 0; i < value; i++) {
        fieldsContainer.innerHTML += `<input type="number" name="field">`;
    }
    fields = new Array(...document.querySelectorAll("input[name='field']"));
}


function execute() {
    let numbers = [...fields.map(f => parseFloat(f.value))]
    let result;
    switch (operation.value) {
        case "add":
            result = numbers.reduce((a, c) => a + c);
            break;
        case "subtract":
            result = numbers.reduce((a, c) => a - c);
            break;
        case "divide":
            result = numbers.reduce((a, c) => a / c);
            break;
        case "multiply":
            result = numbers.reduce((a, c) => a * c);
            break;
    }
    output.innerHTML = result;

}