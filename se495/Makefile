out=se495-zavrsni_rad-nikola_tasic_3698.pdf

default_recipe: doc

doc: body.md
	pandoc \
		--from=markdown+smart+yaml_metadata_block+implicit_figures \
		--standalone \
		--pdf-engine=xelatex \
		--strip-comments \
		--highlight-style=tango \
		-o body.pdf \
		body.md
	pdftk first.pdf body.pdf cat output $(out)
