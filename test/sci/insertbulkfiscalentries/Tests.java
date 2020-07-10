package sci.insertbulkfiscalentries;

import fileManager.FileManager;
import java.util.LinkedHashMap;
import java.util.Map;
import sci.insertbulkfiscalentries.Model.Cities_ModelTest;
import sci.insertbulkfiscalentries.Model.Services_ModelTest;
import sci.insertbulkfiscalentries.Model.States_ModelTest;
import sci.insertbulkfiscalentries.Model.Test;
import sql.Database;


public class Tests {

    public static void main(String[] args) {
        System.out.println("Initializing tests...");
        
        System.out.println("Connecting to database\n\n");
        Database.setStaticObject(new Database(FileManager.getFile("sci.cfg")));
        
        Map<String, Test> tests = new LinkedHashMap<>();
        tests.put("States_Model Test", new States_ModelTest());
        tests.put("Cities_Model Test", new Cities_ModelTest());
        tests.put("Services_Model Test", new Services_ModelTest());
        
        
        //Run tests
        for (Map.Entry<String, Test> entry : tests.entrySet()) {
            String testName = entry.getKey();
            Test test = entry.getValue();
            
            System.out.println("Testando " + testName);
            test.run();
            System.out.println("Teste finalizado: " + testName);
            System.out.println("\n");
        }
    }
    
}
