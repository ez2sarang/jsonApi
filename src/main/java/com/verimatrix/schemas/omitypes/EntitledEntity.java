
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EntitledEntity complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="EntitledEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityType" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}EntityType"/>
 *         &lt;element name="entityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntitledEntity", propOrder = {
    "entityType",
    "entityId"
})
public class EntitledEntity {

    @XmlElement(required = true)
    protected EntityType entityType;
    @XmlElement(required = true)
    protected String entityId;

    /**
     * entityType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EntityType }
     *     
     */
    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * entityType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityType }
     *     
     */
    public void setEntityType(EntityType value) {
        this.entityType = value;
    }

    /**
     * entityId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * entityId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityId(String value) {
        this.entityId = value;
    }

}
