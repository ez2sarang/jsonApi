
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageHandle complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * messageId �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getMessageId() {
        return messageId;
    }

    /**
     * messageId �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setMessageId(int value) {
        this.messageId = value;
    }

}
