
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DomainListQuery complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="DomainListQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsDomainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="domainCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DomainListQuery", propOrder = {
    "smsDomainId",
    "domainCount"
})
public class DomainListQuery {

    @XmlElement(required = true)
    protected String smsDomainId;
    protected int domainCount;

    /**
     * smsDomainId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsDomainId() {
        return smsDomainId;
    }

    /**
     * smsDomainId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsDomainId(String value) {
        this.smsDomainId = value;
    }

    /**
     * domainCount 속성의 값을 가져옵니다.
     * 
     */
    public int getDomainCount() {
        return domainCount;
    }

    /**
     * domainCount 속성의 값을 설정합니다.
     * 
     */
    public void setDomainCount(int value) {
        this.domainCount = value;
    }

}
