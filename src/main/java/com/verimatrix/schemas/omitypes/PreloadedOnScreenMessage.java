
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PreloadedOnScreenMessage complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="PreloadedOnScreenMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="textId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="displayDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="messageHandle" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageHandle"/>
 *         &lt;element name="messageAttributes" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageAttributes"/>
 *         &lt;element name="messageQuadrant" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageQuadrant"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreloadedOnScreenMessage", propOrder = {
    "textId",
    "displayDuration",
    "messageHandle",
    "messageAttributes",
    "messageQuadrant"
})
public class PreloadedOnScreenMessage {

    protected int textId;
    protected int displayDuration;
    @XmlElement(required = true)
    protected MessageHandle messageHandle;
    @XmlElement(required = true)
    protected MessageAttributes messageAttributes;
    @XmlElement(required = true)
    protected MessageQuadrant messageQuadrant;

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
     * displayDuration 속성의 값을 가져옵니다.
     * 
     */
    public int getDisplayDuration() {
        return displayDuration;
    }

    /**
     * displayDuration 속성의 값을 설정합니다.
     * 
     */
    public void setDisplayDuration(int value) {
        this.displayDuration = value;
    }

    /**
     * messageHandle 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageHandle }
     *     
     */
    public MessageHandle getMessageHandle() {
        return messageHandle;
    }

    /**
     * messageHandle 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHandle }
     *     
     */
    public void setMessageHandle(MessageHandle value) {
        this.messageHandle = value;
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
     * messageQuadrant 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageQuadrant }
     *     
     */
    public MessageQuadrant getMessageQuadrant() {
        return messageQuadrant;
    }

    /**
     * messageQuadrant 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageQuadrant }
     *     
     */
    public void setMessageQuadrant(MessageQuadrant value) {
        this.messageQuadrant = value;
    }

}
