package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.defMaq;
import static analisadorlexico.maquina.state;

public class opFracao {

    public static atomo opFracao(String y) {
        switch (state) {
            case 0:
                defMaq = "Fracao";
                atm = y;
                state = 1;
                return new atomo(atm, "Fracao");
            case 1:
                
                atm = atm+y;
                state = 1;
                if(!".".equals(y) && !Character.isDigit(y.charAt(0)) ){
                    System.out.print("ERRO1 ");
                   System.exit(1);
                    return null;
                } 
                if(".".equals(y)){
                    state = 2;
                }
                return new atomo(atm, "Fracao");
            case 2:
                return new atomo(atm, "Fracao");
        }
        return null;
    }
}
