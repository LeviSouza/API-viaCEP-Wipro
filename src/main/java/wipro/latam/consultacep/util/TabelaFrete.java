package wipro.latam.consultacep.util;

import org.springframework.stereotype.Component;

@Component
public class TabelaFrete {

    //Devolve o frete de acordo com a região

    public double obterFreteRegiao(String regiao) {
        if (regiao == null) {
            return 0;
        }
        switch (regiao.toUpperCase()) {
            case "AC":
            case "AM":
            case "AP":
            case "PA":
            case "RO":
            case "RR":
            case "TO":
                return 20.83;
            case "AL":
            case "BA":
            case "CE":
            case "MA":
            case "PB":
            case "PE":
            case "PI":
            case "RN":
            case "SE":
                return 15.98;
            case "DF":
            case "GO":
            case "MT":
            case "MS":
                return 12.50;
            case "ES":
            case "MG":
            case "RJ":
            case "SP":
                return 7.85;
            case "PR":
            case "RS":
            case "SC":
                return 17.30;
            default:
                return 0;
        }
    }
}
