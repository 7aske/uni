//
// Created by nik on 11/23/19.
//

#ifndef SDLGAME_CLEANUP_FUNCTIONS_H
#define SDLGAME_CLEANUP_FUNCTIONS_H

#pragma once

#include <SDL2/SDL.h>
#include <SDL2/SDL_ttf.h>

static void __attribute__((used)) _sdldt(SDL_Texture** tex) {
	SDL_DestroyTexture(*tex);
}

static void __attribute__((used)) _sdlfs(SDL_Surface** surf) {
	SDL_FreeSurface(*surf);
}

static void __attribute__((used)) _afree(void** ptr) {
	free(*ptr);
}

static void _sdlerr(SDL_Renderer* ren, SDL_Window* win) {
	char const* err = SDL_GetError();
	if (err != NULL) {
		fprintf(stderr, "SDL_Error: %s\n", err);
	}
	if (ren != NULL) {
		SDL_DestroyRenderer(ren);
	}
	if (win != NULL) {
		SDL_DestroyWindow(win);
	}
	TTF_Quit();
	SDL_Quit();
	exit(1);
}

#endif //SDLGAME_CLEANUP_FUNCTIONS_H

