package br.com.swconsultoria.nfe.util;

import javax.xml.bind.JAXBElement;
import java.math.BigDecimal;
import java.util.List;

/**
 * Classe utilitária para extração dos valores de impostos de uma lista de elementos JAXB
 * proveniente do bloco <Imposto> do XML da NFe.
 * <p>
 * Cada método percorre a lista de impostos e busca o valor correspondente de acordo com cada
 * tipo de imposto, retornando seu valor como BigDecimal ou BigDecimal.ZERO caso não encontrado.
 */
public class XmlImpostoUtil {

    private XmlImpostoUtil(){}

    /**
     * Recupera o valor do PIS (vPIS) do produto.
     * <p>
     * Percorre a lista de elementos de imposto e verifica os diferentes tipos de apuração do PIS.
     * Retorna o primeiro valor encontrado, ou BigDecimal.ZERO caso nenhum seja localizado.
     *
     * @param impostos Lista de elementos JAXB do bloco de impostos do produto.
     * @return Valor do PIS (vPIS) ou BigDecimal.ZERO se não houver.
     */
    public static BigDecimal getVPIS(List<JAXBElement<?>> impostos) {
        for (JAXBElement<?> elem : impostos) {
            // Verifica se o elemento é do tipo PIS
            if (elem.getValue() instanceof br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS) {
                br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS pis =
                        (br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS) elem.getValue();
                // Busca vPIS em cada modalidade possível no XML
                if (pis.getPISAliq() != null && pis.getPISAliq().getVPIS() != null)
                    return new BigDecimal(pis.getPISAliq().getVPIS());
                if (pis.getPISQtde() != null && pis.getPISQtde().getVPIS() != null)
                    return new BigDecimal(pis.getPISQtde().getVPIS());
                if (pis.getPISOutr() != null && pis.getPISOutr().getVPIS() != null)
                    return new BigDecimal(pis.getPISOutr().getVPIS());
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * Recupera o valor do COFINS (vCOFINS) do produto.
     * <p>
     * Percorre a lista de elementos de imposto e verifica os diferentes tipos de apuração do COFINS.
     * Retorna o primeiro valor encontrado, ou BigDecimal.ZERO caso nenhum seja localizado.
     *
     * @param impostos Lista de elementos JAXB do bloco de impostos do produto.
     * @return Valor do COFINS (vCOFINS) ou BigDecimal.ZERO se não houver.
     */
    public static BigDecimal getVCOFINS(List<JAXBElement<?>> impostos) {
        for (JAXBElement<?> elem : impostos) {
            // Verifica se o elemento é do tipo COFINS
            if (elem.getValue() instanceof br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS) {
                br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS cof =
                        (br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS) elem.getValue();
                // Busca vCOFINS em cada modalidade possível no XML
                if (cof.getCOFINSAliq() != null && cof.getCOFINSAliq().getVCOFINS() != null)
                    return new BigDecimal(cof.getCOFINSAliq().getVCOFINS());
                if (cof.getCOFINSQtde() != null && cof.getCOFINSQtde().getVCOFINS() != null)
                    return new BigDecimal(cof.getCOFINSQtde().getVCOFINS());
                if (cof.getCOFINSOutr() != null && cof.getCOFINSOutr().getVCOFINS() != null)
                    return new BigDecimal(cof.getCOFINSOutr().getVCOFINS());
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * Recupera o valor do ICMS (vICMS) do produto.
     * <p>
     * Percorre todas as possíveis modalidades de ICMS, incluindo regime normal e Simples Nacional,
     * e retorna o primeiro valor encontrado. Caso não localize, retorna BigDecimal.ZERO.
     *
     * @param impostos Lista de elementos JAXB do bloco de impostos do produto.
     * @return Valor do ICMS (vICMS) ou BigDecimal.ZERO se não houver.
     */
    public static BigDecimal getVICMS(List<JAXBElement<?>> impostos) {
        for (JAXBElement<?> elem : impostos) {
            // Verifica se o elemento é do tipo ICMS
            if (elem.getValue() instanceof br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS) {
                br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS icms =
                        (br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS) elem.getValue();
                // Busca vICMS em cada modalidade possível no XML
                if (icms.getICMS00() != null && icms.getICMS00().getVICMS() != null)
                    return new BigDecimal(icms.getICMS00().getVICMS());
                if (icms.getICMS10() != null && icms.getICMS10().getVICMS() != null)
                    return new BigDecimal(icms.getICMS10().getVICMS());
                if (icms.getICMS20() != null && icms.getICMS20().getVICMS() != null)
                    return new BigDecimal(icms.getICMS20().getVICMS());
                if (icms.getICMS51() != null && icms.getICMS51().getVICMS() != null)
                    return new BigDecimal(icms.getICMS51().getVICMS());
                if (icms.getICMS90() != null && icms.getICMS90().getVICMS() != null)
                    return new BigDecimal(icms.getICMS90().getVICMS());
                if (icms.getICMSSN900() != null && icms.getICMSSN900().getVICMS() != null)
                    return new BigDecimal(icms.getICMSSN900().getVICMS());
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * Recupera o valor do ICMS de destino (vICMSUFDest), referente à partilha entre estados.
     *
     * @param impostos Lista de elementos JAXB do bloco de impostos do produto.
     * @return Valor do ICMS de destino (vICMSUFDest) ou BigDecimal.ZERO se não houver.
     */
    public static BigDecimal getVICMSUFDest(List<JAXBElement<?>> impostos) {
        for (JAXBElement<?> elem : impostos) {
            // Verifica se o elemento é do tipo ICMSUFDest
            if (elem.getValue() instanceof br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMSUFDest) {
                br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMSUFDest icmsUfDest =
                        (br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMSUFDest) elem.getValue();
                if (icmsUfDest.getVICMSUFDest() != null)
                    return new BigDecimal(icmsUfDest.getVICMSUFDest());
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * Recupera o valor do FCP (Fundo de Combate à Pobreza) do ICMS (vFCP).
     * <p>
     * Procura o valor do FCP nas modalidades disponíveis do ICMS.
     *
     * @param impostos Lista de elementos JAXB do bloco de impostos do produto.
     * @return Valor do FCP (vFCP) ou BigDecimal.ZERO se não houver.
     */
    public static BigDecimal getVFCP(List<JAXBElement<?>> impostos) {
        for (JAXBElement<?> elem : impostos) {
            // Verifica se o elemento é do tipo ICMS
            if (elem.getValue() instanceof br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS) {
                br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS icms =
                        (br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS) elem.getValue();
                // Busca vFCP em modalidades possíveis
                if (icms.getICMS00() != null && icms.getICMS00().getVFCP() != null)
                    return new BigDecimal(icms.getICMS00().getVFCP());
                if (icms.getICMS10() != null && icms.getICMS10().getVFCP() != null)
                    return new BigDecimal(icms.getICMS10().getVFCP());
                if (icms.getICMS20() != null && icms.getICMS20().getVFCP() != null)
                    return new BigDecimal(icms.getICMS20().getVFCP());
                if (icms.getICMS51() != null && icms.getICMS51().getVFCP() != null)
                    return new BigDecimal(icms.getICMS51().getVFCP());
                if (icms.getICMS90() != null && icms.getICMS90().getVFCP() != null)
                    return new BigDecimal(icms.getICMS90().getVFCP());
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * Recupera o valor do FCP de destino (vFCPUFDest), referente ao Fundo de Combate à Pobreza
     * na partilha entre estados.
     *
     * @param impostos Lista de elementos JAXB do bloco de impostos do produto.
     * @return Valor do FCP de destino (vFCPUFDest) ou BigDecimal.ZERO se não houver.
     */
    public static BigDecimal getVFCPUFDest(List<JAXBElement<?>> impostos) {
        for (JAXBElement<?> elem : impostos) {
            // Verifica se o elemento é do tipo ICMSUFDest
            if (elem.getValue() instanceof br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMSUFDest) {
                br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMSUFDest icmsUfDest =
                        (br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMSUFDest) elem.getValue();
                if (icmsUfDest.getVFCPUFDest() != null)
                    return new BigDecimal(icmsUfDest.getVFCPUFDest());
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * Recupera o valor do ICMS monofásico.
     *
     * @param impostos Lista de elementos JAXB do bloco de impostos do produto.
     * @return Valor do ICMS Monofásico ou BigDecimal.ZERO se não houver.
     *
     * OBS: Este método está como placeholder, ajuste conforme o schema e uso na sua empresa.
     */
    public static BigDecimal getVICMSMono(List<JAXBElement<?>> impostos) {
        for (JAXBElement<?> elem : impostos) {
            // Verifica se o elemento é do tipo ICMS
            if (elem.getValue() instanceof br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS) {
                br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS icms =
                        (br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS) elem.getValue();
                // Busca vICMS em cada modalidade possível no XML
                if (icms.getICMS02() != null && icms.getICMS02().getVICMSMono() != null)
                    return new BigDecimal(icms.getICMS02().getVICMSMono());
                if (icms.getICMS15() != null && icms.getICMS15().getVICMSMono() != null)
                    return new BigDecimal(icms.getICMS15().getVICMSMono());
                if (icms.getICMS53() != null && icms.getICMS53().getVICMSMono() != null)
                    return new BigDecimal(icms.getICMS53().getVICMSMono());
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * Recupera o valor do ISSQN (vISSQN) do produto, relacionado ao serviço.
     *
     * @param impostos Lista de elementos JAXB do bloco de impostos do produto.
     * @return Valor do ISSQN ou BigDecimal.ZERO se não houver.
     */
    public static BigDecimal getVISSQN(List<JAXBElement<?>> impostos) {
        for (JAXBElement<?> elem : impostos) {
            // Verifica se o elemento é do tipo ISSQN
            if (elem.getValue() instanceof br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ISSQN) {
                br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ISSQN issqn =
                        (br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ISSQN) elem.getValue();
                if (issqn.getVISSQN() != null)
                    return new BigDecimal(issqn.getVISSQN());
            }
        }
        return BigDecimal.ZERO;
    }

}
