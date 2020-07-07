package sci.insertbulkfiscalentries.Model.Entities;

import java.math.BigDecimal;
import java.util.Calendar;

public class FiscalNote {
    private Integer id;
    private String code;    
    private Calendar date;
    private City city;
    private Service service;
    private BigDecimal value;
    private Cnpj cnpj; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
}
