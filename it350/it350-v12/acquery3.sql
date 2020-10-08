select flight.FlightID, AirportName, CityName, DepDay, DepTime from flight join flightdep on flight.FlightID = flightdep.FlightID join route on flight.RouteID = route.RouteID join airport on airport.AirportID = fromAirport order by DepDay asc, DepTime asc;

select * from flight_schedule where (AirportName like '%Orly%' or AirportName like "%Azur%") and (DepDay >= 5 and DepDay <= 7) and (DepTime >= '09:00' and DepTime <= '15:00');

