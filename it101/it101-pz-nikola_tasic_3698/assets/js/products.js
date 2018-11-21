const buttons = document.querySelectorAll('section .learnMore');
buttons.forEach(b => {
	b.addEventListener('click', e => {
		toggleLearnMore(e);
	});
});
function toggleLearnMore(e) {
	const content = e.target.parentElement.parentElement.children[2];
	content.classList.toggle('fade');
}

class ScrollHandler {
	constructor() {
		this.sections = document.querySelectorAll('main section');
		this.currentIndex = 0;
		this.canMove = false;
		document.addEventListener('keydown', e => {
			e.preventDefault();
			this.key(e.keyCode);
		});
		document.querySelector('main').addEventListener('mousewheel', e => {
			e.preventDefault();
			this.scroll(e.deltaY);
		});
		setInterval(() => {
			this.canMove = true;
		}, 700);
	}
	key(code) {
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
			this.canMove = false;
			this.sections[this.currentIndex].scrollIntoView({ behavior: 'smooth', block: 'end' });
			this.sections[this.currentIndex].children[0].classList.add('animated');
			this.sections[this.currentIndex].children[0].classList.add('slideInRight');
			for (let i = 0; i < this.sections.length; i++) {
				if (i != this.currentIndex) {
					this.sections[i].children[0].classList.remove('animated');
					this.sections[i].children[0].classList.remove('slideInRight');
				}
			}
		}
	}
}

scrollHandler = new ScrollHandler();
