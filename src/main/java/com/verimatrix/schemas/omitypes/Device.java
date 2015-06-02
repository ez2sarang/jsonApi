
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Device complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="Device">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsDeviceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smsNetworkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deviceType" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}DeviceType"/>
 *         &lt;element name="networkDeviceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smsAuthenticator" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Device", propOrder = {
    "smsDeviceId",
    "smsNetworkId",
    "deviceType",
    "networkDeviceId",
    "smsAuthenticator"
})
public class Device {

    @XmlElement(required = true)
    protected String smsDeviceId;
    @XmlElement(required = true)
    protected String smsNetworkId;
    @XmlElement(required = true)
    protected DeviceType deviceType;
    @XmlElement(required = true)
    protected String networkDeviceId;
    protected byte[] smsAuthenticator;

    /**
     * smsDeviceId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsDeviceId() {
        return smsDeviceId;
    }

    /**
     * smsDeviceId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsDeviceId(String value) {
        this.smsDeviceId = value;
    }

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
     * deviceType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link DeviceType }
     *     
     */
    public DeviceType getDeviceType() {
        return deviceType;
    }

    /**
     * deviceType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceType }
     *     
     */
    public void setDeviceType(DeviceType value) {
        this.deviceType = value;
    }

    /**
     * networkDeviceId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkDeviceId() {
        return networkDeviceId;
    }

    /**
     * networkDeviceId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkDeviceId(String value) {
        this.networkDeviceId = value;
    }

    /**
     * smsAuthenticator 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSmsAuthenticator() {
        return smsAuthenticator;
    }

    /**
     * smsAuthenticator 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSmsAuthenticator(byte[] value) {
        this.smsAuthenticator = value;
    }

}
