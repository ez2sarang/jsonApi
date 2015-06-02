
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>LicenseData complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * lowerBounds �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getLowerBounds() {
        return lowerBounds;
    }

    /**
     * lowerBounds �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setLowerBounds(int value) {
        this.lowerBounds = value;
    }

    /**
     * upperBounds �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getUpperBounds() {
        return upperBounds;
    }

    /**
     * upperBounds �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setUpperBounds(int value) {
        this.upperBounds = value;
    }

    /**
     * limit �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getLimit() {
        return limit;
    }

    /**
     * limit �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setLimit(int value) {
        this.limit = value;
    }

    /**
     * activeCount �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getActiveCount() {
        return activeCount;
    }

    /**
     * activeCount �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setActiveCount(int value) {
        this.activeCount = value;
    }

}
