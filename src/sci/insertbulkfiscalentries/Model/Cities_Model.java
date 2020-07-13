package sci.insertbulkfiscalentries.Model;

import fileManager.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import sci.insertbulkfiscalentries.Model.Entities.City;
import sql.Database;

public class Cities_Model {

    private Map<String, City> cities = new HashMap<>();

    public Map<String, City> getStateCitiesFromDb(Integer stateCode) {
        //clear map
        cities.clear();

        //Get Sql script
        File sqlFile = FileManager.getFile("sql/getStateCities.sql");
        Map<String, String> sqlChanges = new HashMap<>();
        sqlChanges.put("coduf", stateCode.toString());

        //Get list of database states
        ArrayList<String[]> databaseCities = Database.getDatabase().select(sqlFile, sqlChanges);

        //Percorre todos estados do banco
        for (String[] databaseCity : databaseCities) {
            //Cria objeto
            City city = new City();
            city.setCode(Integer.valueOf(databaseCity[0]));
            city.setName(databaseCity[2]);

            //Adiciona objeto no mapa
            cities.put(city.getName(), city);
        }

        //Sort results
        cities = new TreeMap<>(cities);
        
        return cities;
    }

    public Map<String, City> getCities() {
        return cities;
    }

    public void setCities(Map<String, City> cities) {
        this.cities = cities;
    }
}
