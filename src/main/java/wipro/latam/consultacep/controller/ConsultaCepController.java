package wipro.latam.consultacep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wipro.latam.consultacep.feign.service.ViaCepService;
import wipro.latam.consultacep.model.Cep;
import wipro.latam.consultacep.model.DadosEndereco;
import wipro.latam.consultacep.util.TabelaFrete;

@RequestMapping("/v1")
@RestController
public class ConsultaCepController {

    @Autowired
    private ViaCepService viaCepService;
    @Autowired
    private TabelaFrete tabelaFrete;



    @GetMapping("/consulta-endereco")
    public ResponseEntity consultacep(@RequestParam(value = "cep") String cep){

    DadosEndereco dadosEndereco = viaCepService.consulta(cep);
    dadosEndereco.setFrete(tabelaFrete.obterFreteRegiao(dadosEndereco.getEstado()));

    return   ResponseEntity.ok(dadosEndereco);

//        DadosEndereco dadosEndereco;
//     try{
//        dadosEndereco  = viaCepService.consulta(cep);
//        dadosEndereco.setFrete(tabelaFrete.obterFreteRegiao(dadosEndereco.getEstado()));
//
//         return   ResponseEntity.ok(dadosEndereco);
//     }catch (Exception e){
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CEP Inexistente");
//     }

    }
}
