//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.samuelweb.nfe.util.to;

import br.com.samuelweb.nfe.dom.Enum.TipoManifestacao;

public class Manifesto {
    private String chave;
    private TipoManifestacao tipoManifestacao;
    private String motivo;

    public Manifesto() {
    }

    public Manifesto(String chave, TipoManifestacao tipoManifestacao, String motivo) {
        this.chave = chave;
        this.tipoManifestacao = tipoManifestacao;
        this.motivo = motivo;
    }

    public String getChave() {
        return this.chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public TipoManifestacao getTipoManifestacao() {
        return this.tipoManifestacao;
    }

    public void setTipoManifestacao(TipoManifestacao tipoManifestacao) {
        this.tipoManifestacao = tipoManifestacao;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
