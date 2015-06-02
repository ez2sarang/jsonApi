
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OSDAttributes complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * osdActive �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isOsdActive() {
        return osdActive;
    }

    /**
     * osdActive �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setOsdActive(boolean value) {
        this.osdActive = value;
    }

    /**
     * osdDuration �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getOsdDuration() {
        return osdDuration;
    }

    /**
     * osdDuration �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setOsdDuration(int value) {
        this.osdDuration = value;
    }

    /**
     * osdType �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getOsdType() {
        return osdType;
    }

    /**
     * osdType �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setOsdType(int value) {
        this.osdType = value;
    }

    /**
     * osdLocation �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getOsdLocation() {
        return osdLocation;
    }

    /**
     * osdLocation �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setOsdLocation(int value) {
        this.osdLocation = value;
    }

}
