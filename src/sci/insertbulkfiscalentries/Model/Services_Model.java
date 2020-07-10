package sci.insertbulkfiscalentries.Model;

import SimpleDotEnv.Env;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import sci.insertbulkfiscalentries.Model.Entities.Service;
import sql.Database;

public class Services_Model {

    private Map<String, Service> services = new HashMap<>();

    public Map<String, Service> getServices() {
        return services;
    }

    public void setServices(Map<String, Service> services) {
        this.services = services;
    }

    public Map<String, Service> getDatabaseServices(Integer enterpriseCode) {
        //clear
        services.clear();

        //Get SQl Script
        File sqlFile = fileManager.FileManager.getFile("sql/getProductsServices.sql");
        Map<String, String> sqlChanges = new HashMap<>();
        sqlChanges.put("enterpriseCode", enterpriseCode.toString());
        sqlChanges.put("typeServiceCode", Env.get("typeServiceCode"));

        //Get database values
        ArrayList<String[]> databaseServices = Database.getDatabase().select(sqlFile, sqlChanges);

        for (String[] databaseService : databaseServices) {
            try{
                Service service = new Service();
                service.setCode(Integer.valueOf(databaseService[0]));
                service.setType(Integer.valueOf(databaseService[1]));
                service.setName(databaseService[2]);
                service.setCfop(Integer.valueOf(databaseService[3]));

                services.put(service.getName(), service);
            }catch(Exception e){                
            }
        }

        return services;
    }
}
