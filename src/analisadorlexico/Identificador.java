package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.defMaq;
import static analisadorlexico.maquina.state;

public class Identificador {

    public static atomo Identificador(String y) {
  
        switch (state) {
            case 0:
                defMaq = "Identificador";
                atm = y;
                state = 1;
                return new atomo(atm, "Identificador");
            case 1:
                atm = atm + y;
                state = 1;
                return new atomo(atm, "Identificador");
        }
        return null;
    }
}
