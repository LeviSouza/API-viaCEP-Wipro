package wipro.latam.consultacep.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wipro.latam.consultacep.model.DadosEndereco;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepInterface {

    @GetMapping("{cep}/json")
    DadosEndereco buscarcep(@PathVariable("cep") String cep);

}
