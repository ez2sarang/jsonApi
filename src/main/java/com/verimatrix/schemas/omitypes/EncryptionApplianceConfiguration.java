
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.verimatrix.schemas.iptvtypes.DTVEncryptionConfiguration;
import com.verimatrix.schemas.iptvtypes.VODEncryptionConfiguration;


/**
 * <p>EncryptionApplianceConfiguration complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="EncryptionApplianceConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="vodEncryptionConfiguration" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}VODEncryptionConfiguration"/>
 *         &lt;element name="dtvEncryptionConfiguration" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}DTVEncryptionConfiguration"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptionApplianceConfiguration", propOrder = {
    "vodEncryptionConfiguration",
    "dtvEncryptionConfiguration"
})
public class EncryptionApplianceConfiguration {

    protected VODEncryptionConfiguration vodEncryptionConfiguration;
    protected DTVEncryptionConfiguration dtvEncryptionConfiguration;

    /**
     * vodEncryptionConfiguration 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link VODEncryptionConfiguration }
     *     
     */
    public VODEncryptionConfiguration getVodEncryptionConfiguration() {
        return vodEncryptionConfiguration;
    }

    /**
     * vodEncryptionConfiguration 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link VODEncryptionConfiguration }
     *     
     */
    public void setVodEncryptionConfiguration(VODEncryptionConfiguration value) {
        this.vodEncryptionConfiguration = value;
    }

    /**
     * dtvEncryptionConfiguration 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link DTVEncryptionConfiguration }
     *     
     */
    public DTVEncryptionConfiguration getDtvEncryptionConfiguration() {
        return dtvEncryptionConfiguration;
    }

    /**
     * dtvEncryptionConfiguration 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link DTVEncryptionConfiguration }
     *     
     */
    public void setDtvEncryptionConfiguration(DTVEncryptionConfiguration value) {
        this.dtvEncryptionConfiguration = value;
    }

}
