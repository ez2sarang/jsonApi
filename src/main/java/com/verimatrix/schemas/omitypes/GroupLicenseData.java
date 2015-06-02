
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GroupLicenseData complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="GroupLicenseData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="groupData" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}GroupData"/>
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
@XmlType(name = "GroupLicenseData", propOrder = {
    "groupData",
    "licenseData"
})
public class GroupLicenseData {

    @XmlElement(required = true)
    protected GroupData groupData;
    @XmlElement(required = true)
    protected LicenseData licenseData;

    /**
     * groupData �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link GroupData }
     *     
     */
    public GroupData getGroupData() {
        return groupData;
    }

    /**
     * groupData �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupData }
     *     
     */
    public void setGroupData(GroupData value) {
        this.groupData = value;
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
