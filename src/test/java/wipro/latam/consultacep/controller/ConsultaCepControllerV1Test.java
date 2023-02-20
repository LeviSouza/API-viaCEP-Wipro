package wipro.latam.consultacep.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import wipro.latam.consultacep.feign.service.ViaCepService;
import wipro.latam.consultacep.model.DadosEndereco;
import wipro.latam.consultacep.util.TabelaFrete;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class ConsultaCepControllerV1Test {


    //Faz o mock do ViaCepService
    @Mock
    private ViaCepService viaCepService;

    //Faz o mock do TabelaFrete
    @Mock
    private TabelaFrete tabelaFrete;

    //Injeta as Mocks na controller
    @InjectMocks
    private ConsultaCepControllerV1 controller;


    @BeforeEach
    void setUp() {

    }

    @Test
    void testarConsultaDeCep() throws Exception {

        //cria uma builder modck da controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        //Seta dados para teste
        DadosEndereco dadosEndereco = new DadosEndereco();
        String cep = "08970000";
        dadosEndereco.setEstado("RJ");

        //Testa os metodos das classes
        when(this.viaCepService.consulta(cep)).thenReturn(dadosEndereco);
        when(this.tabelaFrete.obterFreteRegiao("RJ")).thenReturn(7.85);

        //Faz uma requisição com protocolo GET e confirma se o retorno esta correto
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/consulta-endereco")
                        .param("cep", cep))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andReturn();

        //Faz os testes com o retorno obtido
        ModelAndView modelAndView = result.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "index");
        ModelAndViewAssert.assertModelAttributeAvailable(modelAndView, "endereco");
        Assertions.assertEquals(7.85, ((DadosEndereco) result.getModelAndView().getModel().get("endereco")).getFrete());
    }
}

