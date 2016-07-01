package contracts;

import models.City;

public interface ICity {

	int readCityCount();
	
	int createCity(City city);
	
}
