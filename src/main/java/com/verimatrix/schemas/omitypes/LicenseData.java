
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>LicenseData complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="LicenseData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lowerBounds" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="upperBounds" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="limit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="activeCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LicenseData", propOrder = {
    "lowerBounds",
    "upperBounds",
    "limit",
    "activeCount"
})
public class LicenseData {

    protected int lowerBounds;
    protected int upperBounds;
    protected int limit;
    protected int activeCount;

    /**
     * lowerBounds 속성의 값을 가져옵니다.
     * 
     */
    public int getLowerBounds() {
        return lowerBounds;
    }

    /**
     * lowerBounds 속성의 값을 설정합니다.
     * 
     */
    public void setLowerBounds(int value) {
        this.lowerBounds = value;
    }

    /**
     * upperBounds 속성의 값을 가져옵니다.
     * 
     */
    public int getUpperBounds() {
        return upperBounds;
    }

    /**
     * upperBounds 속성의 값을 설정합니다.
     * 
     */
    public void setUpperBounds(int value) {
        this.upperBounds = value;
    }

    /**
     * limit 속성의 값을 가져옵니다.
     * 
     */
    public int getLimit() {
        return limit;
    }

    /**
     * limit 속성의 값을 설정합니다.
     * 
     */
    public void setLimit(int value) {
        this.limit = value;
    }

    /**
     * activeCount 속성의 값을 가져옵니다.
     * 
     */
    public int getActiveCount() {
        return activeCount;
    }

    /**
     * activeCount 속성의 값을 설정합니다.
     * 
     */
    public void setActiveCount(int value) {
        this.activeCount = value;
    }

}
