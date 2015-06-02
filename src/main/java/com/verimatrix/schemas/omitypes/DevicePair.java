
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DevicePair complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="DevicePair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stbDevice" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}STBDevice"/>
 *         &lt;element name="smartCardDevice" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}SmartCardDevice"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DevicePair", propOrder = {
    "stbDevice",
    "smartCardDevice"
})
public class DevicePair {

    @XmlElement(required = true)
    protected STBDevice stbDevice;
    @XmlElement(required = true)
    protected SmartCardDevice smartCardDevice;

    /**
     * stbDevice 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link STBDevice }
     *     
     */
    public STBDevice getStbDevice() {
        return stbDevice;
    }

    /**
     * stbDevice 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link STBDevice }
     *     
     */
    public void setStbDevice(STBDevice value) {
        this.stbDevice = value;
    }

    /**
     * smartCardDevice 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SmartCardDevice }
     *     
     */
    public SmartCardDevice getSmartCardDevice() {
        return smartCardDevice;
    }

    /**
     * smartCardDevice 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SmartCardDevice }
     *     
     */
    public void setSmartCardDevice(SmartCardDevice value) {
        this.smartCardDevice = value;
    }

}
