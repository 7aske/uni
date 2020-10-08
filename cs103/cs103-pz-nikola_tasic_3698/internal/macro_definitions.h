//
// Created by nik on 11/23/19.
//

#ifndef AGAME_MACRO_DEFINITIONS_H
#define AGAME_MACRO_DEFINITIONS_H

#define TARGET_FPS 60
#define WINDOW_TITLE "A Game"
// screen size
#define WIDTH (1024)
#define HEIGHT (768)

// apparent block size on the screen
#define BSIZE (32)

// blocks count in the viewport
#define SCR_W (WIDTH / BSIZE)
#define SCR_H (HEIGHT / BSIZE)
#define WRLD_W (3)
#define WRLD_H (2)

#define LVL_W (WRLD_W * SCR_W)
#define LVL_H (WRLD_H * SCR_H)

#define FONT_RES "res/UbuntuMono-R.ttf"
#define SPRITES_RES "res/sprites.png"


// utils print functions
#define printd(x) printf("%d\n")
#define printt(x, y) printf("(%d, %d)\n", x, y)


#endif //AGAME_MACRO_DEFINITIONS_H
