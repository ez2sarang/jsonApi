
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DeviceTypeLicenseData complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="DeviceTypeLicenseData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deviceTypeData" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}DeviceTypeData"/>
 *         &lt;element name="licenseData" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}LicenseData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceTypeLicenseData", propOrder = {
    "deviceTypeData",
    "licenseData"
})
public class DeviceTypeLicenseData {

    @XmlElement(required = true)
    protected DeviceTypeData deviceTypeData;
    @XmlElement(required = true)
    protected LicenseData licenseData;

    /**
     * deviceTypeData �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DeviceTypeData }
     *     
     */
    public DeviceTypeData getDeviceTypeData() {
        return deviceTypeData;
    }

    /**
     * deviceTypeData �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceTypeData }
     *     
     */
    public void setDeviceTypeData(DeviceTypeData value) {
        this.deviceTypeData = value;
    }

    /**
     * licenseData �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link LicenseData }
     *     
     */
    public LicenseData getLicenseData() {
        return licenseData;
    }

    /**
     * licenseData �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link LicenseData }
     *     
     */
    public void setLicenseData(LicenseData value) {
        this.licenseData = value;
    }

}
