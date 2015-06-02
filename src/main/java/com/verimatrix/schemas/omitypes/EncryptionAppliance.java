
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EncryptionAppliance complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="EncryptionAppliance">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applianceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkContentType" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}ContentType" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptionAppliance", propOrder = {
    "applianceId",
    "networkContentType",
    "description"
})
public class EncryptionAppliance {

    @XmlElement(required = true)
    protected String applianceId;
    protected ContentType networkContentType;
    protected String description;

    /**
     * applianceId 속성의 값을 가져옵니다.
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
     * applianceId 속성의 값을 설정합니다.
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
     * networkContentType 속성의 값을 가져옵니다.
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
     * networkContentType 속성의 값을 설정합니다.
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
     * description 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * description 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
