
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkDeviceIdData complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="NetworkDeviceIdData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="device" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}Device"/>
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
@XmlType(name = "NetworkDeviceIdData", propOrder = {
    "device",
    "attributes"
})
public class NetworkDeviceIdData {

    @XmlElement(required = true)
    protected Device device;
    protected NetworkDeviceIdAttributes attributes;

    /**
     * device 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Device }
     *     
     */
    public Device getDevice() {
        return device;
    }

    /**
     * device 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Device }
     *     
     */
    public void setDevice(Device value) {
        this.device = value;
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
