
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.verimatrix.schemas.iptvtypes.DTVEncryptionConfiguration;
import com.verimatrix.schemas.iptvtypes.VODEncryptionConfiguration;


/**
 * <p>EncryptionApplianceConfiguration complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * vodEncryptionConfiguration �Ӽ��� ���� �����ɴϴ�.
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
     * vodEncryptionConfiguration �Ӽ��� ���� �����մϴ�.
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
     * dtvEncryptionConfiguration �Ӽ��� ���� �����ɴϴ�.
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
     * dtvEncryptionConfiguration �Ӽ��� ���� �����մϴ�.
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
