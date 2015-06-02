
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.verimatrix.schemas.iptvtypes.DTVEncryptionAttributes;
import com.verimatrix.schemas.iptvtypes.VODEncryptionAttributes;


/**
 * <p>NetworkContentEncryptionAttributes complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * vodEncryptionAttributes �Ӽ��� ���� �����ɴϴ�.
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
     * vodEncryptionAttributes �Ӽ��� ���� �����մϴ�.
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
     * dtvEncryptionAttributes �Ӽ��� ���� �����ɴϴ�.
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
     * dtvEncryptionAttributes �Ӽ��� ���� �����մϴ�.
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
