package analisadorlexico;

import static analisadorlexico.AnalisadorLexico.count;
import static analisadorlexico.AnalisadorLexico.line;
import static analisadorlexico.opRelacional.opRelacional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class maquina {

    public static String atm;
    public static int state = 0;
    public static String aux = "";
    public static String defMaq = "";

    public static void entrada(String x) {
        //só pra saber q linha está
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(x);
        if (matcher.find()) {
            count += 1;
            if (count == 2) {
                line += 1;
            }
        } else {
            count = 0;
        }
        //----------

        //Numero_Inteiro
        if (Character.isDigit(x.charAt(0)) || "Inteiro".equals(defMaq)) {

        } //Identificador
        else if (Character.isLetter(x.charAt(0)) || "Identificador".equals(defMaq)) {

        } //OP_Relacional
        else if (("<".equals(x) || ">".equals(x) || "=".equals(x)) || "Relacional".equals(defMaq)) {
            opRelacional(x);
        } //OP_Logico
        else if (("&".equals(x) || "$".equals(x) || "!".equals(x)) || "Logico".equals(defMaq)) {
           
        } //OP_Fracao
        else if (".".equals(x) || "Fracao".equals(defMaq)) {

        } //Frase
        else if ("\\".equals(x) || "Frase".equals(defMaq)) {

        } //Comentario
        else if ("/".equals(x) || "Comentario".equals(defMaq)) {

        }

    }

}
