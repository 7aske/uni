select depDay, count(flight.flightId) as perDay from route join flight on route.routeId = flight.routeId join flightdep on flightdep.flightId = flight.flightId group by depDay having count(flight.flightId) > (select count(flightdep.flightId)/7 from flightdep);

