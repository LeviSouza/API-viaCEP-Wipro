package wipro.latam.consultacep.exception;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import wipro.latam.consultacep.feign.service.ViaCepService;


@RestControllerAdvice
@ControllerAdvice
public class ErrorsAdvice {

    @Autowired
    private ViaCepService viaCepService;

    //Captura os erros provenientes do FeignClient, como o erro 400 por exemplo
    @ExceptionHandler(FeignException.class)
    public ModelAndView handleFeignException(FeignException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.status());
        //Seta uma mensagem
        String mensagem = "CEP Inexistente!";

        //Seta o ViewName para a página de redirecionamento
        ModelAndView retornoTemplate = new ModelAndView();

        //Seta o ViewName para a página de redirecionamento
        retornoTemplate.setViewName("index");

        //Seta o o objeto da view com a mensagem
        retornoTemplate.addObject("mensagemErro",mensagem);

        //Retorna um objetco da view contendo os dados
        return retornoTemplate;
    }


}
