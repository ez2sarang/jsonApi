
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PreloadedMessage complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * textId 속성의 값을 가져옵니다.
     * 
     */
    public int getTextId() {
        return textId;
    }

    /**
     * textId 속성의 값을 설정합니다.
     * 
     */
    public void setTextId(int value) {
        this.textId = value;
    }

    /**
     * sector 속성의 값을 가져옵니다.
     * 
     */
    public int getSector() {
        return sector;
    }

    /**
     * sector 속성의 값을 설정합니다.
     * 
     */
    public void setSector(int value) {
        this.sector = value;
    }

    /**
     * messageAttributes 속성의 값을 가져옵니다.
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
     * messageAttributes 속성의 값을 설정합니다.
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
     * messageText 속성의 값을 가져옵니다.
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
     * messageText 속성의 값을 설정합니다.
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
