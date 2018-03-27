package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.defMaq;
import static analisadorlexico.maquina.expectedValue;
import static analisadorlexico.maquina.state;

public class opLogico {

    public static atomo opLogico(String y) {

        switch (state) {
            case 0:
                defMaq = "Logico";
                atm = y;
                if ("$".equals(y) || "&".equals(y) || "!".equals(y)) {
                    state = 1;
                    expectedValue.clear();
                    return new atomo(atm, "OP Logico");
                }
            default:
                System.out.println("ERRO");
                break;
        }
        return null;
    }
}
