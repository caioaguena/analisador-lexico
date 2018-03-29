package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.defMaq;
import static analisadorlexico.maquina.state;

public class Comentario {

    public static atomo Comentario(String y) {
        switch (state) {
            case 0:
                defMaq = "Comentario";
                atm = y;
                state = 1;
                return new atomo(atm, "Comentario");
            case 1:
                atm = atm + y;
                if (!"*".equals(y)) {
                    System.out.print("ERROx");
                    System.exit(1);
                    return new atomo(atm, "Comentario");
                } else {
                    state = 2;
                    return new atomo(atm, "Comentario");
                }
            case 2:
                atm = atm + y;
                if ("*".equals(y)) {
                    state = 3;
                    return new atomo(atm, "Comentario");
                }
                return new atomo(atm, "Comentario");
            case 3:
                atm = atm + y;
                if (!"/".equals(y)) {
                    System.out.print("ERROy");
                    System.exit(1);
                    return new atomo(atm, "Comentario");
                } else {
                    state = 4;
                }
            case 4:
                 return new atomo(atm, "Comentario");
        }
        return null;
    }
}
