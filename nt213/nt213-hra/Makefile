out=nt213-hra.pdf
in=nt213-hra.md
default_recipe: pdf

pdf: $(int)
	pandoc $(in) -t beamer -w pdf -o $(out)

open: $(out)
	zathura $(out)
