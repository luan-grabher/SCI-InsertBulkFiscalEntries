package sci.insertbulkfiscalentries.Model;

import fileManager.FileManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import sci.insertbulkfiscalentries.Model.Entities.State;

public class States_Model {
    private Map<String, State> states = new HashMap<>();
    
    public Map<String, State> getStatesFromDb(){
        //clear map
        states.clear();
        
        //Get Sql script
        File sqlFile = FileManager.getFile("sql/getStates.sql");        
        String sqlScript = FileManager.getText(sqlFile);
        
        
        return states;
    }
}
