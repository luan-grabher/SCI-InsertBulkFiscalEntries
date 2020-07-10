package sci.insertbulkfiscalentries.Model;

import fileManager.FileManager;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import sci.insertbulkfiscalentries.Model.Entities.State;
import sql.Database;

public class States_Model {
    private Map<String, State> states = new HashMap<>();
    
    public Map<String, State> getStatesFromDb(){
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
        
        return states;
    }

    public Map<String, State> getStates() {
        return states;
    }

    public void setStates(Map<String, State> states) {
        this.states = states;
    }        
}
