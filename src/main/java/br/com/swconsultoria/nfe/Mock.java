package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub;
import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub.NfeResultMsg;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import br.com.swconsultoria.nfe.wsdl.NFeStatusServico4.NFeStatusServico4Stub;

public interface Mock {
	
	public NFeStatusServico4Stub.NfeResultMsg nfeStatusServicoNF(NFeStatusServico4Stub.NfeDadosMsg dadosMsg);
	public NFeRecepcaoEvento4Stub.NfeResultMsg nfeRecepcaoEvento(NFeRecepcaoEvento4Stub.NfeDadosMsg dadosMsg, ServicosEnum tipoEvento);
	public NfeResultMsg nfeInutilizacaoNF(NFeInutilizacao4Stub.NfeDadosMsg dadosMsg);
}
