
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PackageListQuery complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="PackageListQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsPackageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="packageCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageListQuery", propOrder = {
    "smsPackageId",
    "packageCount"
})
public class PackageListQuery {

    @XmlElement(required = true)
    protected String smsPackageId;
    protected int packageCount;

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
     * packageCount 속성의 값을 가져옵니다.
     * 
     */
    public int getPackageCount() {
        return packageCount;
    }

    /**
     * packageCount 속성의 값을 설정합니다.
     * 
     */
    public void setPackageCount(int value) {
        this.packageCount = value;
    }

}
