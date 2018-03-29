package analisadorlexico;

import static analisadorlexico.AnalisadorLexico.count;
import static analisadorlexico.AnalisadorLexico.line;
import static analisadorlexico.Comentario.Comentario;
import static analisadorlexico.Frase.Frase;
import static analisadorlexico.opExponencial.opExponencial;
import static analisadorlexico.Identificador.Identificador;
import static analisadorlexico.NumeroInteiro.NumeroInteiro;
import static analisadorlexico.opFracao.opFracao;
import static analisadorlexico.opLogico.opLogico;
import static analisadorlexico.opRelacional.opRelacional;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class maquina {

    public static String atm;
    public static int state = 0;
    public static String aux = "";
    public static String defMaq = "";
    public static atomo auxiliar = null;
    public static ArrayList<String> expectedValue = new ArrayList<String>();

    public static void entrada(String x) {
        // System.out.println(x);
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

        if (!expectedValue.contains(x) && (!"".equals(defMaq) && !"Identificador".equals(defMaq) && !"Inteiro".equals(defMaq) && !"Fracao".equals(defMaq) && !"Frase".equals(defMaq) && !"Comentario".equals(defMaq))) {
            System.out.println("Linha: " + line + " Atomo: " + auxiliar.name + " Lexeme: " + auxiliar.lexeme);
            reset();
            atm = x;
        } else if ("Identificador".equals(defMaq) && (!Character.isDigit(x.charAt(0)) && !Character.isLetter(x.charAt(0)))) {
            System.out.println("Linha: " + line + " Atomo: " + auxiliar.name + " Lexeme: " + auxiliar.lexeme);
            reset();
            atm = x;
        } else if (("Inteiro".equals(defMaq) || "Fracao".equals(defMaq)) && (state == 2)) {
            System.out.println("Linha: " + line + " Atomo: " + auxiliar.name + " Lexeme: " + auxiliar.lexeme);
            reset();
            atm = x;

        } else if (("Frase".equals(defMaq)) && (state == 3)) {
            System.out.println("Linha: " + line + " Atomo: " + auxiliar.name + " Lexeme: " + auxiliar.lexeme);
            reset();
            atm = x;
        } else if (("Comentario".equals(defMaq)) && (state == 4)) {
            System.out.println("Linha: " + line + " Atomo: " + auxiliar.name + " Lexeme: " + auxiliar.lexeme);
            reset();
            atm = x;
        } else if (("Exponencial".equals(defMaq)) && (state == 2) && !Character.isDigit(x.charAt(0))) {
            System.out.println("Linha: " + line + " Atomo: " + auxiliar.name + " Lexeme: " + auxiliar.lexeme);
            reset();
            atm = x;
        }
        
        //     System.out.println(x);
            System.out.println("Maquina: " + defMaq + " Estado: " + state +" atomo: " + atm); 
        //Numero_Inteiro
        if ((Character.isDigit(x.charAt(0)) || "Inteiro".equals(defMaq) || (state == 1 && Character.isDigit(x.charAt(0)))) && !"Frase".equals(defMaq) && !"Comentario".equals(defMaq)) {
            auxiliar = NumeroInteiro(x);
        } //Identificador
        else if ((Character.isLetter(x.charAt(0)) || "Identificador".equals(defMaq) || (state == 1 && Character.isDigit(x.charAt(0)))) && !"Frase".equals(defMaq) && !"Comentario".equals(defMaq)) {
            auxiliar = Identificador(x);
        } //OP_Relacional
        else if (("<".equals(x) || ">".equals(x) || "=".equals(x)) || "Relacional".equals(defMaq)) {
            auxiliar = opRelacional(x);
        } //OP_Logico
        else if (("&".equals(x) || "$".equals(x) || "!".equals(x)) || "Logico".equals(defMaq)) {
            auxiliar = opLogico(x);
        } //OP_Fracao
        else if (".".equals(x) || "Fracao".equals(defMaq)) {
            auxiliar = opFracao(x);
        } //Frase
        else if ("\\".equals(x) || "Frase".equals(defMaq)) {
            auxiliar = Frase(x);
        } //Comentario
        else if ("/".equals(x) || "Comentario".equals(defMaq)) {
            auxiliar = Comentario(x);
        } //Exponencial
        else if ("e".equals(x) || "E".equals(x) || "Comentario".equals(defMaq)) {
            auxiliar = opExponencial(x);
        }

    }

    public static void reset() {
        state = 0;
        defMaq = "";
        expectedValue.clear();
        auxiliar = null;
    }
}
