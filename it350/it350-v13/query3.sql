 select aircraft.AircraftId, AircraftName, count(AircraftName), status from aircraft
join aircrafttype on aircraft.AircraftTypeId = aircrafttype.AircraftTypeId
left join flight on flight.aircraftId = aircraft.aircraftId
left join route on route.routeId = flight.routeId

group by AircraftName
having status = 1;
