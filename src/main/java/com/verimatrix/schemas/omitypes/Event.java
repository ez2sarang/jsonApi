
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Event complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="Event">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsEventId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smsContentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smsNetworkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="exclusive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="preStartDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timePeriodRule" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}TimePeriodRule"/>
 *         &lt;element name="ratingLevel" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}RatingLevel" minOccurs="0"/>
 *         &lt;element name="fingerprintMessage" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}FingerprintMessage" minOccurs="0"/>
 *         &lt;element name="eventParameters" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}EventParameters" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", propOrder = {
    "smsEventId",
    "smsContentId",
    "smsNetworkId",
    "exclusive",
    "preStartDuration",
    "timePeriodRule",
    "ratingLevel",
    "fingerprintMessage",
    "eventParameters"
})
public class Event {

    @XmlElement(required = true)
    protected String smsEventId;
    @XmlElement(required = true)
    protected String smsContentId;
    @XmlElement(required = true)
    protected String smsNetworkId;
    protected boolean exclusive;
    protected int preStartDuration;
    @XmlElement(required = true)
    protected TimePeriodRule timePeriodRule;
    protected Integer ratingLevel;
    protected FingerprintMessage fingerprintMessage;
    protected EventParameters eventParameters;

    /**
     * smsEventId �Ӽ��� ���� �����ɴϴ�.
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
     * smsEventId �Ӽ��� ���� �����մϴ�.
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
     * exclusive �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isExclusive() {
        return exclusive;
    }

    /**
     * exclusive �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setExclusive(boolean value) {
        this.exclusive = value;
    }

    /**
     * preStartDuration �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getPreStartDuration() {
        return preStartDuration;
    }

    /**
     * preStartDuration �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setPreStartDuration(int value) {
        this.preStartDuration = value;
    }

    /**
     * timePeriodRule �Ӽ��� ���� �����ɴϴ�.
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
     * timePeriodRule �Ӽ��� ���� �����մϴ�.
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
     * eventParameters �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link EventParameters }
     *     
     */
    public EventParameters getEventParameters() {
        return eventParameters;
    }

    /**
     * eventParameters �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link EventParameters }
     *     
     */
    public void setEventParameters(EventParameters value) {
        this.eventParameters = value;
    }

}
