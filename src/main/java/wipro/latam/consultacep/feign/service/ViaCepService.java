package wipro.latam.consultacep.feign.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import wipro.latam.consultacep.feign.ViaCepInterface;
import wipro.latam.consultacep.model.DadosEndereco;

@RequiredArgsConstructor
@Service
public class ViaCepService {


    private final ViaCepInterface viacep;

    //Consulta as informações sobre o cep requerido
    public DadosEndereco consulta(String cep){
        return viacep.buscarcep(cep);
    }

}
