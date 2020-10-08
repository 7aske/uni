file=it370-pz01-nikola_tasic_3698

default_recipe: compile

compile:
	Rscript -e "rmarkdown::render('$(file).rmd', quiet=TRUE)"
	wkhtmltopdf $(file).html $(file).pdf
	rm $(file).html