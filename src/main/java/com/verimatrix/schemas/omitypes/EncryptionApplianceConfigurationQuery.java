
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EncryptionApplianceConfigurationQuery complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="EncryptionApplianceConfigurationQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsNetworkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applianceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contentType" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}ContentType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptionApplianceConfigurationQuery", propOrder = {
    "smsNetworkId",
    "applianceId",
    "contentType"
})
public class EncryptionApplianceConfigurationQuery {

    @XmlElement(required = true)
    protected String smsNetworkId;
    @XmlElement(required = true)
    protected String applianceId;
    @XmlElement(required = true)
    protected ContentType contentType;

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
     * applianceId �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplianceId() {
        return applianceId;
    }

    /**
     * applianceId �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplianceId(String value) {
        this.applianceId = value;
    }

    /**
     * contentType �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ContentType }
     *     
     */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * contentType �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentType }
     *     
     */
    public void setContentType(ContentType value) {
        this.contentType = value;
    }

}
