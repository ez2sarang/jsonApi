
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.verimatrix.schemas.iptvtypes.VODEncryptionStatus;


/**
 * <p>NetworkContentEncryptionStatus complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="NetworkContentEncryptionStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="encryptionStatus" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}VODEncryptionStatus"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContentEncryptionStatus", propOrder = {
    "encryptionStatus"
})
public class NetworkContentEncryptionStatus {

    protected VODEncryptionStatus encryptionStatus;

    /**
     * encryptionStatus 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link VODEncryptionStatus }
     *     
     */
    public VODEncryptionStatus getEncryptionStatus() {
        return encryptionStatus;
    }

    /**
     * encryptionStatus 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link VODEncryptionStatus }
     *     
     */
    public void setEncryptionStatus(VODEncryptionStatus value) {
        this.encryptionStatus = value;
    }

}
