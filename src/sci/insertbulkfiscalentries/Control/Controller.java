package sci.insertbulkfiscalentries.Control;

import Entity.Executavel;
import sci.insertbulkfiscalentries.View.Home;
import sci.insertbulkfiscalentries.View.UserInputs;

public class Controller {

    //Models
    private UserInputs userInputs = new UserInputs();
    private Home frame = new Home();

    private Integer enterprise;

    public class setEnterprise extends Executavel {

        public setEnterprise() {
            name = "Definindo número de empresa.";
        }

        @Override
        public void run() {
            enterprise = userInputs.getEnterpriseCode();
        }

    }

    public class showJframe extends Executavel {

        public showJframe() {
            name = "Exibindo o JFrame";
        }

        @Override
        public void run() {
            frame.setVisible(true);
        }

    }
}
