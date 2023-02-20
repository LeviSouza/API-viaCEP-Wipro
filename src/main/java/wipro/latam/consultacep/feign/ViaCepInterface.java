package wipro.latam.consultacep.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wipro.latam.consultacep.model.DadosEndereco;

@FunctionalInterface
@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepInterface{

    //Recebe um Cep e faz a consulta na API viacep utilizando o FeignClient
    @GetMapping("{cep}/json")
    DadosEndereco buscarcep(@PathVariable("cep") String cep);

}
