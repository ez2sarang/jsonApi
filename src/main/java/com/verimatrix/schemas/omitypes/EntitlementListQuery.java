
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EntitlementListQuery complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="EntitlementListQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsEntitlementId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entityType" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}EntityType"/>
 *         &lt;element name="entitlementCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntitlementListQuery", propOrder = {
    "smsEntitlementId",
    "entityType",
    "entitlementCount"
})
public class EntitlementListQuery {

    @XmlElement(required = true)
    protected String smsEntitlementId;
    @XmlElement(required = true)
    protected EntityType entityType;
    protected int entitlementCount;

    /**
     * smsEntitlementId �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsEntitlementId() {
        return smsEntitlementId;
    }

    /**
     * smsEntitlementId �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsEntitlementId(String value) {
        this.smsEntitlementId = value;
    }

    /**
     * entityType �Ӽ��� ���� �����ɴϴ�.
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
     * entityType �Ӽ��� ���� �����մϴ�.
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
     * entitlementCount �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getEntitlementCount() {
        return entitlementCount;
    }

    /**
     * entitlementCount �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setEntitlementCount(int value) {
        this.entitlementCount = value;
    }

}
