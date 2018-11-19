const cards = document.querySelectorAll('.card');
cards.forEach(c => {
	c.addEventListener('mouseenter', e => {
		c.style.color = 'black';
		c.style.backgroundColor = 'white';
	});
	c.addEventListener('mouseleave', e => {
		c.style.color = 'white';
		c.style.backgroundColor = '#343434';
	});
});
