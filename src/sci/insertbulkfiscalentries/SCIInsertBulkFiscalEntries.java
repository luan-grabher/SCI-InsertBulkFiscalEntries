package sci.insertbulkfiscalentries;

import Entity.Executavel;
import Executor.Execution;
import fileManager.Args;
import fileManager.FileManager;
import fileManager.Selector;
import java.io.File;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.ini4j.Ini;
import sci.insertbulkfiscalentries.Control.Controller;

public class SCIInsertBulkFiscalEntries {

    public static Ini ini = null;

    public static void main(String[] args) {
        //String iniPath = "\\\\heimerdinger\\docs\\Informatica\\Programas\\Moresco\\01 - Programas\\";
        String iniName = Args.get(args, "ini") == null?"": Args.get(args, "ini");

        //iniName = ""; //Para testes pega o .ini da pasta do programa
        if (null != iniName) {
            try {
                ini = new Ini(FileManager.getFile(iniName + ".ini"));

                //Fornece o arquivo Excel se a pessoa quiser
                int choice = JOptionPane.showOptionDialog(null,
                        "O que você deseja?",
                        "Make your choice!",
                        0,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Criar XMLs para importação com arquivo Excel", "Baixar o arquivo Excel exemplo"},
                        0
                );

                if (choice == 0) {
                    //Pega arquivo XLSX com Notas
                    JOptionPane.showMessageDialog(null, "Escolha o arquivo Excel com as notas para serem importadas:");
                    File file = Selector.selectFile("", "Excel XLSX", ".xlsx");
                    if (file != null) {
                        run(file);
                    } else {
                        throw new Error("Arquivo inválido!");
                    }
                } else {
                    File file = new File("Exemplo Inserir NFs.xlsx");
                    File copyFile = new File(System.getProperty("user.home") + "\\Desktop\\Exemplo Inserir NFs.xlsx");
                    Files.copy(file.toPath(), copyFile.toPath());
                    
                    JOptionPane.showMessageDialog(null, "Arquivo salvo em: "+ copyFile.getPath());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (Error e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Argumento .ini não informado no atalho do programa!", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void run(File file) {
        Controller controller = new Controller();

        try {
            Execution exec = new Execution("Inserir NFs");

            Map<String, Executavel> execs = new LinkedHashMap<>();
            //execs.put("Conectando ao banco de dados", controller.new setDatabase());
            execs.put("Criar xml das notas do arquivo", controller.new convertFiscalNotesMapToXml(file));

            exec.setExecutionMap(execs);
            exec.runExecutables();
            exec.endExecution(false);
            JOptionPane.showMessageDialog(null, "Arquivos XMLs salvos em:\n" + file.getParent());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Ocorreu um erro na execução!", 0);
            System.exit(0);
        } catch (Error e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Ocorreu um erro na execução!", 0);
            System.exit(0);
        }
    }

}
