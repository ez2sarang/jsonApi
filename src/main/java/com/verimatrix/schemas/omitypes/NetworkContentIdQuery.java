
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkContentIdQuery complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="NetworkContentIdQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsNetworkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkContentType" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}ContentType"/>
 *         &lt;element name="networkContentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contentCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="filter" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}NetworkContentIdFilter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContentIdQuery", propOrder = {
    "smsNetworkId",
    "networkContentType",
    "networkContentId",
    "contentCount",
    "filter"
})
public class NetworkContentIdQuery {

    @XmlElement(required = true)
    protected String smsNetworkId;
    @XmlElement(required = true)
    protected ContentType networkContentType;
    @XmlElement(required = true)
    protected String networkContentId;
    protected int contentCount;
    protected NetworkContentIdFilter filter;

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
     * networkContentType �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ContentType }
     *     
     */
    public ContentType getNetworkContentType() {
        return networkContentType;
    }

    /**
     * networkContentType �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentType }
     *     
     */
    public void setNetworkContentType(ContentType value) {
        this.networkContentType = value;
    }

    /**
     * networkContentId �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkContentId() {
        return networkContentId;
    }

    /**
     * networkContentId �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkContentId(String value) {
        this.networkContentId = value;
    }

    /**
     * contentCount �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getContentCount() {
        return contentCount;
    }

    /**
     * contentCount �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setContentCount(int value) {
        this.contentCount = value;
    }

    /**
     * filter �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link NetworkContentIdFilter }
     *     
     */
    public NetworkContentIdFilter getFilter() {
        return filter;
    }

    /**
     * filter �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkContentIdFilter }
     *     
     */
    public void setFilter(NetworkContentIdFilter value) {
        this.filter = value;
    }

}
