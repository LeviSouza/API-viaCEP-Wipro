package wipro.latam.consultacep.feign.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wipro.latam.consultacep.feign.ViaCepInterface;
import wipro.latam.consultacep.model.Cep;
import wipro.latam.consultacep.model.DadosEndereco;

@RequiredArgsConstructor
@Service
public class ViaCepService {

    private final ViaCepInterface viacep;

    public DadosEndereco consulta(Cep cep){
        return viacep.buscarcep(cep.getCep());
    }
}
