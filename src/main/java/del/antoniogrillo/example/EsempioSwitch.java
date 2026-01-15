package del.antoniogrillo.example;

import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import dev.antoniogrillo.primoprogettospring.entity.Utente;

public class EsempioSwitch {

    public static void main(String[] args) {
        Object valore=new Utente();

        switch (valore){
            case Utente u-> System.out.println(u.getCognome());
            case Automobile a-> System.out.println(a.getModello());
            case String s-> System.out.println(s);
            case Integer i-> System.out.println(i);
            default -> System.out.println("errore");
        }

        int i=switch (valore){
            case Utente u -> u.getAnni();
            case Automobile a -> a.getColore().codePointAt(0);
            default -> 0;
        };

        int val=10;

        int result=switch (val){
            case 1 -> 10;
            case 2 -> 20;
            default -> 0;
        };

        if(valore instanceof Utente u){
            System.out.println(u.getCognome());
        }
    }
}
