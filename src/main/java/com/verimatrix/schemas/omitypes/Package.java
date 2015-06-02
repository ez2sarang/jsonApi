
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Package complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="Package">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsPackageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="packageAttributes" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}PackageAttributes" minOccurs="0"/>
 *         &lt;element name="packageParameters" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}PackageParameters" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Package", propOrder = {
    "smsPackageId",
    "description",
    "packageAttributes",
    "packageParameters"
})
public class Package {

    @XmlElement(required = true)
    protected String smsPackageId;
    protected String description;
    protected PackageAttributes packageAttributes;
    protected PackageParameters packageParameters;

    /**
     * smsPackageId �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsPackageId() {
        return smsPackageId;
    }

    /**
     * smsPackageId �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsPackageId(String value) {
        this.smsPackageId = value;
    }

    /**
     * description �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * description �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * packageAttributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link PackageAttributes }
     *     
     */
    public PackageAttributes getPackageAttributes() {
        return packageAttributes;
    }

    /**
     * packageAttributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageAttributes }
     *     
     */
    public void setPackageAttributes(PackageAttributes value) {
        this.packageAttributes = value;
    }

    /**
     * packageParameters �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link PackageParameters }
     *     
     */
    public PackageParameters getPackageParameters() {
        return packageParameters;
    }

    /**
     * packageParameters �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageParameters }
     *     
     */
    public void setPackageParameters(PackageParameters value) {
        this.packageParameters = value;
    }

}
