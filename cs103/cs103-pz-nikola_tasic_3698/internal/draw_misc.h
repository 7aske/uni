//
// Created by nik on 11/23/19.
//

#ifndef SDLGAME_DRAW_MISC_H
#define SDLGAME_DRAW_MISC_H

#define LINE_H 16
#define CHAR_W 8

#pragma once

#include <SDL2/SDL.h>
#include <SDL2/SDL_ttf.h>
#include <string.h>

#include "macro_definitions.h"
#include "cleanup_functions.h"

#define COLOR_WHITE (SDL_Color){255, 255, 255, 168}

static void draw_text(SDL_Renderer* renderer, TTF_Font* font, char const* text, int x, int y, SDL_Color* c) {
	assert(renderer != (void*) 0);
	assert(font != (void*) 0);
	assert(text != (void*) 0);
	SDL_Rect msg_rect;
	SDL_Color color = COLOR_WHITE;

	if (c != NULL) {
		color = *c;
	}

	SDL_Surface* msg_surf __attribute__((__cleanup__(_sdlfs))) = TTF_RenderText_Blended(font, text, color);
	SDL_Texture* msg_tex __attribute__((__cleanup__(_sdldt))) = SDL_CreateTextureFromSurface(renderer, msg_surf);

	msg_rect.x = x;
	msg_rect.y = y;
	msg_rect.w = (int) strnlen(text, WIDTH) * CHAR_W;
	msg_rect.h = LINE_H;
	SDL_RenderCopy(renderer, msg_tex, NULL, &msg_rect);
}

static void draw_help(SDL_Renderer* renderer, TTF_Font* font) {


	static const char
			* const line1 = "O-toggle maze solve",
			* const line2 = "L-toggle light",
			* const line3 = "R-generate new maze",
			* const line4 = "Q,E-toggle realm",
			* const line5 = "1,2,3,0-change realm",
			* const line6 = "Space-shoot",
			* const line7 = "G,H-toggle graph",
			* const line8 = "N-toggle enemies";

	SDL_Color color = {255, 255, 255, 128};

	draw_text(renderer, font, line1, 5, HEIGHT - 1 * LINE_H, &color);
	draw_text(renderer, font, line2, 5, HEIGHT - 2 * LINE_H, &color);
	draw_text(renderer, font, line3, 5, HEIGHT - 3 * LINE_H, &color);
	draw_text(renderer, font, line4, 5, HEIGHT - 4 * LINE_H, &color);
	draw_text(renderer, font, line5, 5, HEIGHT - 5 * LINE_H, &color);
	draw_text(renderer, font, line6, 5, HEIGHT - 6 * LINE_H, &color);
	draw_text(renderer, font, line7, 5, HEIGHT - 7 * LINE_H, &color);
	draw_text(renderer, font, line8, 5, HEIGHT - 8 * LINE_H, &color);
}

static void draw_fps(SDL_Renderer* renderer, TTF_Font* font, int const* fps) {
	char buf[32];
	snprintf(buf, 31, "FPS %d", *fps);
	draw_text(renderer, font, buf, 5, 5, NULL);
}

#endif //SDLGAME_DRAW_MISC_H
