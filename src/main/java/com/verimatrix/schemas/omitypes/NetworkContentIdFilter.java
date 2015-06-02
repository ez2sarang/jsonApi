
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkContentIdFilter complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="NetworkContentIdFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attributes" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}NetworkContentIdAttributes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkContentIdFilter", propOrder = {
    "attributes"
})
public class NetworkContentIdFilter {

    protected NetworkContentIdAttributes attributes;

    /**
     * attributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link NetworkContentIdAttributes }
     *     
     */
    public NetworkContentIdAttributes getAttributes() {
        return attributes;
    }

    /**
     * attributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkContentIdAttributes }
     *     
     */
    public void setAttributes(NetworkContentIdAttributes value) {
        this.attributes = value;
    }

}
