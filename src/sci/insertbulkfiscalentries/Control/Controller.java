package sci.insertbulkfiscalentries.Control;

import Entity.Executavel;
import fileManager.FileManager;
import java.io.File;
import javax.swing.JOptionPane;
import sci.insertbulkfiscalentries.Model.FiscalNotesFile;
import sci.insertbulkfiscalentries.SCIInsertBulkFiscalEntries;
import static sci.insertbulkfiscalentries.SCIInsertBulkFiscalEntries.cnpjErrors;
import sql.Database;

public class Controller {
    
    public Integer enterprise;

    public class setDatabase extends Executavel {

        @Override
        public void run() {
            Database.setStaticObject(new Database(FileManager.getFile("sci.cfg")));
            if (!Database.getDatabase().testConnection()) {
                throw new Error("Não foi possível conectar ao banco de dados!");
            }
        }
    }

    public class convertFiscalNotesMapToXml extends Executavel {

        private File file;

        public convertFiscalNotesMapToXml(File file) {
            this.file = file;
        }

        @Override
        public void run() {
                FiscalNotesFile.getFiscalNotes(file);
                FiscalNotesFile.createXmlOfFiscalNotes();
        }
    }
    
    public class verifyCNPJErros extends Executavel{
        private File file;

        public verifyCNPJErros(File file) {
            this.file = file;
        }                
        
        @Override
        public void run() {
            if (cnpjErrors.length() > 0 ){
                String text = "Não foi possível buscar as informações (cidades) dos seguintes CNPJs:" + cnpjErrors.toString();
                
                FileManager.save(file.getParentFile(), "Erros de CNPJ.txt", text);
                JOptionPane.showMessageDialog(null, "Alguns CNPJs ficaram sem cidade, tente novamente em alguns minutos.\nA lista com os CNPJs com erros ficou no arquivo"
                        + " 'Erros de CNPJ.txt' na pasta '" + file.getParent() + "'");
            }
        }
        
    }

}
