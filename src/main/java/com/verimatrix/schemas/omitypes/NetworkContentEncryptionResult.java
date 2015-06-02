
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkContentEncryptionResult complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="NetworkContentEncryptionResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}Result"/>
 *         &lt;element name="networkContentEncryptionStatus" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}NetworkContentEncryptionStatus" minOccurs="0"/>
 *         &lt;element name="encryptionStatus" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}EncryptionStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContentEncryptionResult", propOrder = {
    "result",
    "networkContentEncryptionStatus",
    "encryptionStatus"
})
public class NetworkContentEncryptionResult {

    @XmlElement(required = true)
    protected Result result;
    protected NetworkContentEncryptionStatus networkContentEncryptionStatus;
    protected EncryptionStatus encryptionStatus;

    /**
     * result 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * result 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
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
