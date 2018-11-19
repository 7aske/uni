const form = document.querySelector('form');
const output = document.querySelector('#errors');
const possibleErrors = {
	firstName: 'Invalid First Name.',
	lastName: 'Invalid Last Name.',
	phone: 'Invalid Phone number.',
	message: 'Please enter a message.'
};
let errors = [];
const errorTemplate = text => {
	return `<div class="alert alert-danger">${text}</div>`;
};
function appendErrors() {
	output.innerHTML = '';
	errors.forEach(e => {
		output.innerHTML += errorTemplate(e);
	});
	errors = [];
}
form.addEventListener('submit', e => {
	e.preventDefault();
	if (!/^[a-zA-Z]+$/.test(e.target.firstName.value)) errors.push(possibleErrors.firstName);
	if (!/^[a-zA-Z]+$/.test(e.target.lastName.value)) errors.push(possibleErrors.lastName);
	if (!/^\d+$/.test(e.target.phone.value)) errors.push(possibleErrors.phone);
	if (e.target.message.value == 0) errors.push(possibleErrors.message);
	appendErrors();
});
