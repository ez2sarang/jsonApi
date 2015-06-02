
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CopyControlAttributes complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="CopyControlAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ccCGMS_A" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}CGMS_A"/>
 *         &lt;element name="ccACP" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}ACP"/>
 *         &lt;element name="ccDwightCavendish" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ccHDCP" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CopyControlAttributes", propOrder = {
    "ccCGMSA",
    "ccACP",
    "ccDwightCavendish",
    "ccHDCP"
})
public class CopyControlAttributes {

    @XmlElement(name = "ccCGMS_A")
    protected int ccCGMSA;
    protected int ccACP;
    protected boolean ccDwightCavendish;
    protected boolean ccHDCP;

    /**
     * ccCGMSA �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getCcCGMSA() {
        return ccCGMSA;
    }

    /**
     * ccCGMSA �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setCcCGMSA(int value) {
        this.ccCGMSA = value;
    }

    /**
     * ccACP �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getCcACP() {
        return ccACP;
    }

    /**
     * ccACP �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setCcACP(int value) {
        this.ccACP = value;
    }

    /**
     * ccDwightCavendish �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isCcDwightCavendish() {
        return ccDwightCavendish;
    }

    /**
     * ccDwightCavendish �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setCcDwightCavendish(boolean value) {
        this.ccDwightCavendish = value;
    }

    /**
     * ccHDCP �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isCcHDCP() {
        return ccHDCP;
    }

    /**
     * ccHDCP �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setCcHDCP(boolean value) {
        this.ccHDCP = value;
    }

}
