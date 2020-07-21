package sci.insertbulkfiscalentries.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import sci.insertbulkfiscalentries.Model.Entities.FiscalNote;
import sci.insertbulkfiscalentries.Model.Entities.ServiceMoviment;


/* Modelo do Movimento de Serviço*/
public class ServiceMoviment_Model {

    //Cria lista de movimentos de serviço
    private List<ServiceMoviment> serviceMoviments;

    /**
     * Cria lista de movimentos de serviço com base nas Nfs da tabela da View
     *
     * @param fiscalNotes Lista de notas fiscais retiradas da tabela da View
     * @return A lista privada de movimentos de serviço
     */
    public List<ServiceMoviment> createServiceovimentsList(List<FiscalNote> fiscalNotes) {
        //Começa a lista de novo
        serviceMoviments = new ArrayList<>();

        //Percorre a lista de notas fiscais
        //  Cria objeto de movimento de serviço
        //  Define os campos do objeto conforme os campos da nota fiscal
        //  Adiciona na lista de movimentos de serviço o objeto
        return serviceMoviments;
    }

    public List<ServiceMoviment> getServiceMoviments() {
        return serviceMoviments;
    }

    /**
     * Cria arquivo de importação com a lista de movimentos de serviço
     *
     * @return O arquivo que foi salvo
     */
    public File createServiceMovimentsImportationFile() {
        //Inicializa StringBuilder
        
        //Percorre movimentos de serviço
        //  Adiciona na StringBuilder os campos do movimento
        //  Separa o campo com virgula
        
        //Cria arquivo com FileManager com o texto do StringBuilder na Área de Trabalho
        
        //Retorna arquivo criado na área de trabalho
        return null;
    }

}
