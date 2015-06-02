
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>VODEncryptionStatus complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="VODEncryptionStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="statusText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="percentComplete" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VODEncryptionStatus", propOrder = {
    "statusCode",
    "statusText",
    "percentComplete"
})
public class VODEncryptionStatus {

    protected int statusCode;
    @XmlElement(required = true)
    protected String statusText;
    protected int percentComplete;

    /**
     * statusCode �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * statusCode �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setStatusCode(int value) {
        this.statusCode = value;
    }

    /**
     * statusText �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * statusText �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusText(String value) {
        this.statusText = value;
    }

    /**
     * percentComplete �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getPercentComplete() {
        return percentComplete;
    }

    /**
     * percentComplete �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setPercentComplete(int value) {
        this.percentComplete = value;
    }

}
