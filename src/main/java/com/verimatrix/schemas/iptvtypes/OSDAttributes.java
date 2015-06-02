
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OSDAttributes complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="OSDAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="osdActive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="osdDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="osdType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="osdLocation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OSDAttributes", propOrder = {
    "osdActive",
    "osdDuration",
    "osdType",
    "osdLocation"
})
public class OSDAttributes {

    protected boolean osdActive;
    protected int osdDuration;
    protected int osdType;
    protected int osdLocation;

    /**
     * osdActive 속성의 값을 가져옵니다.
     * 
     */
    public boolean isOsdActive() {
        return osdActive;
    }

    /**
     * osdActive 속성의 값을 설정합니다.
     * 
     */
    public void setOsdActive(boolean value) {
        this.osdActive = value;
    }

    /**
     * osdDuration 속성의 값을 가져옵니다.
     * 
     */
    public int getOsdDuration() {
        return osdDuration;
    }

    /**
     * osdDuration 속성의 값을 설정합니다.
     * 
     */
    public void setOsdDuration(int value) {
        this.osdDuration = value;
    }

    /**
     * osdType 속성의 값을 가져옵니다.
     * 
     */
    public int getOsdType() {
        return osdType;
    }

    /**
     * osdType 속성의 값을 설정합니다.
     * 
     */
    public void setOsdType(int value) {
        this.osdType = value;
    }

    /**
     * osdLocation 속성의 값을 가져옵니다.
     * 
     */
    public int getOsdLocation() {
        return osdLocation;
    }

    /**
     * osdLocation 속성의 값을 설정합니다.
     * 
     */
    public void setOsdLocation(int value) {
        this.osdLocation = value;
    }

}
