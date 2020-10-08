create view view1 as select AirportID, AirportName, NumTerminals, count(route.routeId) as routes from airport join route on airport.AirportId = route.fromAirport group by airport.airportId having(routes >= 2 and NumTerminals > 2) order by NumTerminals desc, routes desc;

