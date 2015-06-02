
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ContentEventQuery complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="ContentEventQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsContentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smsEventId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContentEventQuery", propOrder = {
    "smsContentId",
    "smsEventId",
    "eventCount"
})
public class ContentEventQuery {

    @XmlElement(required = true)
    protected String smsContentId;
    @XmlElement(required = true)
    protected String smsEventId;
    protected int eventCount;

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
     * smsEventId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsEventId() {
        return smsEventId;
    }

    /**
     * smsEventId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsEventId(String value) {
        this.smsEventId = value;
    }

    /**
     * eventCount 속성의 값을 가져옵니다.
     * 
     */
    public int getEventCount() {
        return eventCount;
    }

    /**
     * eventCount 속성의 값을 설정합니다.
     * 
     */
    public void setEventCount(int value) {
        this.eventCount = value;
    }

}
