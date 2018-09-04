package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class MontaICMSCst90 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        if (StringUtils.isNotBlank(icms.getUFST())
                || icms.getpBCOp().compareTo(BigDecimal.ZERO) != 0) {
            buildIcmsPartilha(imposto, icms);
        } else{
            buildIcms(imposto, icms);
        }
    }

    private void buildIcms(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 icms90 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS90();
        if (icms.getOrig() != null) {
            icms90.setOrig(icms.getOrig().toString());
        }
        if (icms.getCST() != null) {
            icms90.setCST(icms.getCST().getValue());
        }
        if (icms.getvBC().compareTo(BigDecimal.ZERO) > 0
                || icms.getvICMS().compareTo(BigDecimal.ZERO) > 0) {
            if (icms.getModBC() != null) {
                icms90.setModBC(icms.getModBC().getValue().toString());
            }
            if (icms.getvBC() != null) {
                icms90.setVBC(icms.getvBC().toString());
            }
            if (icms.getpRedBC() != null) {
                icms90.setPRedBC(icms.getpRedBC().toString());
            }
            if (icms.getpICMS() != null) {
                icms90.setPICMS(icms.getpICMS().toString());
            }
            if (icms.getvICMS() != null) {
                icms90.setVICMS(icms.getvICMS().toString());
            }
        }

        if (icms.getvBCST().compareTo(BigDecimal.ZERO) > 0
                || icms.getvICMSST().compareTo(BigDecimal.ZERO) > 0) {
            if (icms.getModBCST() != null) {
                icms90.setModBCST(icms.getModBCST().toString());
            }
            if (icms.getpMVAST() != null) {
                icms90.setPMVAST(icms.getpMVAST().toString());
            }
            if (icms.getpRedBCST() != null) {
                icms90.setPRedBCST(icms.getpRedBCST().toString());
            }
            if (icms.getvBCST() != null) {
                icms90.setVBCST(icms.getvBCST().toString());
            }
            if (icms.getpICMSST() != null) {
                icms90.setPICMSST(icms.getpICMSST().toString());
            }
            if (icms.getvICMSST() != null) {
                icms90.setVICMSST(icms.getvICMSST().toString());
            }
        }
        if (icms.getvBCFCP() != null) {
            icms90.setVBCFCP(icms.getvBCFCP().toString());
        }
        if (icms.getpFCP() != null) {
            icms90.setPFCP(icms.getpFCP().toString());
        }
        if (icms.getvFCP() != null) {
            icms90.setVFCP(icms.getvFCP().toString());
        }
        if (icms.getvBCFCPST() != null) {
            icms90.setVBCFCPST(icms.getvBCFCPST().toString());
        }
        if (icms.getpFCPST() != null) {
            icms90.setPFCPST(icms.getpFCPST().toString());
        }
        if (icms.getvFCPST() != null) {
            icms90.setVFCPST(icms.getvFCPST().toString());
        }
        if (icms.getvICMSDeson() != null) {
            icms90.setVICMSDeson(icms.getvICMSDeson().toString());
        }
        if (icms.getMotDesICMS() != null) {
            icms90.setMotDesICMS(icms.getMotDesICMS().toString());
        }
        imposto.setICMS90(icms90);
    }

    private void buildIcmsPartilha(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart icmsPart = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart();
        if (icms.getOrig() != null) {
            icmsPart.setOrig(icms.getOrig().toString());
        }
        if (icms.getCST() != null) {
            icmsPart.setCST(icms.getCST().getValue());
        }
        if (icms.getvBC().compareTo(BigDecimal.ZERO) > 0
                || icms.getvICMS().compareTo(BigDecimal.ZERO) > 0) {
            if (icms.getModBC() != null) {
                icmsPart.setModBC(icms.getModBC().getValue().toString());
            }
            if (icms.getvBC() != null) {
                icmsPart.setVBC(icms.getvBC().toString());
            }
            if (icms.getpRedBC() != null) {
                icmsPart.setPRedBC(icms.getpRedBC().toString());
            }
            if (icms.getpICMS() != null) {
                icmsPart.setPICMS(icms.getpICMS().toString());
            }
            if (icms.getvICMS() != null) {
                icmsPart.setVICMS(icms.getvICMS().toString());
            }
        }
        if (icms.getvBCST().compareTo(BigDecimal.ZERO) > 0
                || icms.getvICMSST().compareTo(BigDecimal.ZERO) > 0) {
            if (icms.getModBCST() != null) {
                icmsPart.setModBCST(icms.getModBCST().toString());
            }
            if (icms.getpMVAST() != null) {
                icmsPart.setPMVAST(icms.getpMVAST().toString());
            }
            if (icms.getpRedBCST() != null) {
                icmsPart.setPRedBCST(icms.getpRedBCST().toString());
            }
            if (icms.getvBCST() != null) {
                icmsPart.setVBCST(icms.getvBCST().toString());
            }
            if (icms.getpICMSST() != null) {
                icmsPart.setPICMSST(icms.getpICMSST().toString());
            }
            if (icms.getvICMSST() != null) {
                icmsPart.setVICMSST(icms.getvICMSST().toString());
            }
        }
        if (icms.getpBCOp() != null) {
            icmsPart.setPBCOp(icms.getpBCOp().toString());
        }
        if (icms.getUFST() != null) {
            icmsPart.setUFST(TUf.fromValue(icms.getUFST()));
        }
        imposto.setICMSPart(icmsPart);
    }
}
