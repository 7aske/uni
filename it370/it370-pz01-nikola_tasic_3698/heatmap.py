import pygame
import pandas as pd
import seaborn as sns
from os.path import basename, splitext, join
from sys import argv


# Background class used for drawing pygame background image
class Background(pygame.sprite.Sprite):
	def __init__(self, image_file, location):
		pygame.sprite.Sprite.__init__(self)
		self.image = pygame.image.load(image_file)
		self.rect = self.image.get_rect()
		self.rect.left, self.rect.top = location


# parse the image name and extension from the first cli argument
# eg. python heatmap.py Settings.png
image_path = argv[1]
plot_base = "" if argv[2] is None else argv[2]
name, ext = splitext(basename(image_path))
plot_path = join(plot_base, name + "_plot" + ext)
scr_path = join(plot_base, name + "_scr" + ext)

# load background image and get its size
bg = Background(image_path, [0, 0])
size = bg.rect.size

screen = pygame.display.set_mode(size)
clock = pygame.time.Clock()

WHITE = (255, 255, 255)
RED = (255, 0, 0)

dataset = []

# main program loop
running = True
while running:
	for event in pygame.event.get():
		if event.type == pygame.QUIT:
			running = False
		elif event.type == pygame.KEYDOWN:
			# 'q' quits the program
			if event.key == pygame.K_q:
				running = False
		elif event.type == pygame.MOUSEBUTTONDOWN:
			# if the image is clicked get the position and save it to the dataset
			pos = pygame.mouse.get_pos()
			print(pos)
			dataset.append(pos)

	screen.fill(WHITE)

	screen.blit(bg.image, bg.rect)
	# draw current dataset points to the screen
	for data in dataset:
		pygame.draw.circle(screen, RED, data, 20)
	pygame.display.flip()

	clock.tick(60)

# save current screen as well
pygame.image.save(screen, scr_path)

# invert Y values so that generated heatmap is correctly rendered
dataset = map(lambda tup: (tup[0], size[1] - tup[1]), dataset)

# convert mouse click tuple array to pd.Dataset
df = pd.DataFrame(dataset, columns=["x", "y"])

# set the size of the generated plot
sns.set(rc={'figure.figsize': size})

# generate the plot and then set up its size
# kind = "kde", "reg", "scatter" ...
# size has to be set otherwise the plot is square shaped and doesnt match the original image
plot = sns.jointplot(x="x", y="y", data=df, kind="kde", space=0, color="r")
plot.ax_marg_x.set_xlim(0, size[0])
plot.ax_marg_y.set_ylim(0, size[1])
plot.fig.set_figwidth(int(size[0] / 100))
plot.fig.set_figheight(int(size[1] / 100))

# save the plot to a separate image file
plot.savefig(plot_path)

pygame.quit()

