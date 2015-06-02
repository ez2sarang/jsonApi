
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.verimatrix.schemas.iptvtypes.DTVEncryptionAttributes;
import com.verimatrix.schemas.iptvtypes.VODEncryptionAttributes;


/**
 * <p>NetworkContentEncryptionAttributes complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="NetworkContentEncryptionAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="vodEncryptionAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}VODEncryptionAttributes"/>
 *         &lt;element name="dtvEncryptionAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}DTVEncryptionAttributes"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContentEncryptionAttributes", propOrder = {
    "vodEncryptionAttributes",
    "dtvEncryptionAttributes"
})
public class NetworkContentEncryptionAttributes {

    protected VODEncryptionAttributes vodEncryptionAttributes;
    protected DTVEncryptionAttributes dtvEncryptionAttributes;

    /**
     * vodEncryptionAttributes 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link VODEncryptionAttributes }
     *     
     */
    public VODEncryptionAttributes getVodEncryptionAttributes() {
        return vodEncryptionAttributes;
    }

    /**
     * vodEncryptionAttributes 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link VODEncryptionAttributes }
     *     
     */
    public void setVodEncryptionAttributes(VODEncryptionAttributes value) {
        this.vodEncryptionAttributes = value;
    }

    /**
     * dtvEncryptionAttributes 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link DTVEncryptionAttributes }
     *     
     */
    public DTVEncryptionAttributes getDtvEncryptionAttributes() {
        return dtvEncryptionAttributes;
    }

    /**
     * dtvEncryptionAttributes 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link DTVEncryptionAttributes }
     *     
     */
    public void setDtvEncryptionAttributes(DTVEncryptionAttributes value) {
        this.dtvEncryptionAttributes = value;
    }

}
