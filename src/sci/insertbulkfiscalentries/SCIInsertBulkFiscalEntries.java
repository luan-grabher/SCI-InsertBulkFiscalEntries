package sci.insertbulkfiscalentries;

import Entity.Executavel;
import Executor.Execution;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sci.insertbulkfiscalentries.Control.Controller;

public class SCIInsertBulkFiscalEntries {


    public static void main(String[] args) {
        run();                      
    }
    
    public static void run(){
        Controller controller = new Controller();
        
        try {
            controller.new setDatabase().run();
            controller.new setEnterprise().run();
            controller.new showJframe().run(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(controller.frame, "Erro: " + e.getMessage(), "Ocorreu um erro na execução!", 0);
            System.exit(0);
        }           
    }
    
}
