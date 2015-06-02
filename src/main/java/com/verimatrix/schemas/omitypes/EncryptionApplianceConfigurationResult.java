
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EncryptionApplianceConfigurationResult complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="EncryptionApplianceConfigurationResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}Result"/>
 *         &lt;element name="ratingLevel" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}RatingLevel" minOccurs="0"/>
 *         &lt;element name="applianceConfiguration" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}EncryptionApplianceConfiguration" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptionApplianceConfigurationResult", propOrder = {
    "result",
    "ratingLevel",
    "applianceConfiguration"
})
public class EncryptionApplianceConfigurationResult {

    @XmlElement(required = true)
    protected Result result;
    protected Integer ratingLevel;
    protected EncryptionApplianceConfiguration applianceConfiguration;

    /**
     * result 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * result 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * ratingLevel 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRatingLevel() {
        return ratingLevel;
    }

    /**
     * ratingLevel 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRatingLevel(Integer value) {
        this.ratingLevel = value;
    }

    /**
     * applianceConfiguration 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionApplianceConfiguration }
     *     
     */
    public EncryptionApplianceConfiguration getApplianceConfiguration() {
        return applianceConfiguration;
    }

    /**
     * applianceConfiguration 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionApplianceConfiguration }
     *     
     */
    public void setApplianceConfiguration(EncryptionApplianceConfiguration value) {
        this.applianceConfiguration = value;
    }

}
