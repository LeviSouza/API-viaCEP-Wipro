package wipro.latam.consultacep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wipro.latam.consultacep.feign.service.ViaCepService;
import wipro.latam.consultacep.model.Cep;
import wipro.latam.consultacep.model.DadosEndereco;
import wipro.latam.consultacep.util.TabelaFrete;


@RequestMapping("/v1")
@RestController
public class ConsultaCepController {

    @Autowired
    private ViaCepService viaCepService;


    @GetMapping("/consulta-endereco")
    public ResponseEntity consultacep(@RequestBody Cep cep){

    DadosEndereco dadosEndereco = viaCepService.consulta(cep);
    dadosEndereco.setFrete();

     return   ResponseEntity.ok(dadosEndereco);
    }
}
