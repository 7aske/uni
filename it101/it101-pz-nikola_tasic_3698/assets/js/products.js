const buttons = document.querySelectorAll('section .learnMore');
buttons.forEach(b => {
	b.addEventListener('click', e => {
		toggleLearnMore(e);
	});
});
function toggleLearnMore(e) {
	const content = e.target.parentElement.parentElement.children[1];
	content.classList.toggle('fade');
}

class ScrollHandler {
	constructor() {
		this.sections = document.querySelectorAll('main section');
		this.viewportHeight = window.innerHeight;
		this.scrollValue = 0;
		this.currentIndex = 0;
		this.canMove = false;
		document.addEventListener('keydown', e => {
			e.preventDefault();
			this.keyDown(e.keyCode);
		});
		document.querySelector('main').addEventListener('mousewheel', e => {
			e.preventDefault();
			this.scroll(e.deltaY);
		});
		setInterval(() => {
			this.canMove = true;
		}, 500);
	}
	keyDown(code) {
		if (code == 38) {
			if (this.currentIndex > 0) this.currentIndex--;
			this.move();
		} else if (code == 40) {
			if (this.currentIndex < this.sections.length - 1) this.currentIndex++;
			this.move();
		}
	}
	scroll(y) {
		if (this.canMove) {
			if (y < 0) {
				if (this.currentIndex > 0) this.currentIndex--;
				this.move();
			} else {
				if (this.currentIndex < this.sections.length - 1) this.currentIndex++;
				this.move();
			}
		}
	}
	move() {
		if (this.canMove) {
			this.sections[this.currentIndex].scrollIntoView({ behavior: 'smooth', block: 'end' });
			this.canMove = false;
		}
	}
}

scrollHandler = new ScrollHandler();
