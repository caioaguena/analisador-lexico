/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorlexico;

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
    static ArrayList<String> expectedToken = new ArrayList<String>();
    
    public Boolean consumir(String x){
        
        if(started = false){
            return bloco(x);
        }
        
        maqAtual = defMaq(x);
        attET(x);
        if(expectedToken.contains(x)){
          
            return true;
        }else{
            return false;
        }
        
    }
    
    private String defMaq(String maq){
        if (state == 0 || endline == true){
            switch(maq){
                case "inicio": return "outra parte"; 
                case "variaveis": state = 1;return "declaracao_de_variaveis";
                case "funcao":return "declaracao_de_funcao";
                case "procedimento":return "declaracao_de_procedimento";
                default: return maqAtual;
            }
        }
        return maqAtual;
    }
    
    private void attET(String x){
        switch(maqAtual){
            case "declaracao_de_variaveis":ddv(x);
            case "declaracao_de_funcao":ddf(x);
            case "declaracao_de_procedimento":ddp(x);
            case "parametros_formais": pf(x);
            case "outra parte":
            default:break;
        }
    }
    
    private Boolean bloco(String y){
        if("algoritmo".equals(y) && state == 0){
            state = 1;
            return true;
        }
        else if("IDENTIFICADOR".equals(y) && state == 1){
            started = true;
            state = 0;
            expectedToken.add("inicio");
            expectedToken.add("variaveis");
            expectedToken.add("funcao");
            expectedToken.add("procedimento");
            return true;
        }
        else{return false;}
    }
    
    //declaração de variavel
    private void ddv(String x){
        expectedToken.clear();
        switch(state){
            case 1: state = 2;
            expectedToken.add("IDENTIFICADOR");
            case 2:
                if(x.equals("VETOR")){
                    state = 5;
                    expectedToken.add("VETOR");
                }else{
             state = 3;
            expectedToken.add("NUMERO_REAL");
            expectedToken.add("NUMERO_INTEIRO");
            expectedToken.add("LETRA");
                }
            case 3: state = 4;
            endline = true;
             expectedToken.add(";");
            case 5: state = 6;
            expectedToken.add("[");
            case 6: state = 7;
            expectedToken.add("NUMERO_REAL");
            expectedToken.add("NUMERO_INTEIRO");
            case 7: state = 8;
            expectedToken.add("..");
            case 8: state = 9;
            expectedToken.add("NUMERO_REAL");
            expectedToken.add("NUMERO_INTEIRO");
            case 9: state = 10;
            expectedToken.add("]");
            case 10: state = 11;
            expectedToken.add("de");
            case 11: state = 3;
            expectedToken.add("INTEIRO");
            expectedToken.add("REAL");
            expectedToken.add("CARACTERE");
            expectedToken.add("LOGICO");
            case 4:state = 2;
            expectedToken.add("IDENTIFICADOR");
            default: System.out.print("erro ddv");
        }
    }
    
    //declaração de função
    private void ddf(String x){
        expectedToken.clear();
        switch(state){
            case 0: state = 1;    
                expectedToken.add("funcao");
            case 1: state = 2;
                 expectedToken.add("IDENTIFICADOR");
            case 2:
                if(x.equals("(")){
                    state = 0;
                    maqAtual = "parametros_formais";
                }
                 expectedToken.add("(");
            case 3:
            default:
        }
    }
    
    //declaração de procedimento
    private void ddp(String x){
        
    }
    
    //parametros formais
    private void pf(String x){
        
    }
}
