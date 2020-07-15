package sci.insertbulkfiscalentries.Control;

import Entity.Executavel;
import fileManager.FileManager;
import sci.insertbulkfiscalentries.View.Home;
import sci.insertbulkfiscalentries.View.UserInputs;
import sql.Database;

public class Controller {

    //Models
    private UserInputs userInputs = new UserInputs();
    private Home frame = new Home();

    private Integer enterprise;
    
    public class setDatabase extends Executavel{

        public setDatabase() {
            name = "Definindo banco de dados...";
        }

        @Override
        public void run() {
            Database.setStaticObject(new Database(FileManager.getFile("sci.cfg")));
            if(!Database.getDatabase().testConnection()){
                throw new Error("Não foi possível conectar ao banco de dados!");
            }
        }                        
    }

    public class setEnterprise extends Executavel {

        public setEnterprise() {
            name = "Definindo número de empresa.";
        }

        @Override
        public void run() {
            enterprise = userInputs.getEnterpriseCode();
            frame.setEnterpriseCode(enterprise);            
        }

    }

    public class showJframe extends Executavel {

        public showJframe() {
            name = "Exibindo o JFrame";
        }

        @Override
        public void run() {
            frame.setVisible(true);
            frame.setServicesList();
            frame.setStatesList();
        }

    }
}
