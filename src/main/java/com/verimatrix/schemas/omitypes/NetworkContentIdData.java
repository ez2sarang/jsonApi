
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkContentIdData complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="NetworkContentIdData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="networkContent" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}NetworkContent"/>
 *         &lt;element name="attributes" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}NetworkContentEncryptionAttributes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContentIdData", propOrder = {
    "networkContent",
    "attributes"
})
public class NetworkContentIdData {

    @XmlElement(required = true)
    protected NetworkContent networkContent;
    protected NetworkContentEncryptionAttributes attributes;

    /**
     * networkContent �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link NetworkContent }
     *     
     */
    public NetworkContent getNetworkContent() {
        return networkContent;
    }

    /**
     * networkContent �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkContent }
     *     
     */
    public void setNetworkContent(NetworkContent value) {
        this.networkContent = value;
    }

    /**
     * attributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link NetworkContentEncryptionAttributes }
     *     
     */
    public NetworkContentEncryptionAttributes getAttributes() {
        return attributes;
    }

    /**
     * attributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkContentEncryptionAttributes }
     *     
     */
    public void setAttributes(NetworkContentEncryptionAttributes value) {
        this.attributes = value;
    }

}
