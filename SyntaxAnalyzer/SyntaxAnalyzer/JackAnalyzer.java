package SyntaxAnalyzer;
import java.io.File;
import java.util.ArrayList;


//Jack analyser class
public class JackAnalyzer 
{

    public static ArrayList<File> getJackFiles(File dir){

        File[] files = dir.listFiles();

        ArrayList<File> result = new ArrayList<File>();

        if (files == null) return result;

        for (File f:files){

            if (f.getName().endsWith(".jack")){

                result.add(f);

            }

        }

        return result;

    }

    public static void main(String[] args) {

        if (args.length != 1){

            //System.out.println("Usage:java JackAnalyzer [filename|directory]");

        }
        else 
        {

            String fileInName ="SquareGame.jack" ;

            File fileIn = new File(fileInName);

            String fileOutPath = "", tokenFileOutPath = "";

            File fileOut,tokenFileOut;

            ArrayList<File> jackFiles = new ArrayList<File>();

            if (fileIn.isFile()) {

                //if it is a single file, see whether it is a vm file
                String path = fileIn.getAbsolutePath();

                if (!path.endsWith(".jack")) {

                    throw new IllegalArgumentException(".jack file is required!");

                }

                jackFiles.add(fileIn);

            } 
            else if (fileIn.isDirectory()) {

                //if it is a directory get all jack files under this directory
                jackFiles = getJackFiles(fileIn);

                //if no vn file in this directory
                if (jackFiles.size() == 0) {

                    throw new IllegalArgumentException("No jack file in this directory");

                }

            }

            for (File f: jackFiles) {

                fileOutPath = f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf(".")) + ".xml";
                tokenFileOutPath = f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf(".")) + "T.xml";
                fileOut = new File(fileOutPath);
                tokenFileOut = new File(tokenFileOutPath);

                CompilationEngine compilationEngine = new CompilationEngine(f,fileOut,tokenFileOut);
                compilationEngine.compileClass();

                System.out.println("File created : " + fileOutPath);
                System.out.println("File created : " + tokenFileOutPath);
            }

        }

    }
}
