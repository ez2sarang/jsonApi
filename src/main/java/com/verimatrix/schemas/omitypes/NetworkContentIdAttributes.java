
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.verimatrix.schemas.iptvtypes.DTVContentAttributes;
import com.verimatrix.schemas.iptvtypes.VODContentAttributes;


/**
 * <p>NetworkContentIdAttributes complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="NetworkContentIdAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="dtvContentAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}DTVContentAttributes"/>
 *         &lt;element name="vodContentAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}VODContentAttributes"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContentIdAttributes", propOrder = {
    "dtvContentAttributes",
    "vodContentAttributes"
})
public class NetworkContentIdAttributes {

    protected DTVContentAttributes dtvContentAttributes;
    protected VODContentAttributes vodContentAttributes;

    /**
     * dtvContentAttributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DTVContentAttributes }
     *     
     */
    public DTVContentAttributes getDtvContentAttributes() {
        return dtvContentAttributes;
    }

    /**
     * dtvContentAttributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DTVContentAttributes }
     *     
     */
    public void setDtvContentAttributes(DTVContentAttributes value) {
        this.dtvContentAttributes = value;
    }

    /**
     * vodContentAttributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link VODContentAttributes }
     *     
     */
    public VODContentAttributes getVodContentAttributes() {
        return vodContentAttributes;
    }

    /**
     * vodContentAttributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link VODContentAttributes }
     *     
     */
    public void setVodContentAttributes(VODContentAttributes value) {
        this.vodContentAttributes = value;
    }

}
