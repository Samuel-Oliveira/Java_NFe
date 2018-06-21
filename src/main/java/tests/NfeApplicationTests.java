package tests;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.impl.ErrosValidacao;
import br.com.samuelweb.nfe.util.validators.impl.NfeValidator;
import br.com.samuelweb.nfe.util.validators.impl.ValidarMunicipio;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class NfeApplicationTests {

    public void contextLoads() {
    }

    //@Test
    public void testarObjetoIde() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Iniciando teste Objeto IDE");
        System.out.println("--------------------------------------------------------------------------");
        TNFe nfe = new TNFe();
        TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.
        nfe.setInfNFe(infNFe);
        Ide ide = new Ide();
        ide.setCuf(12);
        ide.setNatOp("");
        NfeValidator validator = new NfeValidator();
        try {
            if (!validator.validarObject(ide)) {
                List<ErrosValidacao> errosValidacaos = validator.getErrosList();
                for (ErrosValidacao errosValidacao : errosValidacaos) {
                    System.out.println(errosValidacao.toString());
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Final do teste Objeto IDE");
        System.out.println("--------------------------------------------------------------------------");
    }


    //@Test
    public void testarCodigoMunicipio() {
        ValidarMunicipio validarMunicipio = new ValidarMunicipio();
        RetornoValidar retornoValidar;

        retornoValidar = validarMunicipio.validar(9999999);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(5203962);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(9999999);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(320000);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(4123456);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(5200050);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(4118501);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());
    }


}
