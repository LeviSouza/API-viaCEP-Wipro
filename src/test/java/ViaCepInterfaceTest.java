import org.springframework.boot.test.mock.mockito.MockBean;

import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wipro.latam.consultacep.feign.ViaCepInterface;
import wipro.latam.consultacep.model.DadosEndereco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@AutoConfigureMockMvc
class ViaCepInterfaceTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ViaCepInterface viaCepInterface;


    @Test
    void testBuscarEnderecoPorCep() throws Exception {
        // Configura o comportamento esperado do Feign Client
        //MockMvc mockMvc = MockMvcBuilders.standaloneSetup(viaCepInterface).build();
        String cep = "01001000";
        DadosEndereco dadosEndereco = new DadosEndereco();
        dadosEndereco.setCep("01001-000");
        dadosEndereco.setRua("Praça da Sé");
        dadosEndereco.setBairro("Sé");
        dadosEndereco.setCidade("São Paulo");
        dadosEndereco.setEstado("SP");
        //when(this.viaCepInterface.buscarcep(cep)).thenReturn(dadosEndereco);

        // Faz a requisição usando o MockMvc e verifica o resultado
        mockMvc.perform(get("/endereco/{cep}", cep))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("01001-000"))
                .andExpect(jsonPath("$.logradouro").value("Praça da Sé"))
                .andExpect(jsonPath("$.bairro").value("Sé"))
                .andExpect(jsonPath("$.localidade").value("São Paulo"))
                .andExpect(jsonPath("$.uf").value("SP"));
    }
}

//@ExtendWith(MockitoExtension.class)
//public class ViaCepInterfaceTest {
//
//    @Mock
//    private ViaCepInterface viaCepInterface;
//
//    private DadosEndereco dadosEndereco;
//
//    @BeforeEach
//    public void setUp() {
//        dadosEndereco = new DadosEndereco();
//        dadosEndereco.setCep("01001-000");
//        dadosEndereco.setRua("Praça da Sé");
//        dadosEndereco.setComplemento("lado ímpar");
//        dadosEndereco.setBairro("Sé");
//        dadosEndereco.setCidade("São Paulo");
//        dadosEndereco.setEstado("SP");
//        dadosEndereco.setFrete("3550308");
//    }
//
//    @Test
//    public void testBuscarCep() {
//        String cep = "08970000";
//        //when(this.viaCepInterface.buscarcep(cep)).thenReturn(dadosEndereco);
//
//        DadosEndereco endereco = new DadosEndereco();
//        endereco = viaCepInterface.buscarcep(cep);
//
//        assertEquals(dadosEndereco.getCep(), endereco.getCep());
//        assertEquals(dadosEndereco.getRua(), endereco.getRua());
//        assertEquals(dadosEndereco.getComplemento(), endereco.getComplemento());
//        assertEquals(dadosEndereco.getBairro(), endereco.getBairro());
//        assertEquals(dadosEndereco.getCidade(), endereco.getCidade());
//        assertEquals(dadosEndereco.getEstado(), endereco.getEstado());
//        assertEquals(dadosEndereco.getFrete(), endereco.getFrete());
//
//    }
//}

//    @Test
//    public void testBuscarCepInvalido() {
//        String cep = "00000-000";
//        when(viaCepInterface.buscarcep(anyString())).thenThrow(FeignException.class);
//
//        DadosEndereco endereco = viaCepInterface.buscarcep(cep);
//
//        assertEquals(null, endereco);
//    }
//}