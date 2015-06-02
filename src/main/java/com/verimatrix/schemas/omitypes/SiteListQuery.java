
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SiteListQuery complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="SiteListQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsSiteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entityType" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}SiteEntityType"/>
 *         &lt;element name="siteCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SiteListQuery", propOrder = {
    "smsSiteId",
    "entityType",
    "siteCount"
})
public class SiteListQuery {

    @XmlElement(required = true)
    protected String smsSiteId;
    @XmlElement(required = true)
    protected SiteEntityType entityType;
    protected int siteCount;

    /**
     * smsSiteId �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsSiteId() {
        return smsSiteId;
    }

    /**
     * smsSiteId �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsSiteId(String value) {
        this.smsSiteId = value;
    }

    /**
     * entityType �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link SiteEntityType }
     *     
     */
    public SiteEntityType getEntityType() {
        return entityType;
    }

    /**
     * entityType �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteEntityType }
     *     
     */
    public void setEntityType(SiteEntityType value) {
        this.entityType = value;
    }

    /**
     * siteCount �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getSiteCount() {
        return siteCount;
    }

    /**
     * siteCount �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setSiteCount(int value) {
        this.siteCount = value;
    }

}
