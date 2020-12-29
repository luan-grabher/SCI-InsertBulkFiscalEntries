package sci.insertbulkfiscalentries.Model;

import fileManager.FileManager;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import sci.insertbulkfiscalentries.Model.Entities.City;
import sci.insertbulkfiscalentries.Model.Entities.State;
import sql.Database;

public class States_Model {
    
    /**
     * Retorna um mapa com os estados cadastrados no único
     * @return mapa com os estados cadastrados no único
     */
    public static Map<String, State> getStatesFromDb(){
        Map<String, State> states = new HashMap<>();
        //clear map
        states.clear();
        
        //Get Sql script
        File sqlFile = FileManager.getFile("sql/getStates.sql");        
        
        //Get list of database states
        ArrayList<String[]> databaseStates = Database.getDatabase().select(sqlFile);
        
        //Percorre todos estados do banco
        for (String[] databaseState : databaseStates) {
            //Cria objeto
            State state = new State();
            state.setCode(Integer.valueOf(databaseState[0]));
            state.setAbbreviation(databaseState[1]);
            state.setName(databaseState[2]);
            state.setAliquot(new BigDecimal(databaseState[3]==null?"0":databaseState[3]));
            
            //Adiciona objeto no mapa
            states.put(state.getName(), state);
        }
        
        //SortMap by key
        states = new TreeMap<>(states);
        return states;
    }

    
    /**
     * Retorna um mapa com as cidades do estado informado
     * @param stateCode Código do estado no único
     * @return mapa com as cidades do estado informado
     */
    public static Map<String, City> getStateCitiesFromDb(Integer stateCode) {
        Map<String, City> cities = new HashMap<>();

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
}
