package wipro.latam.consultacep.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import wipro.latam.consultacep.feign.service.ViaCepService;
import wipro.latam.consultacep.model.DadosEndereco;
import wipro.latam.consultacep.util.TabelaFrete;

@RequestMapping("/v1")
@RestController
@Api(value = "API REST Consultar Cep")
@CrossOrigin(origins = "*")
public class ConsultaCepControllerV1 {

    //Injeta a classe ViaCepService
    @Autowired
    private ViaCepService viaCepService;

    //Injeta a classe tabelaFrete
    @Autowired
    private TabelaFrete tabelaFrete;


    @GetMapping("/consulta-endereco")
    @ApiOperation(value = "Retorna um template com os dados de um Cep")
    public ModelAndView consultacep(@RequestParam(value = "cep") String cep){

        //Chama o método de consulta passando o cep como parâmetro e popula os dados do endereço
        DadosEndereco dadosEndereco = viaCepService.consulta(cep);

        //Seta o frete de acordo com a região que pertence
        dadosEndereco.setFrete(tabelaFrete.obterFreteRegiao(dadosEndereco.getEstado()));

        //Cria a instancia de uma ModelAndView
        ModelAndView retornoTemplate = new ModelAndView();

        //Seta o ViewName para a página de redirecionamento
        retornoTemplate.setViewName("index");

        //Seta o o objeto da view com os dados do endereço do endereço
        retornoTemplate.addObject("endereco", dadosEndereco);

        //Retorna um objetco da view contendo os dados
        return retornoTemplate;
    }

}
