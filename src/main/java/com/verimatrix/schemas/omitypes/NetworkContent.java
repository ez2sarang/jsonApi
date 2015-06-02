
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkContent complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="NetworkContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsNetworkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smsContentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkContentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="networkContentType" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}ContentType"/>
 *         &lt;element name="ratingLevel" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}RatingLevel" minOccurs="0"/>
 *         &lt;element name="fingerprintMessage" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}FingerprintMessage" minOccurs="0"/>
 *         &lt;element name="encryptionAttributes" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}NetworkContentEncryptionAttributes" minOccurs="0"/>
 *         &lt;element name="encryptionParameters" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}EncryptionParameters" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContent", propOrder = {
    "smsNetworkId",
    "smsContentId",
    "networkContentId",
    "networkContentType",
    "ratingLevel",
    "fingerprintMessage",
    "encryptionAttributes",
    "encryptionParameters"
})
public class NetworkContent {

    @XmlElement(required = true)
    protected String smsNetworkId;
    @XmlElement(required = true)
    protected String smsContentId;
    protected String networkContentId;
    @XmlElement(required = true)
    protected ContentType networkContentType;
    protected Integer ratingLevel;
    protected FingerprintMessage fingerprintMessage;
    protected NetworkContentEncryptionAttributes encryptionAttributes;
    protected EncryptionParameters encryptionParameters;

    /**
     * smsNetworkId �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsNetworkId() {
        return smsNetworkId;
    }

    /**
     * smsNetworkId �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsNetworkId(String value) {
        this.smsNetworkId = value;
    }

    /**
     * smsContentId �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsContentId() {
        return smsContentId;
    }

    /**
     * smsContentId �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsContentId(String value) {
        this.smsContentId = value;
    }

    /**
     * networkContentId �Ӽ��� ���� �����ɴϴ�.
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
     * networkContentId �Ӽ��� ���� �����մϴ�.
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
     * networkContentType �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ContentType }
     *     
     */
    public ContentType getNetworkContentType() {
        return networkContentType;
    }

    /**
     * networkContentType �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentType }
     *     
     */
    public void setNetworkContentType(ContentType value) {
        this.networkContentType = value;
    }

    /**
     * ratingLevel �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRatingLevel() {
        return ratingLevel;
    }

    /**
     * ratingLevel �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRatingLevel(Integer value) {
        this.ratingLevel = value;
    }

    /**
     * fingerprintMessage �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FingerprintMessage }
     *     
     */
    public FingerprintMessage getFingerprintMessage() {
        return fingerprintMessage;
    }

    /**
     * fingerprintMessage �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FingerprintMessage }
     *     
     */
    public void setFingerprintMessage(FingerprintMessage value) {
        this.fingerprintMessage = value;
    }

    /**
     * encryptionAttributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link NetworkContentEncryptionAttributes }
     *     
     */
    public NetworkContentEncryptionAttributes getEncryptionAttributes() {
        return encryptionAttributes;
    }

    /**
     * encryptionAttributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkContentEncryptionAttributes }
     *     
     */
    public void setEncryptionAttributes(NetworkContentEncryptionAttributes value) {
        this.encryptionAttributes = value;
    }

    /**
     * encryptionParameters �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionParameters }
     *     
     */
    public EncryptionParameters getEncryptionParameters() {
        return encryptionParameters;
    }

    /**
     * encryptionParameters �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionParameters }
     *     
     */
    public void setEncryptionParameters(EncryptionParameters value) {
        this.encryptionParameters = value;
    }

}
