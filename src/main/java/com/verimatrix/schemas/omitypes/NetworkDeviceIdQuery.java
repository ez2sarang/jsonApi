
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkDeviceIdQuery complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="NetworkDeviceIdQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsNetworkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkDeviceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deviceCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="filter" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}NetworkDeviceIdFilter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkDeviceIdQuery", propOrder = {
    "smsNetworkId",
    "networkDeviceId",
    "deviceCount",
    "filter"
})
public class NetworkDeviceIdQuery {

    @XmlElement(required = true)
    protected String smsNetworkId;
    @XmlElement(required = true)
    protected String networkDeviceId;
    protected int deviceCount;
    protected NetworkDeviceIdFilter filter;

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
     * networkDeviceId �Ӽ��� ���� �����ɴϴ�.
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
     * networkDeviceId �Ӽ��� ���� �����մϴ�.
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
     * deviceCount �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getDeviceCount() {
        return deviceCount;
    }

    /**
     * deviceCount �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setDeviceCount(int value) {
        this.deviceCount = value;
    }

    /**
     * filter �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link NetworkDeviceIdFilter }
     *     
     */
    public NetworkDeviceIdFilter getFilter() {
        return filter;
    }

    /**
     * filter �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkDeviceIdFilter }
     *     
     */
    public void setFilter(NetworkDeviceIdFilter value) {
        this.filter = value;
    }

}
