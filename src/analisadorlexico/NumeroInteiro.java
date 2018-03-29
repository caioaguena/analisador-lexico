package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.defMaq;
import static analisadorlexico.maquina.state;

public class NumeroInteiro {
    public static atomo NumeroInteiro(String y){
           switch (state) {
            case 0:
                defMaq = "Inteiro";
                atm = y;
                state = 1;
                return new atomo(atm, "Numero_Inteiro");
            case 1:
                atm = atm+y;
                state = 1;
                if(!"+".equals(y) && !Character.isDigit(y.charAt(0)) ){
                   System.out.print("ERRO ");
                   System.exit(1);
                    return null;
                }    else if("+".equals(y)){
                    state =2;
                }
                return new atomo(atm, "Numero_Inteiro");
            case 2:
                return new atomo(atm, "Numero_Inteiro");
        }
        return null;
    }
}
