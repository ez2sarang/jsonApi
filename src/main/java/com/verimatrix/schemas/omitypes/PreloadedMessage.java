
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PreloadedMessage complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="PreloadedMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="textId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sector" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="messageAttributes" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageAttributes"/>
 *         &lt;element name="messageText" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageText"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreloadedMessage", propOrder = {
    "textId",
    "sector",
    "messageAttributes",
    "messageText"
})
public class PreloadedMessage {

    protected int textId;
    protected int sector;
    @XmlElement(required = true)
    protected MessageAttributes messageAttributes;
    @XmlElement(required = true)
    protected MessageText messageText;

    /**
     * textId �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getTextId() {
        return textId;
    }

    /**
     * textId �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setTextId(int value) {
        this.textId = value;
    }

    /**
     * sector �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getSector() {
        return sector;
    }

    /**
     * sector �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setSector(int value) {
        this.sector = value;
    }

    /**
     * messageAttributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link MessageAttributes }
     *     
     */
    public MessageAttributes getMessageAttributes() {
        return messageAttributes;
    }

    /**
     * messageAttributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageAttributes }
     *     
     */
    public void setMessageAttributes(MessageAttributes value) {
        this.messageAttributes = value;
    }

    /**
     * messageText �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link MessageText }
     *     
     */
    public MessageText getMessageText() {
        return messageText;
    }

    /**
     * messageText �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageText }
     *     
     */
    public void setMessageText(MessageText value) {
        this.messageText = value;
    }

}
