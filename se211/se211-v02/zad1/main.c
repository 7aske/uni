#include <stdio.h>
#include <time.h>
#include <spandsp.h>
#include <assert.h>

enum SEASONS {
	SEASON_INVALID = -1,
	SEASON_WINTER = 0,
	SEASON_SPRING = 1,
	SEASON_SUMMER = 2,
	SEASON_AUTUMN = 3,
};

typedef enum SEASONS season_t;

season_t get_season(struct tm* date) {
	if (date->tm_mon <= TM_MARCH && date->tm_mday <= 21)
		return SEASON_WINTER;
	else if (date->tm_mon <= TM_JUNE && date->tm_mday <= 21)
		return SEASON_SPRING;
	else if (date->tm_mon <= TM_SEPTEMBER && date->tm_mday <= 23)
		return SEASON_SUMMER;
	else if (date->tm_mon <= TM_DECEMBER && date->tm_mday <= 21)
		return SEASON_AUTUMN;
	if (date->tm_mon == TM_DECEMBER && date->tm_mday > 21)
		return SEASON_WINTER;
	else
		return SEASON_INVALID;
}

int main() {
	time_t now = time(NULL);
	struct tm* date = localtime(&now);

	struct tm date1;
	date1.tm_mday = 11;
	date1.tm_mon = 6;

	struct tm date2;
	date2.tm_mday = 11;
	date2.tm_mon = 11;

	assert(get_season(date) == SEASON_WINTER);
	assert(get_season(&date1) == SEASON_SUMMER);
	assert(get_season(&date2) == SEASON_AUTUMN);

	return 0;
}
