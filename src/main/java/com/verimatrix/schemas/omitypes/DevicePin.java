
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DevicePin complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * index 속성의 값을 가져옵니다.
     * 
     */
    public int getIndex() {
        return index;
    }

    /**
     * index 속성의 값을 설정합니다.
     * 
     */
    public void setIndex(int value) {
        this.index = value;
    }

    /**
     * value 속성의 값을 가져옵니다.
     * 
     */
    public int getValue() {
        return value;
    }

    /**
     * value 속성의 값을 설정합니다.
     * 
     */
    public void setValue(int value) {
        this.value = value;
    }

}
