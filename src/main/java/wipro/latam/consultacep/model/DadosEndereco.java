package wipro.latam.consultacep.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class DadosEndereco {

    //Classe contendo os dados do endereço, assim como o frete e o retorno de erro

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("logradouro")
    private String rua;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("localidade")
    private String cidade;

    @JsonProperty("uf")
    private String estado;

    private double frete;

    @JsonProperty("erro")
    private boolean erro = false;
}
