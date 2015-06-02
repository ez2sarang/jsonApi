
package com.verimatrix.schemas.omitypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OnScreenMessage complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="OnScreenMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageHandle" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageHandle"/>
 *         &lt;element name="messageAttributes" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageAttributes"/>
 *         &lt;element name="messageText" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageText" maxOccurs="2"/>
 *         &lt;element name="messageDescriptor" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageDescriptor" maxOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OnScreenMessage", propOrder = {
    "messageHandle",
    "messageAttributes",
    "messageText",
    "messageDescriptor"
})
public class OnScreenMessage {

    @XmlElement(required = true)
    protected MessageHandle messageHandle;
    @XmlElement(required = true)
    protected MessageAttributes messageAttributes;
    @XmlElement(required = true)
    protected List<MessageText> messageText;
    @XmlElement(required = true, nillable = true)
    protected List<MessageDescriptor> messageDescriptor;

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
     * Gets the value of the messageText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageText }
     * 
     * 
     */
    public List<MessageText> getMessageText() {
        if (messageText == null) {
            messageText = new ArrayList<MessageText>();
        }
        return this.messageText;
    }

    /**
     * Gets the value of the messageDescriptor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageDescriptor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageDescriptor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageDescriptor }
     * 
     * 
     */
    public List<MessageDescriptor> getMessageDescriptor() {
        if (messageDescriptor == null) {
            messageDescriptor = new ArrayList<MessageDescriptor>();
        }
        return this.messageDescriptor;
    }

}
