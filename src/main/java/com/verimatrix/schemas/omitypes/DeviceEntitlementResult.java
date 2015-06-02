
package com.verimatrix.schemas.omitypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DeviceEntitlementResult complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="DeviceEntitlementResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}Result"/>
 *         &lt;element name="device" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}Device" minOccurs="0"/>
 *         &lt;element name="entitlements" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}DeviceData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceEntitlementResult", propOrder = {
    "result",
    "device",
    "entitlements"
})
public class DeviceEntitlementResult {

    @XmlElement(required = true)
    protected Result result;
    protected Device device;
    protected List<DeviceData> entitlements;

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
     * Gets the value of the entitlements property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entitlements property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntitlements().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeviceData }
     * 
     * 
     */
    public List<DeviceData> getEntitlements() {
        if (entitlements == null) {
            entitlements = new ArrayList<DeviceData>();
        }
        return this.entitlements;
    }

}
