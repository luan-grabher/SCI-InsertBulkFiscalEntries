package sci.insertbulkfiscalentries.Model.Entities;

import java.math.BigDecimal;

public class State extends DatabaseObject{
    private String abbreviation;
    private BigDecimal aliquot;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }        
}
