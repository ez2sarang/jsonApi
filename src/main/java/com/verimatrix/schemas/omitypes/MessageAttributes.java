
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageAttributes complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * contentMode 속성의 값을 가져옵니다.
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
     * contentMode 속성의 값을 설정합니다.
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
