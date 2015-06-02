
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Domain complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="Domain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsDomainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maxDevices" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timePeriodRule" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}TimePeriodRule"/>
 *         &lt;element name="domainAddress" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}DomainAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Domain", propOrder = {
    "smsDomainId",
    "maxDevices",
    "timePeriodRule",
    "domainAddress"
})
public class Domain {

    @XmlElement(required = true)
    protected String smsDomainId;
    protected int maxDevices;
    @XmlElement(required = true)
    protected TimePeriodRule timePeriodRule;
    protected Integer domainAddress;

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
     * maxDevices 속성의 값을 가져옵니다.
     * 
     */
    public int getMaxDevices() {
        return maxDevices;
    }

    /**
     * maxDevices 속성의 값을 설정합니다.
     * 
     */
    public void setMaxDevices(int value) {
        this.maxDevices = value;
    }

    /**
     * timePeriodRule 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriodRule }
     *     
     */
    public TimePeriodRule getTimePeriodRule() {
        return timePeriodRule;
    }

    /**
     * timePeriodRule 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriodRule }
     *     
     */
    public void setTimePeriodRule(TimePeriodRule value) {
        this.timePeriodRule = value;
    }

    /**
     * domainAddress 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDomainAddress() {
        return domainAddress;
    }

    /**
     * domainAddress 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDomainAddress(Integer value) {
        this.domainAddress = value;
    }

}
