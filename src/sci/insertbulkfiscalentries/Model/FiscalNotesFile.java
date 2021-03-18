package sci.insertbulkfiscalentries.Model;

import JExcel.XLSX;
import fileManager.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static sci.insertbulkfiscalentries.SCIInsertBulkFiscalEntries.ini;

public class FiscalNotesFile {

    private static List<Map<String, Object>> fiscalNotes = new ArrayList<>();
    private static File file;
    private static final String defaultStringXml = FileManager.getText(FileManager.getFile(ini.get("Config", "defaultXml")));

    public static void getFiscalNotes(File file) {
        FiscalNotesFile.file = file;

        Map<String, Map<String, String>> config = new HashMap<>();

        config.put("prestadorCnpj", XLSX.convertStringToConfig("cnpj", ini.get("Colunas", "cnpj")));
        config.put("nf", XLSX.convertStringToConfig("nf", ini.get("Colunas", "nf")));
        config.put("data", XLSX.convertStringToConfig("data", ini.get("Colunas", "data")));
        config.put("valor", XLSX.convertStringToConfig("valor", ini.get("Colunas", "valor")));
        config.put("discriminacao", XLSX.convertStringToConfig("discriminacao", ini.get("Colunas", "discriminacao")));
        config.put("pis", XLSX.convertStringToConfig("pis", ini.get("Colunas", "pis")));
        config.put("cofins", XLSX.convertStringToConfig("cofins", ini.get("Colunas", "cofins")));
        config.put("ir", XLSX.convertStringToConfig("ir", ini.get("Colunas", "ir")));
        config.put("csll", XLSX.convertStringToConfig("csll", ini.get("Colunas", "csll")));
        config.put("iss", XLSX.convertStringToConfig("iss", ini.get("Colunas", "iss")));
        config.put("issRetido", XLSX.convertStringToConfig("issRetido", ini.get("Colunas", "issRetido")));
        config.put("aliquota", XLSX.convertStringToConfig("aliquota", ini.get("Colunas", "aliquota")));
        config.put("valorLiquido", XLSX.convertStringToConfig("valorLiquido", ini.get("Colunas", "valorLiquido")));

        fiscalNotes = XLSX.get(file, config);
    }

    public static void createXmlOfFiscalNotes() {
        //Pega cnpj da empresa
        String cnpj = ini.get("Config", "cnpj");

        //Percorre todas nfs
        fiscalNotes.forEach((map) -> {
            //Pega texto xml padrao
            String[] xmlString = new String[]{defaultStringXml};

            xmlString[0] = xmlString[0].replaceAll(":tomadorCnpj", cnpj);

            //Buscar com API a razao social do CNPJ :prestadorRazaoSocial
            String prestadorRazao = "";
            Map<String, String> prestadorInfo=  CNPJ.CNPJ.get((String) map.get("prestadorCnpj"));
            if(prestadorInfo != null){
                prestadorRazao = prestadorInfo.get("Nome da empresa");
            }
            xmlString[0] = xmlString[0].replaceAll(":prestadorRazaoSocial", prestadorRazao);
            
            //--Percorre colunas
            map.forEach((name, val) -> {
                //----Para cada coluna da replace no valor
                xmlString[0] = xmlString[0].replaceAll(":" + name, (String) val);
            });

            //--Salva arquivo xml com nome da nota e cnpj na mesma pasta do arquivo excel
            FileManager.save(file.getParent() + "\\" + (String) map.get("nf") + ".xml", xmlString[0]);
        });

    }
}
