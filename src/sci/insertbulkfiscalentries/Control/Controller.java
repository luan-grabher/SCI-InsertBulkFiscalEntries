package sci.insertbulkfiscalentries.Control;

import Entity.Executavel;
import fileManager.FileManager;
import java.io.File;
import sci.insertbulkfiscalentries.Model.FiscalNotesFile;
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

}
