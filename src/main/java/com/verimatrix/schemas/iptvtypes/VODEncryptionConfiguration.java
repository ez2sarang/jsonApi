
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>VODEncryptionConfiguration complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="VODEncryptionConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="encryptionAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}EncryptionAttributes"/>
 *         &lt;element name="copyControlAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}CopyControlAttributes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VODEncryptionConfiguration", propOrder = {
    "encryptionAttributes",
    "copyControlAttributes"
})
public class VODEncryptionConfiguration {

    @XmlElement(required = true)
    protected EncryptionAttributes encryptionAttributes;
    @XmlElement(required = true)
    protected CopyControlAttributes copyControlAttributes;

    /**
     * encryptionAttributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionAttributes }
     *     
     */
    public EncryptionAttributes getEncryptionAttributes() {
        return encryptionAttributes;
    }

    /**
     * encryptionAttributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionAttributes }
     *     
     */
    public void setEncryptionAttributes(EncryptionAttributes value) {
        this.encryptionAttributes = value;
    }

    /**
     * copyControlAttributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link CopyControlAttributes }
     *     
     */
    public CopyControlAttributes getCopyControlAttributes() {
        return copyControlAttributes;
    }

    /**
     * copyControlAttributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link CopyControlAttributes }
     *     
     */
    public void setCopyControlAttributes(CopyControlAttributes value) {
        this.copyControlAttributes = value;
    }

}
