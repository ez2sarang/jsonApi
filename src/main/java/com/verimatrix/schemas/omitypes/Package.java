
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Package complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * smsPackageId 속성의 값을 가져옵니다.
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
     * smsPackageId 속성의 값을 설정합니다.
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
     * description 속성의 값을 가져옵니다.
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
     * description 속성의 값을 설정합니다.
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
     * packageAttributes 속성의 값을 가져옵니다.
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
     * packageAttributes 속성의 값을 설정합니다.
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
     * packageParameters 속성의 값을 가져옵니다.
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
     * packageParameters 속성의 값을 설정합니다.
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
