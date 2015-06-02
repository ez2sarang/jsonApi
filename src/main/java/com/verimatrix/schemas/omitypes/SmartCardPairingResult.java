
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SmartCardPairingResult complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="SmartCardPairingResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}Result"/>
 *         &lt;element name="stbDeviceList" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}STBDeviceList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmartCardPairingResult", propOrder = {
    "result",
    "stbDeviceList"
})
public class SmartCardPairingResult {

    @XmlElement(required = true)
    protected Result result;
    protected STBDeviceList stbDeviceList;

    /**
     * result 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * result 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * stbDeviceList 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link STBDeviceList }
     *     
     */
    public STBDeviceList getStbDeviceList() {
        return stbDeviceList;
    }

    /**
     * stbDeviceList 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link STBDeviceList }
     *     
     */
    public void setStbDeviceList(STBDeviceList value) {
        this.stbDeviceList = value;
    }

}
