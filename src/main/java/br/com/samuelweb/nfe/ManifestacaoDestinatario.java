package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.dom.Enum.TipoManifestacao;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.DateUtil;
import br.com.samuelweb.nfe.util.ListsUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.com.samuelweb.nfe.util.to.Manifesto;
import br.inf.portalfiscal.nfe.schema.envConfRecebto.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envConfRecebto.TEvento;
import br.inf.portalfiscal.nfe.schema.envConfRecebto.TRetEnvEvento;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com Data: 28/09/2017 - 11:11
 */
class ManifestacaoDestinatario {

	static TRetEnvEvento eventoManifestacao(ConfiguracoesNfe config, String chave, TipoManifestacao manifestacao,
											String cnpj, String data, String motivo) throws NfeException {
		try {
			TEnvEvento envEvento = new TEnvEvento();
			envEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);
			envEvento.setIdLote("1");

			TEvento evento = gettEvento(config,
					chave,
					manifestacao,
					cnpj,
					data,
					motivo
			);
			envEvento.getEvento().add(evento);

			return gettRetEnvEvento(config, envEvento);

		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}
	}

	static List<TRetEnvEvento> eventoManifestacao(ConfiguracoesNfe config, List<Manifesto> manifestos, String cnpj) throws NfeException {
		String dataFormatada = DateUtil.formatDate(LocalDateTime.now(), ZoneId.systemDefault());
		return eventoManifestacao(config, manifestos, cnpj, dataFormatada);
	}

	static List<TRetEnvEvento> eventoManifestacao(ConfiguracoesNfe config, List<Manifesto> manifestos, String cnpj, LocalDateTime data, ZoneId zoneId) throws NfeException {
		String dataFormatada = DateUtil.formatDate(data, zoneId);
		return eventoManifestacao(config, manifestos, cnpj, dataFormatada);
	}

	static List<TRetEnvEvento> eventoManifestacao(ConfiguracoesNfe config, List<Manifesto> manifestos, String cnpj, ZonedDateTime zonedDateTime) throws NfeException {
		String dataFormatada = DateUtil.formatDate(zonedDateTime);
		return eventoManifestacao(config, manifestos, cnpj, dataFormatada);
	}

	private static TEvento gettEvento(ConfiguracoesNfe config, String chave, TipoManifestacao manifestacao,
									  String cnpj, String data, String motivo) {
		String id = "ID" + manifestacao.getCodigo() + chave + "01";

		TEvento evento = new TEvento();
		evento.setVersao(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);

		TEvento.InfEvento infEvento = new TEvento.InfEvento();
		infEvento.setId(id);
		infEvento.setCOrgao("91");
		infEvento.setTpAmb(config.getAmbiente());
		infEvento.setCNPJ(cnpj);
		infEvento.setChNFe(chave);
		infEvento.setDhEvento(data);
		infEvento.setTpEvento(manifestacao.getCodigo());
		infEvento.setNSeqEvento("1");
		infEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);

		TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
		detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);
		detEvento.setDescEvento(manifestacao.getValor());
		if (TipoManifestacao.OPERACAO_NAO_REALIZADA.equals(manifestacao)) {
			detEvento.setXJust(motivo);
		}
		infEvento.setDetEvento(detEvento);
		evento.setInfEvento(infEvento);

		return evento;
	}

	private static List<TRetEnvEvento> eventoManifestacao(ConfiguracoesNfe config, List<Manifesto> manifestos, String cnpj, String data) throws NfeException {
		if (manifestos == null || manifestos.isEmpty()) {
			throw new NfeException("Nenhum evento para enviar");
		}
		try {
			List<TRetEnvEvento> tRetEnvEventoList = new ArrayList<>();
			List<List<Manifesto>> manifestosPartition = ListsUtil.partition(manifestos, 20);

			//Particiona a lista em sublistas de 20 elementos(HP04 evento G HP01 xml 1-20 - Evento, um lote pode conter até 20 eventos) e envia para ws
			for (List<Manifesto> manifestoList : manifestosPartition) {
				TEnvEvento envEvento = new TEnvEvento();
				envEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);
				envEvento.setIdLote("1");

				for (Manifesto manifesto : manifestoList) {
					TEvento evento = gettEvento(config,
							manifesto.getChave(),
							manifesto.getTipoManifestacao(),
							cnpj,
							data,
							manifesto.getMotivo()
					);
					envEvento.getEvento().add(evento);
				}
				TRetEnvEvento tRetEnvEvento = gettRetEnvEvento(config, envEvento);
				tRetEnvEventoList.add(tRetEnvEvento);
			}

			return tRetEnvEventoList;
		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}
	}

	private static TRetEnvEvento gettRetEnvEvento(ConfiguracoesNfe config, TEnvEvento envEvento) throws JAXBException, NfeException {
		String xml = XmlUtil.objectToXml(envEvento);
		xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

		xml = Eventos.enviarEvento(config, xml, ConstantesUtil.EVENTO.MANIFESTACAO, false, "");

		return XmlUtil.xmlToObject(xml, TRetEnvEvento.class);
	}

}

