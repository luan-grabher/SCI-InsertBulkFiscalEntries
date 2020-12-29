package sci.insertbulkfiscalentries.View;

import javax.swing.JOptionPane;


public class UserInputs {
    public static Integer getEnterpriseCode(){
        try{
            return Integer.valueOf(JOptionPane.showInputDialog("Insira o número da empresa:"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Número de empresa inválido!","Empresa incorreta!",JOptionPane.ERROR_MESSAGE);
            throw new Error("Número de empresa inválido!");
        }
    }
}
