package sci.insertbulkfiscalentries.Model;

import java.util.Map;
import sci.insertbulkfiscalentries.Model.Entities.Service;


public class Services_ModelTest extends Test{    

    @Override
    public void run() {
        //Variables
        Integer enterpriseCode = 331;
        String keyTest = "Energia Elétrica";
        
        //Instance
        Services_Model instance = new Services_Model();
        
        //Test method
        Map<String,Service> results = instance.getDatabaseServices(enterpriseCode);
        
        if(results.size() < 1){
            throw new Error("Não existe nenhum serviço no banco de dados para a empresa " + enterpriseCode);
        }
        
        if(!results.containsKey(keyTest)){
            throw new Error("Não foi encontrado o serviço: " + keyTest);
        }
    }    
}
