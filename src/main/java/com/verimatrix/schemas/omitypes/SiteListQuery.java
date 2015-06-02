
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SiteListQuery complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * smsSiteId 속성의 값을 가져옵니다.
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
     * smsSiteId 속성의 값을 설정합니다.
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
     * entityType 속성의 값을 가져옵니다.
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
     * entityType 속성의 값을 설정합니다.
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
     * siteCount 속성의 값을 가져옵니다.
     * 
     */
    public int getSiteCount() {
        return siteCount;
    }

    /**
     * siteCount 속성의 값을 설정합니다.
     * 
     */
    public void setSiteCount(int value) {
        this.siteCount = value;
    }

}
