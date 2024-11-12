package sci.insertbulkfiscalentries.Model;

import CNPJ.CNPJ;
import Dates.Dates;
import JExcel.XLSX;
import fileManager.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;
import static sci.insertbulkfiscalentries.SCIInsertBulkFiscalEntries.cnpjErrors;
import static sci.insertbulkfiscalentries.SCIInsertBulkFiscalEntries.ini;

public class FiscalNotesFile {

    private static List<Map<String, Object>> fiscalNotes = new ArrayList<>();
    private static File file;
    private static final String defaultStringXml = FileManager.getText(FileManager.getFile("insertBulkFiscalEntries.xml"));

    public static void getFiscalNotes(File file) {
        FiscalNotesFile.file = file;

        Map<String, Map<String, String>> config = new LinkedHashMap<>();

        config.put("prestadorCnpj", XLSX.convertStringToConfig("cnpj", ini.get("Colunas", "cnpj")));
        config.put("nf", XLSX.convertStringToConfig("nf", ini.get("Colunas", "nf")));
        config.put("data", XLSX.convertStringToConfig("data", ini.get("Colunas", "data")));

        /*Valor liquido antes do valor por causa das substituições*/
        config.put("liquido", XLSX.convertStringToConfig("liquido", ini.get("Colunas", "liquido")));

        config.put("valor", XLSX.convertStringToConfig("valor", ini.get("Colunas", "valor")));
        config.put("discriminacao", XLSX.convertStringToConfig("discriminacao", ini.get("Colunas", "discriminacao")));
        config.put("pis", XLSX.convertStringToConfig("pis", ini.get("Colunas", "pis")));
        config.put("cofins", XLSX.convertStringToConfig("cofins", ini.get("Colunas", "cofins")));
        config.put("ir", XLSX.convertStringToConfig("ir", ini.get("Colunas", "ir")));
        config.put("csll", XLSX.convertStringToConfig("csll", ini.get("Colunas", "csll")));
        config.put("iss", XLSX.convertStringToConfig("iss", ini.get("Colunas", "iss")));
        config.put("issRetido", XLSX.convertStringToConfig("issRetido", ini.get("Colunas", "issRetido")));
        config.put("aliquota", XLSX.convertStringToConfig("aliquota", ini.get("Colunas", "aliquota")));

        fiscalNotes = XLSX.get(file, config);
    }

    public static void createXmlOfFiscalNotes() {
        try {
            //Municipios
            Section municipios = (Section) (new Ini(FileManager.getFile("municipios.ini"))).get("codigos");

            //Pega cnpj da empresa
            String cnpj = ini.get("Config", "cnpj");

            //Percorre todas nfs
            fiscalNotes.forEach((map) -> {                
                //Pega texto xml padrao
                String[] xmlString = new String[]{defaultStringXml};

                xmlString[0] = xmlString[0].replaceAll(":tomadorCnpj", cnpj);

                //Buscar com API a razao social do CNPJ :prestadorRazaoSocial
                Map<String, String> prestador = new HashMap<>();
                Map<String, String> prestadorInfo = CNPJ.get((String) map.get("prestadorCnpj"));
                if (prestadorInfo != null) {
                    prestador.put("RazaoSocial", prestadorInfo.get("Nome da empresa"));
                    prestador.put("Rua", prestadorInfo.get("Rua"));
                    prestador.put("RuaNumero", prestadorInfo.get("Rua numero"));
                    prestador.put("Bairro", prestadorInfo.get("Bairro"));
                    prestador.put("UF", prestadorInfo.get("UF"));
                    prestador.put("CEP", prestadorInfo.get("CEP"));
                    prestador.put("Cidade", prestadorInfo.get("Cidade"));

                }else{
                    cnpjErrors.append("\r\n").append((String) map.get("prestadorCnpj"));
                }

                xmlString[0] = xmlString[0].replaceAll(":prestadorMunicipio", (String) municipios.getOrDefault(prestador.getOrDefault("Cidade", ""), ""));
                xmlString[0] = xmlString[0].replaceAll(":prestadorRazaoSocial", prestador.getOrDefault("RazaoSocial", ""));
                xmlString[0] = xmlString[0].replaceAll(":prestadorRuaNumero", prestador.getOrDefault("RuaNumero", ""));
                xmlString[0] = xmlString[0].replaceAll(":prestadorRua", prestador.getOrDefault("Rua", ""));
                xmlString[0] = xmlString[0].replaceAll(":prestadorBairro", prestador.getOrDefault("Bairro", ""));
                xmlString[0] = xmlString[0].replaceAll(":prestadorUF", prestador.getOrDefault("UF", ""));
                xmlString[0] = xmlString[0].replaceAll(":prestadorCEP", prestador.getOrDefault("CEP", ""));

                //--Percorre colunas
                map.forEach((name, val) -> {
                    val = val == null ? "" : val;

                    String str = val.toString();

                    //Converte a data se for data
                    if (str.startsWith("java.util.GregorianCalendar")) {
                        str = Dates.getCalendarInThisStringFormat((Calendar) val, "yyyy-MM-dd");
                    }

                    //----Para cada coluna da replace no valor
                    xmlString[0] = xmlString[0].replaceAll(":" + name, str);
                });

                //--Salva arquivo xml com nome da nota e cnpj na mesma pasta do arquivo excel
                String cnpjPrestador = (String) map.get("prestadorCnpj");
                String cnpjTomardor = cnpj;

                String nomeArquivoXML = (String) map.get("nf") + "_T" + cnpjTomardor + ".xml";
                String caminhoArquivoXML = file.getParent() + "\\" + nomeArquivoXML;
                String textoArquivoXML = xmlString[0];

                FileManager.save(caminhoArquivoXML, textoArquivoXML);
            });

        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
