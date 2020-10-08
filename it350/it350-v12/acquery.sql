 select * from flight join aircraft on aircraft.AircraftID = flight.AircraftID join aircrafttype on aircraft.AircraftTypeID = aircrafttype.AircraftTypeID where AircraftName like 'Boeing%';

