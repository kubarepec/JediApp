package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileImportExport {



    public static void objectToFile(String path) throws FileNotFoundException {

            File file = new File(path);
            PrintWriter pw = new PrintWriter(file);
            StringBuilder jediS=new StringBuilder();
            for(Jedi j:Jedi.listaJedi){


                jediS.append(Szyfrowanie.szyfr(j.getNazwa()));
                jediS.append("<X>");
                jediS.append(Szyfrowanie.szyfr(j.getKolor().name()));
                jediS.append("<X>");
                jediS.append(Szyfrowanie.szyfr(Integer.toString(j.getMoc())));
                jediS.append("<X>");
                jediS.append(Szyfrowanie.szyfr(Boolean.toString(j.getCzyJasna())));
                jediS.append("<X>");
                jediS.append(Szyfrowanie.szyfr(Integer.toString(j.getIdJedi())));
                jediS.append("<X>");
                if(j.getZakon()==null){
                    jediS.append("0");
                }
                else{
                    jediS.append(Szyfrowanie.szyfr(Integer.toString(j.getZakon().getIdZakon())));
                }
                jediS.append("\n");
            }
            pw.print(jediS);
            pw.close();

    }

    public static void fileToObject(String path) throws FileNotFoundException {
        Jedi.listaJedi.clear();
        String[] jediLineT= new String[6];
        File file = new File(path);
        Scanner scanner= new Scanner(file);
        while (scanner.hasNextLine()){
            jediLineT=scanner.nextLine().split("<X>");
            Jedi j=new Jedi(Szyfrowanie.deszyfr(jediLineT[0]),KolorMiecza.valueOf( Szyfrowanie.deszyfr(jediLineT[1])),Integer.parseInt(Szyfrowanie.deszyfr( jediLineT[2])),Boolean.parseBoolean(Szyfrowanie.deszyfr(jediLineT[3])),Integer.parseInt(Szyfrowanie.deszyfr( jediLineT[4])));
            if(!(Integer.parseInt(Szyfrowanie.deszyfr( jediLineT[5]))==0)){
                Zakon z=Zakon.listaZakon.get(Integer.parseInt(Szyfrowanie.deszyfr( jediLineT[5]))-1);
                    j.setZakon(z);
            }

        }
        }
    public static void objectToFileZakon(String path) throws FileNotFoundException {

        File file = new File(path);
        PrintWriter pw = new PrintWriter(file);
        StringBuilder zakonS=new StringBuilder();
        for(Zakon z:Zakon.listaZakon){


            zakonS.append(Szyfrowanie.szyfr(z.getNazwa()));
            zakonS.append("<X>");
            zakonS.append(Szyfrowanie.szyfr(Integer.toString(z.getIdZakon())));
            zakonS.append("<X>");

            zakonS.append("\n");
        }
        pw.print(zakonS);
        pw.close();

    }
    }


