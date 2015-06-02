
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkContentTypeEncryptionStatus complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="NetworkContentTypeEncryptionStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="networkContentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkContentEncryptionStatus" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}NetworkContentEncryptionStatus"/>
 *         &lt;element name="encryptionStatus" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}EncryptionStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContentTypeEncryptionStatus", propOrder = {
    "networkContentId",
    "networkContentEncryptionStatus",
    "encryptionStatus"
})
public class NetworkContentTypeEncryptionStatus {

    @XmlElement(required = true)
    protected String networkContentId;
    @XmlElement(required = true)
    protected NetworkContentEncryptionStatus networkContentEncryptionStatus;
    @XmlElement(required = true)
    protected EncryptionStatus encryptionStatus;

    /**
     * networkContentId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkContentId() {
        return networkContentId;
    }

    /**
     * networkContentId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkContentId(String value) {
        this.networkContentId = value;
    }

    /**
     * networkContentEncryptionStatus 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link NetworkContentEncryptionStatus }
     *     
     */
    public NetworkContentEncryptionStatus getNetworkContentEncryptionStatus() {
        return networkContentEncryptionStatus;
    }

    /**
     * networkContentEncryptionStatus 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkContentEncryptionStatus }
     *     
     */
    public void setNetworkContentEncryptionStatus(NetworkContentEncryptionStatus value) {
        this.networkContentEncryptionStatus = value;
    }

    /**
     * encryptionStatus 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionStatus }
     *     
     */
    public EncryptionStatus getEncryptionStatus() {
        return encryptionStatus;
    }

    /**
     * encryptionStatus 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionStatus }
     *     
     */
    public void setEncryptionStatus(EncryptionStatus value) {
        this.encryptionStatus = value;
    }

}
