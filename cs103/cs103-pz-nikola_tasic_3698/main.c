#define SDL_MAIN_HANDLED 1

#include <stdio.h>
#include <SDL2/SDL_ttf.h>
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <signal.h>
#include <assert.h>

#include "macro_definitions.h"
#include "draw_misc.h"

#include "state.h"
#include "entity/player.h"
#include "entity/spawner.h"
#include "entity/light.h"
#include "entity/pew.h"
#include "entity/enemy.h"
#include "event/event.h"

#include "sprites.h"
#include "maze.h"
#include "util.h"

void sig_handler(int);

void Input(state_t* state, SDL_Event*, volatile int* running);

void Update(state_t* state, double delta_time);

void Render(state_t* state, SDL_Renderer* renderer, SDL_Texture* tex, TTF_Font* font);

void Event(state_t* state, double delta_time);

void init_game(state_t* state);


int main(int argc, char** argv) {
	volatile static int running = 1;
	static SDL_Window* window = NULL;
	static SDL_Renderer* renderer = NULL;
	static uint32_t render_flags = SDL_RENDERER_ACCELERATED;
	static TTF_Font* font;
	static SDL_Texture* texture;
	static state_t state;
	maze_test_macros();
	signal(SIGINT, sig_handler);

	//The surface contained by the window
	SDL_Surface* surface = NULL;
	//Initialize SDL
	if (SDL_Init(SDL_INIT_VIDEO) < 0)
		_sdlerr(renderer, window);

	TTF_Init();
	//Create window
	window = SDL_CreateWindow(WINDOW_TITLE,
							  SDL_WINDOWPOS_CENTERED,
							  SDL_WINDOWPOS_CENTERED,
							  WIDTH,
							  HEIGHT,
							  SDL_WINDOW_SHOWN);
	if (!window)
		_sdlerr(renderer, window);
	SDL_SetWindowTitle(window, WINDOW_TITLE);
	SDL_SetWindowResizable(window, SDL_FALSE);

	renderer = SDL_CreateRenderer(window, -1, render_flags);
	if (!renderer)
		_sdlerr(renderer, window);

	surface = IMG_Load(SPRITES_RES);
	texture = SDL_CreateTextureFromSurface(renderer, surface);
	SDL_FreeSurface(surface);
	if (!texture)
		_sdlerr(renderer, window);

	font = TTF_OpenFont(FONT_RES, 32);
	if (!font)
		fprintf(stderr, "TTF_OpenFont: %s\n", TTF_GetError());

	init_game(&state);

	// setup code
	Uint64 prev_time = SDL_GetTicks();
	Uint64 now_time = 0;
	Uint64 elapsed_time = 0;
	SDL_Event event;

	while (running) {
		SDL_RenderClear(renderer);

		now_time = SDL_GetTicks();
		elapsed_time = now_time - prev_time;
		prev_time = now_time;
		// printf("%f %lu %lu\n", 1000.0 / elapsed_time, elapsed_time, now_time);

		while (SDL_PollEvent(&event)) {
			Input(&state, &event, &running);
		}

		Event(&state, elapsed_time);
		Update(&state, elapsed_time);
		Render(&state, renderer, texture, font);

		SDL_RenderPresent(renderer);
		SDL_UpdateWindowSurface(window);

		SDL_Delay(1000 / TARGET_FPS);
	}

	SDL_DestroyRenderer(renderer);
	SDL_DestroyWindow(window);
	TTF_Quit();
	SDL_Quit();
	return 0;
}

void sig_handler(int sig) {
	if (sig == SIGINT) {
		exit(0);
	}
}

void Input(state_t* state, SDL_Event* ev, volatile int* running) {
	switch (ev->type) {
		case SDL_QUIT:
			*running = 0;
			break;
		case SDL_KEYDOWN:
			switch (ev->key.keysym.scancode) {
				case SDL_SCANCODE_W:
				case SDL_SCANCODE_UP:
				case SDL_SCANCODE_A:
				case SDL_SCANCODE_LEFT:
				case SDL_SCANCODE_S:
				case SDL_SCANCODE_DOWN:
				case SDL_SCANCODE_D:
				case SDL_SCANCODE_RIGHT:
					player_move(&state->player, ev->key.keysym.scancode, &state->level);
					if (state->player.x != state->level.mgraph->start->x &&
						state->player.y != state->level.mgraph->start->y) {
						event_dispatch(state, EV_RECALC_GRPH, ev_recalc_grph);
					}
					break;
				case SDL_SCANCODE_SPACE:
					player_shoot(&state->player, state->entities);
					break;
				case SDL_SCANCODE_R:
					event_dispatch(state, EV_GAME_RESTART, ev_game_restart);
					break;
				case SDL_SCANCODE_O:
					overlay_solution(state->level.maze, state->player.x, state->player.y, state->level.exit_x,
									 state->level.exit_y);
					break;
				case SDL_SCANCODE_G:
					state->render_graph = !state->render_graph;
					state_change_graph(state, 0);
					break;
				case SDL_SCANCODE_N:
					state->spawn_enemies = !state->spawn_enemies;
					if (!state->spawn_enemies) {
						event_dispatch(state, EV_ENEMY_DESTROY, ev_enemies_destroy);
					}
					break;
				case SDL_SCANCODE_H:
					state->render_graph_h = !state->render_graph_h;
					break;
				case SDL_SCANCODE_L:
					state_change_light(state, 1);
					break;
				case SDL_SCANCODE_Q:
					state_change_ren(state, 1);
					break;
				case SDL_SCANCODE_E:
					state_change_ren(state, -1);
					break;
				case SDL_SCANCODE_K:
					*running = 0;
					break;
				case SDL_SCANCODE_T:
					state_change_graph(state, 1);
					break;
				case SDL_SCANCODE_Y:
					state_change_graph(state, -1);
					break;
				case SDL_SCANCODE_1:
					state->ren_mode = REN_BLOCKS;
					break;
				case SDL_SCANCODE_2:
					state->ren_mode = REN_ENTITIES;
					break;
				case SDL_SCANCODE_3:
					state->ren_mode = REN_SOLUTION;
					break;
				case SDL_SCANCODE_0:
					state->ren_mode = REN_ALL;
					break;
				default:
					break;
			}
			break;
		default:
			break;
	}
}


void Event(state_t* state, double delta_time) {
	event_t* ev;
	while (!queue_isempty(state->events)) {
		ev = queue_dequeue(state->events);
		assert(ev != NULL);
		ev->callback(state);
		printf("EVENT   %-15s                     %ld\n", get_ev_type(ev), time(0) - state->start_time);
		free(ev);
	}
}

void Update(state_t* state, double delta_time) {
	entity_t* e1;
	entity_t* e2;
	int j, i, jm, im;
	//processing entities
	jm = alist_size(state->entities);
	for (j = 0; j < jm; ++j) {
		e1 = alist_get(state->entities, j);
		switch (e1->type) {
			case E_PEW:
				if (!pew_move(e1, state->level.maze, LVL_W, B_WALL)) {
					alist_rm_idx(state->entities, j);
					j--, jm--;
				} else {
					im = alist_size(state->entities);
					for (i = 0; i < im; ++i) {
						e2 = alist_get(state->entities, i);
						switch (e2->type) {
							case E_ENEMY:
								if (e1->x == e2->x && e1->y == e2->y) {
									e2->hp -= e1->pew.dmg;
									alist_rm_idx(state->entities, j);
									j--, jm--, i = im, im--;
								}
								break;
							default:
								break;
						}

					}
				}
				break;
			case E_ENEMY:
				if (e1->hp <= 0) {
					if (e1->enemy.path != NULL) {
						stack_destroy(e1->enemy.path);
					}
					e2 = ((entity_t*) (e1->enemy.origin));
					if (e2 != NULL) {
						e2->spawner.enemy_count--;
					}
					alist_rm_idx(state->entities, j);
					event_dispatch(state, EV_SCORE_INCR, ev_score_incr);
					j--, jm--;
				} else {
					if (e1->enemy.next_search == 0){
						printf("ENEMY   SEARCH_%-7s (%02d, %02d) -> (%02d, %02d) %ld\n", "NOFORCE", e1->x,
							   e1->y, state->player.x, state->player.y,
							   time(0) - state->start_time);
					}
					enemy_search(e1, &(state->player), &state->level, 0);
					enemy_fpath(e1, state->level.maze, state->level.w, B_WALL);
					if (state->player.x == e1->x && state->player.y == e1->y) {
						event_dispatch(state, EV_GAME_RESTART, ev_game_restart);
					}
				}
				break;
			case E_SPAWNER:
				spawner_spawn(e1, state);
			default:
				break;
		}
	}
	// processing player
	if (state->player.next_move > 0) {
		state->player.next_move--;
	}
	if (state->player.player.next_shot > 0) {
		state->player.player.next_shot--;
	}
	if (state->player.x == state->level.exit_x && state->player.y == state->level.exit_y) {
		event_dispatch(state, EV_LEVEL_NEXT, ev_level_next);
	}
}

void Render(state_t* state, SDL_Renderer* renderer, SDL_Texture* tex, TTF_Font* font) {
	#undef lvlxy
	#undef dlvlxy
	#define lvlxy(ox, oy) state->level.maze[((y + yoff + (oy)) * LVL_W) + (x + xoff + (ox))]
	#define dlvlxy(ox, oy) state->level.doodads[((y + yoff + (oy)) * LVL_W) + (x + xoff + (ox))]
	assert(state->level.doodads != NULL);
	assert(state->level.maze != NULL);
	float light;
	char text_buf[128];
	int i, x, y;
	int xoff = (int) (state->player.x / SCR_W) * SCR_W;
	int yoff = (int) (state->player.y / SCR_H) * SCR_H;
	SDL_Rect dest;
	SDL_Rect src;
	dest.h = BSIZE;
	dest.w = BSIZE;
	for (y = 0; y < SCR_H; ++y) {
		for (x = 0; x < SCR_W; ++x) {
			dest.x = BSIZE * x;
			dest.y = BSIZE * y;

			// rendering lights
			light = 0.0f;
			for (i = 0; i < alist_size(state->entities); ++i) {
				entity_t* source = alist_get(state->entities, i);
				if (source->type == E_LIGHT) {
					if (euclidean_dist(source->x, source->y, x + xoff, y + yoff) < 10) {
						light = light_calc(source, x + xoff, y + yoff, light, state);
					}
				}
			}
			light = light_calc(&state->player, x + xoff, y + yoff, light, state);
			SDL_SetTextureColorMod(tex, light, light * 0.8, light * 0.5);

			if (state->ren_mode == REN_BLOCKS || state->ren_mode == REN_ALL) {

				// rendering background
				switch (lvlxy(0, 0)) {
					case B_WALL:
						load_sprite(SPR_WALL, (spr_rect*) &src);
						break;
					case B_EXIT:
						load_sprite(SPR_DLADDER, (spr_rect*) &src);
						break;
					case B_PATH:
					case B_FLOOR:
						if (lvlxy(0, -1) == B_WALL) {
							load_sprite(SPR_TFLOOR, (spr_rect*) &src);
						} else {
							load_sprite(SPR_FLOOR, (spr_rect*) &src);
						}
						break;

				}
				SDL_RenderCopy(renderer, tex, &src, &dest);

				// rendering doodads
				switch (dlvlxy(0, 0)) {
					case D_BRICK:
						load_sprite(SPR_MBRICK, (spr_rect*) &src);
						break;
					case D_GRATE:
						if (lvlxy(0, 1) != B_WALL) {
							dest.y += BSIZE;
							load_sprite(SPR_OOZEF, (spr_rect*) &src);
							SDL_RenderCopy(renderer, tex, &src, &dest);
							dest.y -= BSIZE;
							load_sprite(SPR_GRATE, (spr_rect*) &src);
						}
						break;
					case D_OOZE:
						if (lvlxy(0, -1) == B_WALL) {
							load_sprite(SPR_OOZE, (spr_rect*) &src);
							dest.y -= BSIZE;
							SDL_RenderCopy(renderer, tex, &src, &dest);
							load_sprite(SPR_OOZEF, (spr_rect*) &src);
							dest.y += BSIZE;
						}
						break;
					case D_SKULL:
						SDL_RenderCopy(renderer, tex, &src, &dest);
						load_sprite(SPR_SKULL, (spr_rect*) &src);
						break;
					case D_PIPE1:
						load_sprite(SPR_PIPE, (spr_rect*) &src);
						break;
					case D_PIPE2:
						load_sprite(SPR_PIPE2, (spr_rect*) &src);
						break;
					case D_TORCH:
						load_sprite(SPR_TORCH, (spr_rect*) &src);
						break;
				}
				SDL_RenderCopy(renderer, tex, &src, &dest);

			}
			if (state->ren_mode == REN_ENTITIES || state->ren_mode == REN_ALL) {
				// rendering entities
				for (i = 0; i < alist_size(state->entities); ++i) {
					entity_t* e = alist_get(state->entities, i);
					assert(e != NULL);
					if (x + xoff == e->x && y + yoff == e->y) {
						switch (e->type) {
							case E_ENEMY:
								load_sprite(SPR_ENEMY1, (spr_rect*) &src);
								SDL_SetTextureColorMod(tex, light, light * 0.8 * (e->hp / E_DEF_HP),
													   light * 0.5 * (e->hp / E_DEF_HP));
								SDL_RenderCopy(renderer, tex, &src, &dest);
								SDL_SetTextureColorMod(tex, light, light * 0.8, light * 0.5);
								break;
							case E_PEW:
								load_sprite(SPR_FBOY, (spr_rect*) &src);
								dest.y -= 10;
								SDL_RenderCopy(renderer, tex, &src, &dest);
								dest.y += 10;
								break;
							case E_LIGHT:
								load_sprite(SPR_TORCH, (spr_rect*) &src);
								SDL_RenderCopy(renderer, tex, &src, &dest);
								break;
						}
					}
				}
			}

			if (state->ren_mode == REN_SOLUTION || state->ren_mode == REN_ALL) {
				if (lvlxy(0, 0) == B_PATH) {
					load_sprite(SPR_COIN, (spr_rect*) &src);
					SDL_RenderCopy(renderer, tex, &src, &dest);
				}
			}

			// rendering player
			if (x + xoff == state->player.x && y + yoff == state->player.y) {
				load_sprite(SPR_PLAYER, (spr_rect*) &src);
				SDL_RenderCopy(renderer, tex, &src, &dest);
			}

			if (state->ren_mode == REN_BLOCKS || state->ren_mode == REN_ALL) {
				if (lvlxy(0, 1) == B_WALL &&
					(lvlxy(0, 0) == B_FLOOR || lvlxy(0, 0) == B_PATH || lvlxy(0, 0) == B_EXIT)) {
					load_sprite(SPR_TWALL, (spr_rect*) &src);
					SDL_RenderCopy(renderer, tex, &src, &dest);
				}
			}
		}
	}

	if (state->render_graph && state->curr_graph != NULL && state->curr_graph->start != NULL &&
		state->curr_graph->end != NULL) {
		draw_node(renderer, font, xoff, yoff, state->curr_graph->start, state->render_graph_h);
	}
	snprintf(text_buf, 127, "Level: %d | Score: %d", state->levelc + 1, state->score);
	draw_text(renderer, font, text_buf, 10, 10, NULL);
	snprintf(text_buf, 127, "Enemies: %3s | Graph: %3s | Light: %4s | Render: %8s", state->spawn_enemies ? "ON" : "OFF",
			 state->render_graph ? "ON" : "OFF",
			 get_light_mode(state->light_mode), get_ren_mode(state->ren_mode));
	draw_text(renderer, font, text_buf, WIDTH - strnlen(text_buf, 127) * CHAR_W, 8, NULL);
	draw_help(renderer, font);

	#undef lvlxy
	#undef dlvlxy
}


void init_game(state_t* state) {
	state->events = queue_new(sizeof(event_t));
	state->start_time = time(0);
	event_dispatch(state, EV_GAME_START, ev_game_start);
	event_dispatch(state, EV_GAME_RESTART, ev_game_restart);
}
