
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageAttributes complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="MessageAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contentMode" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageContentMode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageAttributes", propOrder = {
    "contentMode"
})
public class MessageAttributes {

    @XmlElement(required = true)
    protected MessageContentMode contentMode;

    /**
     * contentMode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link MessageContentMode }
     *     
     */
    public MessageContentMode getContentMode() {
        return contentMode;
    }

    /**
     * contentMode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageContentMode }
     *     
     */
    public void setContentMode(MessageContentMode value) {
        this.contentMode = value;
    }

}
