
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageHandle complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="MessageHandle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sector" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="messageId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageHandle", propOrder = {
    "sector",
    "messageId"
})
public class MessageHandle {

    protected int sector;
    protected int messageId;

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
     * messageId 속성의 값을 가져옵니다.
     * 
     */
    public int getMessageId() {
        return messageId;
    }

    /**
     * messageId 속성의 값을 설정합니다.
     * 
     */
    public void setMessageId(int value) {
        this.messageId = value;
    }

}
