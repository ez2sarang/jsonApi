
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DeviceCommand complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="DeviceCommand">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="command" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceCommand", propOrder = {
    "command"
})
public class DeviceCommand {

    @XmlElement(required = true)
    protected byte[] command;

    /**
     * command 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCommand() {
        return command;
    }

    /**
     * command 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCommand(byte[] value) {
        this.command = value;
    }

}
