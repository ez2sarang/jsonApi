
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DTVEncryptionAttributes complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="DTVEncryptionAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applianceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inputURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="outputURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="copyControlAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}CopyControlAttributes"/>
 *         &lt;element name="osdAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}OSDAttributes"/>
 *         &lt;element name="encryptionAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}EncryptionAttributes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DTVEncryptionAttributes", propOrder = {
    "applianceName",
    "inputURL",
    "outputURL",
    "copyControlAttributes",
    "osdAttributes",
    "encryptionAttributes"
})
public class DTVEncryptionAttributes {

    @XmlElement(required = true)
    protected String applianceName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String inputURL;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String outputURL;
    @XmlElement(required = true)
    protected CopyControlAttributes copyControlAttributes;
    @XmlElement(required = true)
    protected OSDAttributes osdAttributes;
    @XmlElement(required = true)
    protected EncryptionAttributes encryptionAttributes;

    /**
     * applianceName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplianceName() {
        return applianceName;
    }

    /**
     * applianceName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplianceName(String value) {
        this.applianceName = value;
    }

    /**
     * inputURL 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputURL() {
        return inputURL;
    }

    /**
     * inputURL 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputURL(String value) {
        this.inputURL = value;
    }

    /**
     * outputURL 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputURL() {
        return outputURL;
    }

    /**
     * outputURL 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputURL(String value) {
        this.outputURL = value;
    }

    /**
     * copyControlAttributes 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CopyControlAttributes }
     *     
     */
    public CopyControlAttributes getCopyControlAttributes() {
        return copyControlAttributes;
    }

    /**
     * copyControlAttributes 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CopyControlAttributes }
     *     
     */
    public void setCopyControlAttributes(CopyControlAttributes value) {
        this.copyControlAttributes = value;
    }

    /**
     * osdAttributes 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link OSDAttributes }
     *     
     */
    public OSDAttributes getOsdAttributes() {
        return osdAttributes;
    }

    /**
     * osdAttributes 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link OSDAttributes }
     *     
     */
    public void setOsdAttributes(OSDAttributes value) {
        this.osdAttributes = value;
    }

    /**
     * encryptionAttributes 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionAttributes }
     *     
     */
    public EncryptionAttributes getEncryptionAttributes() {
        return encryptionAttributes;
    }

    /**
     * encryptionAttributes 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionAttributes }
     *     
     */
    public void setEncryptionAttributes(EncryptionAttributes value) {
        this.encryptionAttributes = value;
    }

}
