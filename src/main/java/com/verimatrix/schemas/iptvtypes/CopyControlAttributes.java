
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CopyControlAttributes complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * ccCGMSA 속성의 값을 가져옵니다.
     * 
     */
    public int getCcCGMSA() {
        return ccCGMSA;
    }

    /**
     * ccCGMSA 속성의 값을 설정합니다.
     * 
     */
    public void setCcCGMSA(int value) {
        this.ccCGMSA = value;
    }

    /**
     * ccACP 속성의 값을 가져옵니다.
     * 
     */
    public int getCcACP() {
        return ccACP;
    }

    /**
     * ccACP 속성의 값을 설정합니다.
     * 
     */
    public void setCcACP(int value) {
        this.ccACP = value;
    }

    /**
     * ccDwightCavendish 속성의 값을 가져옵니다.
     * 
     */
    public boolean isCcDwightCavendish() {
        return ccDwightCavendish;
    }

    /**
     * ccDwightCavendish 속성의 값을 설정합니다.
     * 
     */
    public void setCcDwightCavendish(boolean value) {
        this.ccDwightCavendish = value;
    }

    /**
     * ccHDCP 속성의 값을 가져옵니다.
     * 
     */
    public boolean isCcHDCP() {
        return ccHDCP;
    }

    /**
     * ccHDCP 속성의 값을 설정합니다.
     * 
     */
    public void setCcHDCP(boolean value) {
        this.ccHDCP = value;
    }

}
