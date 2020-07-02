package sample;

public class Szyfrowanie {
    public static String szyfr(String s){
        String newS="";
        for(int i=0;i<s.length();i++ ){
            char c=s.charAt(i);
            int ascii= ((int) c)+i+s.length();
            newS+=Character.toString((char) ascii);
        }
        return newS;
    }
    public static String deszyfr(String s){
        String newS="";
        for(int i=0;i<s.length();i++ ){
            char c=s.charAt(i);
            int ascii= ((int) c)-i-s.length();
            newS+=Character.toString((char) ascii);
        }
        return newS;
    }
}
