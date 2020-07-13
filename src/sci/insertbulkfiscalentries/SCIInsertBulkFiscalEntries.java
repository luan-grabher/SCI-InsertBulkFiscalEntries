package sci.insertbulkfiscalentries;

import Entity.Executavel;
import Executor.Execution;
import java.util.ArrayList;
import java.util.List;
import sci.insertbulkfiscalentries.Control.Controller;

public class SCIInsertBulkFiscalEntries {


    public static void main(String[] args) {
        run();                      
    }
    
    public static void run(){                
        Controller controller = new Controller();

        List<Executavel> execs = new ArrayList<>();
        execs.add(controller.new setDatabase());
        execs.add(controller.new setEnterprise());
        execs.add(controller.new showJframe());
        
        
        Execution execution = new Execution("Iniciar Programa");
        execution.setShowMessages(false);
        execution.setExecutables(execs);
        execution.runExecutables();
        execution.endExecution(false);
    }
    
}
