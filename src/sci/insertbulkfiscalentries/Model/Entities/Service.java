package sci.insertbulkfiscalentries.Model.Entities;

public class Service extends DatabaseObject{
       private Integer type;
       private Integer cfop;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }             
}
