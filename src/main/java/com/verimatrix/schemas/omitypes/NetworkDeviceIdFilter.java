
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkDeviceIdFilter complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="NetworkDeviceIdFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deviceType" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}DeviceType" minOccurs="0"/>
 *         &lt;element name="attributes" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}NetworkDeviceIdAttributes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkDeviceIdFilter", propOrder = {
    "deviceType",
    "attributes"
})
public class NetworkDeviceIdFilter {

    protected DeviceType deviceType;
    protected NetworkDeviceIdAttributes attributes;

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
     * attributes 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link NetworkDeviceIdAttributes }
     *     
     */
    public NetworkDeviceIdAttributes getAttributes() {
        return attributes;
    }

    /**
     * attributes 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkDeviceIdAttributes }
     *     
     */
    public void setAttributes(NetworkDeviceIdAttributes value) {
        this.attributes = value;
    }

}
