
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>VODEncryptionStatus complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="VODEncryptionStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="statusText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="percentComplete" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VODEncryptionStatus", propOrder = {
    "statusCode",
    "statusText",
    "percentComplete"
})
public class VODEncryptionStatus {

    protected int statusCode;
    @XmlElement(required = true)
    protected String statusText;
    protected int percentComplete;

    /**
     * statusCode 속성의 값을 가져옵니다.
     * 
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * statusCode 속성의 값을 설정합니다.
     * 
     */
    public void setStatusCode(int value) {
        this.statusCode = value;
    }

    /**
     * statusText 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * statusText 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusText(String value) {
        this.statusText = value;
    }

    /**
     * percentComplete 속성의 값을 가져옵니다.
     * 
     */
    public int getPercentComplete() {
        return percentComplete;
    }

    /**
     * percentComplete 속성의 값을 설정합니다.
     * 
     */
    public void setPercentComplete(int value) {
        this.percentComplete = value;
    }

}
