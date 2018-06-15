package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.defMaq;
import static analisadorlexico.maquina.state;
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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class maquina {

    public static String atm="";
    public static int state = 0;
    public static String aux = "";
    public static String atriboff = "";
    public static String defMaq = "";
    public static atomo auxiliar = null;
    public static ArrayList<String> expectedValue = new ArrayList<String>();
    public static ArrayList<String> pReservadas = new ArrayList<String>();
    public static Map<String,String> atomoSemAtributo = new HashMap<String,String>();
    
    public static void entrada(String x) {
        // System.out.println(x);
        //System.out.println("Maquina: " + defMaq + " Estado: " + state +" atomo: " + atm + " X:"+x); 
        //só pra saber q linha está
        lineCounter(x);
        
        if(pReservadas.contains(atm.toUpperCase())){
        System.out.println("Linha: " + line + " Atomo: " + atm.toUpperCase() + " Lexeme: Palavra reservada");
        state = 0;
        defMaq = "";
        expectedValue.clear();
        auxiliar = null;
        atm = "";
        } 
        
        
        if("<".equals(atriboff)){
        atriboff = atriboff + x;
        }
        else{
            atriboff = x;
        }
        //System.out.println(" "+atriboff+" maq:"+defMaq);
    
        
        if (!expectedValue.contains(x) && (!"".equals(defMaq) && !"Identificador".equals(defMaq) && !"Inteiro".equals(defMaq) && !"Fracao".equals(defMaq) && !"Frase".equals(defMaq) && !"Exponencial".equals(defMaq) && !"Comentario".equals(defMaq))) {
            if("<-".equals(atm+x)){
                auxiliar = new atomo("atribuição" ,"Atomo sem atributo");
            }
            reset(x);           
        } else if (("Identificador".equals(defMaq)) && (!Character.isDigit(x.charAt(0)) && !Character.isLetter(x.charAt(0))) && (!"Exponencial".equals(defMaq))) {
            if (("+".equals(x) || "-".equals(x)) && (auxiliar.name.charAt(0) == 'e' || auxiliar.name.charAt(0) == 'E')) {
                defMaq = "Exponencial";
                state = 2;
                auxiliar = new atomo(atm, "Exponencial");
            } else {
                reset(x);
            }
        } else if (("Inteiro".equals(defMaq)) && (state == 1 && !Character.isDigit(x.charAt(0)))) {
            if (x.equals(".")) {
                auxiliar.lexeme = "Fracao";
                auxiliar.name = auxiliar.name + x;
            }
            reset(x);
        } else if (("Frase".equals(defMaq)) && (state == 3)) {
            reset(x);
        } else if (("Comentario".equals(defMaq)) && (state == 4)) {
            reset(x);
        } else if (("Exponencial".equals(defMaq)) && (state == 2) && !Character.isDigit(x.charAt(0))) {
            reset(x);
        }
       
       //System.out.println("Maquina: " + defMaq + " Estado: " + state +" atomo: " + atm + " X:"+x); 
         if((atomoSemAtributo.get(atriboff) != null)  &&  "".equals(defMaq)){    
        System.out.println("Linha: " + line + " Atomo: " + atomoSemAtributo.get(atriboff) + " Lexeme: Atomo sem atributo");
        state = 0;
        defMaq = "";
        expectedValue.clear();
        auxiliar = null;
        atm = "";
        }
       
       
        //Numero_Inteiro
        if ((Character.isDigit(x.charAt(0)) || "Inteiro".equals(defMaq) || (state == 1 && Character.isDigit(x.charAt(0)))) && !"Frase".equals(defMaq) && !"Comentario".equals(defMaq) && !"Exponencial".equals(defMaq) && !"Identificador".equals(defMaq)) {
            auxiliar = NumeroInteiro(x);
        } //Identificador
        else if ((Character.isLetter(x.charAt(0)) || ("Identificador".equals(defMaq) && !"Exponencial".equals(defMaq)) || (state == 1 && Character.isDigit(x.charAt(0)))) && !"Frase".equals(defMaq) && !"Comentario".equals(defMaq)) {
            auxiliar = Identificador(x);
        } //OP_Relacional
        else if (("<".equals(x) || ">".equals(x) || "=".equals(x)) || "Relacional".equals(defMaq)) {
            auxiliar = opRelacional(x);
        } //OP_Logico
        else if (("&".equals(x) || "$".equals(x) || "!".equals(x)) || "Logico".equals(defMaq)) {
            auxiliar = opLogico(x);
        } //OP_Fracao
        /*  else if (".".equals(x) || "Fracao".equals(defMaq)) {
            auxiliar = opFracao(x);
        } */ //Frase
        else if ("\\".equals(x) || "Frase".equals(defMaq)) {
            auxiliar = Frase(x);
        } //Comentario
        else if ("/".equals(x) || "Comentario".equals(defMaq)) {
            auxiliar = Comentario(x);
        } //Exponencial
        else if ("e".equals(x) || "E".equals(x) || "Exponencial".equals(defMaq)) {
            System.out.println("Maquina: " + defMaq + " Estado: " + state +" atomo: " + atm + " X:"+x); 
            auxiliar = opExponencial(x);
        }
        
    }

    public static void reset(String x) {
        System.out.println("Linha: " + line + " Atomo: " + auxiliar.name + " Lexeme: " + auxiliar.lexeme);
        state = 0;
        defMaq = "";
        expectedValue.clear();
        auxiliar = null;
        atm = x;
        atriboff = "";
    }

    public static void listaReservada() {
        pReservadas.add("ALGORITMO");
        pReservadas.add("ATE");
        pReservadas.add("CADEIA");
        pReservadas.add("CARACTER");
        pReservadas.add("ENQUANTO");
        pReservadas.add("ENTAO");
        pReservadas.add("FACA");
        pReservadas.add("FIM");
        pReservadas.add("FUNCAO");
        pReservadas.add("INICIO");
        pReservadas.add("INTEIRO");
        pReservadas.add("PARA");
        pReservadas.add("PASSO");
        pReservadas.add("PROCEDIMENTO");
        pReservadas.add("REAL");
        pReservadas.add("RETORNE");
        pReservadas.add("SE");
        pReservadas.add("SENAO");
        pReservadas.add("VARIAVEIS");
    }

    public static void lineCounter(String x) {
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
    }
    
    public static void initAtomoSemAtributo() {
        atomoSemAtributo.put("<-", "Atribuição");
        atomoSemAtributo.put(".", "Ponto");
        atomoSemAtributo.put("(", "Abre_Par");
        atomoSemAtributo.put(")", "Fecha_Par");
        atomoSemAtributo.put(";", "Ponto_Virgula");
        atomoSemAtributo.put(",", "Virgula");
        atomoSemAtributo.put("-", "Subtrção");
        atomoSemAtributo.put("+", "Adicao");
        atomoSemAtributo.put("*", "Multiplicacao");
        atomoSemAtributo.put("/", "Divisao");
        atomoSemAtributo.put("%", "Resto");
    }
}
