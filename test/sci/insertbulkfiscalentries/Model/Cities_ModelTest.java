
package sci.insertbulkfiscalentries.Model;

import java.util.Map;
import sci.insertbulkfiscalentries.Model.Entities.City;


public class Cities_ModelTest extends Test{

    @Override
    public void run() {
        Integer RSCode = 23;
        
        //Make instance
        Cities_Model instance = new Cities_Model();
        Map<String, City> results =  instance.getStateCitiesFromDb(23);                
                
        if(results.size() != 497){
            throw new Error("As cidades não são as mesmas de antes!");
        }
    }       
}
