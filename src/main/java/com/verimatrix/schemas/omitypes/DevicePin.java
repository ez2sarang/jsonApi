
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DevicePin complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="DevicePin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="index" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DevicePin", propOrder = {
    "index",
    "value"
})
public class DevicePin {

    protected int index;
    protected int value;

    /**
     * index �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getIndex() {
        return index;
    }

    /**
     * index �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setIndex(int value) {
        this.index = value;
    }

    /**
     * value �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getValue() {
        return value;
    }

    /**
     * value �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setValue(int value) {
        this.value = value;
    }

}
