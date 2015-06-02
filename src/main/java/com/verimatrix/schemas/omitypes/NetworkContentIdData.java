
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkContentIdData complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * networkContent 속성의 값을 가져옵니다.
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
     * networkContent 속성의 값을 설정합니다.
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
     * attributes 속성의 값을 가져옵니다.
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
     * attributes 속성의 값을 설정합니다.
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
