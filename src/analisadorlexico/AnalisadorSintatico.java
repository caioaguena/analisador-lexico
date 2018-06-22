/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorlexico;

import static analisadorlexico.AnalisadorLexico.tokens;
import static analisadorlexico.AnalisadorLexico.consumed;
import java.util.ArrayList;

/**
 *
 * @author wch4k
 */
public class AnalisadorSintatico {

    static Boolean started = false;
    static Boolean endline = false;
    static String maqAtual = "";
    static int state = 0;
    static Boolean cAttrib = true;
    static ArrayList<String> previousMachine = new ArrayList<String>();
    public static ArrayList<String> tipos = new ArrayList<String>();
    public static ArrayList<String> operadores = new ArrayList<String>();

    public static Boolean consumir(String x) {
        consumed = x;
        //System.out.println("consumindo: " + x);
        if (started == false) {
            return bloco(x);
        }

        maqAtual = defMaq(x);
        //System.out.println("Maquina atual: "+maqAtual);
        return attET(x);
        // return expectedToken.contains(x);

    }

    public static String defMaq(String maq) {
        if (state == 0 || endline == true) {
            switch (maq) {
                case "INICIO":
                    return "outra parte";
                case "VARIAVEIS":
                    return "declaracao_de_variaveis";
                case "FUNCAO":
                    return "declaracao_de_funcao";
                case "PROCEDIMENTO":
                    return "declaracao_de_procedimento";
                case "Comando":
                    return "Comando";
                case "cAtribuicao":
                    return "cAtribuicao";
                default:
                    return maqAtual;
            }
        }
        return maqAtual;
    }

    public static Boolean attET(String x) {
        // System.out.print("chego aqui ta certo");
        switch (maqAtual) {
            case "declaracao_de_variaveis":
                return ddv();
            case "declaracao_de_funcao":
                return ddf();
            //case "declaracao_de_procedimento":ddp(x);
            case "parametros_formais":
                return pf();
            case "Comando":
                return C();
            case "cAtribuicao":
                return cAtribuicao();
            default:
                break;
        }
        return false;
    }

    public static Boolean bloco(String y) {
        if ("ALGORITMO".equals(y) && "Identificador".equals(AnalisadorLexico.tokens.get(0))) {
            AnalisadorLexico.tokens.remove(0);
            started = true;
            return true;
        } else {
            System.out.print("Ja comecou errado o programa");
            return false;
        }
    }

    public static Boolean cAtribuicao(){
        if(cAttrib){
          if(operadores.contains(consumed)){
            System.out.print(consumed+" ");
            return true;
          }
        }else{
           if(tipos.contains(consumed)){
            System.out.print(consumed+" ");
            return true;
          }
           if(";".equals(consumed)){
               System.out.println("cabo");
           }
        }
        return true;
    }
    
    //Comando
    public static Boolean C(){
 
         //atribuição
        if(("Identificador".equals(consumed) && "Atribuição".equals(tokens.get(0)))){        
            tokens.remove(0);
            maqAtual = "cAtribuicao";
            return true;
        }
        
        //condicional
        if("SE".equals(consumed)){
           
        }
        
        return true;
    }
    
    //declaracao_de_funcao
    public static Boolean ddf() {
        if (state == 0) {
            if ("Identificador".equals(tokens.get(0)) && "Abre_Par".equals(tokens.get(1))) {
                System.out.println("Funcao " + tokens.get(0));
                tokens.remove(0);
                previousMachine.add("declaracao_de_funcao");
                state = 1;
                maqAtual = "parametros_formais";
                return true;
            } else {
                System.out.println("Esperava um Identificador e um ( - Foi encontrado " + tokens.get(0));
                return false;
            }
        }

        if (state == 1) {
            //System.out.println("estado 1 chegou");
            if("Dois_Pontos".equals(tokens.get(0)) && tipos.contains(tokens.get(1)) && "VARIAVEIS".equals(tokens.get(2)) ){
                tokens.remove(0);
                tokens.remove(0);
                 previousMachine.add("declaracao_de_funcao");
                state = 2;
                maqAtual = "declaracao_de_variaveis";
                return true;
            }else {
                System.out.println("Esperava Dois_Pontos e um tipo_simples, foi encontrado " + tokens.get(0));
                return false;
            }
            
        }
        
        if(state == 2){
            if("INICIO".equals(consumed)){
                previousMachine.add("declaracao_de_funcao");
                state = 3;
                maqAtual = "Comando";
           //     tokens.offerFirst("INICIO");
            }
             return true;
        }

        return true;
    }

    //parametros_formais
    public static Boolean pf() {
        if (("REF".equals(tokens.get(0)) && "Identificador".equals(tokens.get(1)) && tipos.contains(tokens.get(2)))) {
            System.out.println("pf: "+tokens.get(0)+" "+tokens.get(1)+" "+tokens.get(2));
            for (int i = 0; i < 3; i++) {
                tokens.remove(0);
            }
            if ("Fecha_Par".equals(tokens.get(0))) {
                maqAtual = previousMachine.get(previousMachine.size() - 1);
                previousMachine.remove(previousMachine.size() - 1);
                return true;
            }
            if (!"Ponto_Virgula".equals(tokens.get(0))) {
                System.out.println("Erro na passagem de parametros formais - Faltando um ponto e virgula");
                return false;
            }
            return true;
        } else if (("Identificador".equals(tokens.get(0)) && tipos.contains(tokens.get(1)))) {
            System.out.println("pf: "+tokens.get(0)+" "+tokens.get(1));
            for (int i = 0; i < 2; i++) {
                tokens.remove(0);
            }
            if ("Fecha_Par".equals(tokens.get(0))) {
                maqAtual = previousMachine.get(previousMachine.size() - 1);
                previousMachine.remove(previousMachine.size() - 1);
                return true;
            }
            if (!"Ponto_Virgula".equals(tokens.get(0))) {
                System.out.println("Erro na passagem de parametros formais - Faltando um ponto e virgula");
                return false;
            }
            return true;
        } else {
            System.out.println("Erro na passagem de parametros formais");
            return false;
        }

    }

    //declaracao_de_variaveis
    public static Boolean ddv() {
        if ("Identificador".equals(tokens.get(0))) {
            System.out.print(tokens.get(0) + " ");
            tokens.remove(0);
            if (tipos.contains(tokens.get(0))) {
                System.out.print(tokens.get(0) + " \n");

                if ("VETOR".equals(tokens.get(0))) {
                    tokens.remove(0);
                    if ("Abre_chave".equals(tokens.get(0)) && tipos.contains(tokens.get(1))
                            && "Ponto".equals(tokens.get(2)) && "Ponto".equals(tokens.get(3)) && tipos.contains(tokens.get(4))
                            && "Fecha_chave".equals(tokens.get(5)) && "DE".equals(tokens.get(6)) && tipos.contains(tokens.get(7))
                            && "Ponto_Virgula".equals(tokens.get(8))) {
                        for (int i = 0; i < 9; i++) {
                            tokens.remove(0);
                        }
                        if (!"Identificador".equals(tokens.get(0))) {
                            if(!previousMachine.isEmpty()){
                                maqAtual = previousMachine.get(previousMachine.size() - 1);
                            }else{
                                maqAtual = "";
                            }                   
                        } else {
                            tokens.offerFirst("Identificador");
                        }
                        return true;
                    } else {
                        System.out.println("Erro na sintaxe do vetor");
                        return false;
                    }

                } else {
                    tokens.remove(0);
                    if ("Ponto_Virgula".equals(tokens.get(0))) {
                        tokens.remove(0);
                        if (!"Identificador".equals(tokens.get(0))) {
                             if(!previousMachine.isEmpty()){
                                maqAtual = previousMachine.get(previousMachine.size() - 1);
                            }else{
                                maqAtual = "";
                            } 
                        } else {
                            tokens.offerFirst("Identificador");
                        }
                        return true;
                    } else {
                        System.out.println("Esperava um Ponto_virgula - Foi encontrado " + tokens.get(0));
                        return false;
                    }
                }

            } else {
                System.out.println("Esperava um Tipo - Foi encontrado " + tokens.get(0));
            }
        } else {
            System.out.println("Esperava IDENTIFICADOR - Foi encontrado " + tokens.get(0));
            return false;
        }
        return true;
    }

    public static void tiposADD() {
        tipos.add("INTEIRO");
        tipos.add("REAL");
        tipos.add("FRASE");
        tipos.add("VETOR");
        tipos.add("Numero_Inteiro");
    }

    public static void operadoresADD(){
        operadores.add("Adicao");
        operadores.add("Subtracao");
        operadores.add("Multiplicacao");
    }
}
