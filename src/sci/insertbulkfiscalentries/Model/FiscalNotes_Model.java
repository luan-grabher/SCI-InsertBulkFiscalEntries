package sci.insertbulkfiscalentries.Model;

import java.math.BigDecimal;
import java.util.Calendar;
import sci.insertbulkfiscalentries.Model.Entities.City;
import sci.insertbulkfiscalentries.Model.Entities.Cnpj;
import sci.insertbulkfiscalentries.Model.Entities.Service;

public class FiscalNotes_Model {
    private String code; //Número de NF
    private Calendar date;
    private City city;
    private Service service;
    private BigDecimal value;
    private Cnpj cnpj; 
    
}
