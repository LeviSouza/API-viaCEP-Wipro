package wipro.latam.consultacep.features;

import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.ModelAndView;
import wipro.latam.consultacep.ConsultaCepApplication;
import wipro.latam.consultacep.controller.ConsultaCepControllerV1;
import wipro.latam.consultacep.feign.service.ViaCepService;
import wipro.latam.consultacep.model.DadosEndereco;
import wipro.latam.consultacep.util.TabelaFrete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@CucumberContextConfiguration
//@ContextConfiguration(classes = {ConsultaCepControllerV1.class})
@SpringBootTest(classes = ConsultaCepApplication.class)
public class StepsConsultarCep {
    @Autowired
    private ConsultaCepControllerV1 consultaCepController;

    private String cep;
    private String cepErrado = "25065014";
    private String ceplongo = "08970-0000";
    private ModelAndView resultCerto;
    private ModelAndView resultErrado;

    private ModelAndView resultlongo;


    @Dado("que o usuário informou um CEP válido {string}")
    public void que_o_usuário_informou_um_cep_válido(String cep) throws Throwable {
        this.cep = cep;
    }

    @Quando("o usuário solicita a consulta do endereço")
    public void o_usuário_solicita_a_consulta_do_endereço() throws Throwable {
        resultCerto = consultaCepController.consultacep(cep);
    }

    @Entao("o sistema deve retornar os dados do endereço com o frete da região")
    public void o_sistema_deve_retornar_os_dados_do_endereço_com_o_frete_da_região() throws Throwable {
        Assertions.assertNotNull(resultCerto);
        Assertions.assertEquals("index", resultCerto.getViewName());

        DadosEndereco dadosEndereco = (DadosEndereco) resultCerto.getModel().get("endereco");
        Assertions.assertNotNull(dadosEndereco);
        Assertions.assertNotNull(dadosEndereco.getRua());
        Assertions.assertNotNull(dadosEndereco.getCidade());
        Assertions.assertNotNull(dadosEndereco.getEstado());
        Assertions.assertNotNull(dadosEndereco.getFrete());
    }


    @Entao("o sistema deve exibir uma mensagem de erro informando que o CEP é inválido")
    public void o_sistema_deve_exibir_uma_mensagem_de_erro_informando_que_o_cep_é_inválido() throws Throwable {

        resultErrado = consultaCepController.consultacep(cepErrado);
        Assertions.assertEquals(true, ((DadosEndereco) resultErrado.getModel().get("endereco")).isErro());
    }


}
