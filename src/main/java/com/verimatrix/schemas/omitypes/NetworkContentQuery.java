
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkContentQuery complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="NetworkContentQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsNetworkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smsContentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contentCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContentQuery", propOrder = {
    "smsNetworkId",
    "smsContentId",
    "contentCount"
})
public class NetworkContentQuery {

    @XmlElement(required = true)
    protected String smsNetworkId;
    @XmlElement(required = true)
    protected String smsContentId;
    protected int contentCount;

    /**
     * smsNetworkId 속성의 값을 가져옵니다.
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
     * smsNetworkId 속성의 값을 설정합니다.
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
     * smsContentId 속성의 값을 가져옵니다.
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
     * smsContentId 속성의 값을 설정합니다.
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
     * contentCount 속성의 값을 가져옵니다.
     * 
     */
    public int getContentCount() {
        return contentCount;
    }

    /**
     * contentCount 속성의 값을 설정합니다.
     * 
     */
    public void setContentCount(int value) {
        this.contentCount = value;
    }

}
