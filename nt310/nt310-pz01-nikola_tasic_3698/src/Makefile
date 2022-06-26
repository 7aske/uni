# arch packages needed to compile:
#   pandoc
#   texlive-most with texlive-core at least
images=img/linus.jpeg img/kenanddennis.jpg img/man.png img/stallman.jpg img/gnu.png 
default_recipe: pdf

pdf: pres.md $(images)
	pandoc -t beamer pres.md -o pres.pdf

pptx: pres.md $(images)
	pandoc -t beamer pres.md -w pptx -o pres.pptx

html: pres.md $(images)
	pandoc -t beamer pres.md -w html -o pres.html

all: pptx html pdf

open: pres.pdf
	zathura pres.pdf || xdg-open pres.pdf
