
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DeviceDomainListQuery complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="DeviceDomainListQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsDeviceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smsDomainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="domainCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceDomainListQuery", propOrder = {
    "smsDeviceId",
    "smsDomainId",
    "domainCount"
})
public class DeviceDomainListQuery {

    @XmlElement(required = true)
    protected String smsDeviceId;
    @XmlElement(required = true)
    protected String smsDomainId;
    protected int domainCount;

    /**
     * smsDeviceId �Ӽ��� ���� �����ɴϴ�.
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
     * smsDeviceId �Ӽ��� ���� �����մϴ�.
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
     * smsDomainId �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsDomainId() {
        return smsDomainId;
    }

    /**
     * smsDomainId �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsDomainId(String value) {
        this.smsDomainId = value;
    }

    /**
     * domainCount �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getDomainCount() {
        return domainCount;
    }

    /**
     * domainCount �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setDomainCount(int value) {
        this.domainCount = value;
    }

}
