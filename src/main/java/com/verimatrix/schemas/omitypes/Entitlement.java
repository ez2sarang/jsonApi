
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Entitlement complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="Entitlement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsEntitlementId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="package" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}Package"/>
 *         &lt;element name="entitledEntity" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}EntitledEntity"/>
 *         &lt;element name="timePeriodRule" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}TimePeriodRule"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entitlement", propOrder = {
    "smsEntitlementId",
    "_package",
    "entitledEntity",
    "timePeriodRule"
})
public class Entitlement {

    @XmlElement(required = true)
    protected String smsEntitlementId;
    @XmlElement(name = "package", required = true)
    protected Package _package;
    @XmlElement(required = true)
    protected EntitledEntity entitledEntity;
    @XmlElement(required = true)
    protected TimePeriodRule timePeriodRule;

    /**
     * smsEntitlementId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsEntitlementId() {
        return smsEntitlementId;
    }

    /**
     * smsEntitlementId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsEntitlementId(String value) {
        this.smsEntitlementId = value;
    }

    /**
     * package 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Package }
     *     
     */
    public Package getPackage() {
        return _package;
    }

    /**
     * package 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Package }
     *     
     */
    public void setPackage(Package value) {
        this._package = value;
    }

    /**
     * entitledEntity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EntitledEntity }
     *     
     */
    public EntitledEntity getEntitledEntity() {
        return entitledEntity;
    }

    /**
     * entitledEntity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EntitledEntity }
     *     
     */
    public void setEntitledEntity(EntitledEntity value) {
        this.entitledEntity = value;
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

}
