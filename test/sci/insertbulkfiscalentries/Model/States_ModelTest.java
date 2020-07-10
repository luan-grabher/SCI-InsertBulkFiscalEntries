
package sci.insertbulkfiscalentries.Model;

import java.util.Map;
import sci.insertbulkfiscalentries.Model.Entities.State;


public class States_ModelTest extends Test{

    @Override
    public void run() {
        //Make instance
        States_Model instance = new States_Model();
        Map<String, State> results =  instance.getStatesFromDb();
        
        if(results.size() != 29){
            throw new Error("Os estados não são os mesmos de antes!");
        }
    }       
}
