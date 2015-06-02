
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.verimatrix.schemas.iptvtypes.VODEncryptionStatus;


/**
 * <p>NetworkContentEncryptionStatus complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * encryptionStatus �Ӽ��� ���� �����ɴϴ�.
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
     * encryptionStatus �Ӽ��� ���� �����մϴ�.
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
