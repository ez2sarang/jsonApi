
package com.verimatrix.schemas.omitypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DeviceData complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="DeviceData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entitlement" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}Entitlement"/>
 *         &lt;element name="events" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}Event" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceData", propOrder = {
    "entitlement",
    "events"
})
public class DeviceData {

    @XmlElement(required = true)
    protected Entitlement entitlement;
    protected List<Event> events;

    /**
     * entitlement 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Entitlement }
     *     
     */
    public Entitlement getEntitlement() {
        return entitlement;
    }

    /**
     * entitlement 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Entitlement }
     *     
     */
    public void setEntitlement(Entitlement value) {
        this.entitlement = value;
    }

    /**
     * Gets the value of the events property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the events property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Event }
     * 
     * 
     */
    public List<Event> getEvents() {
        if (events == null) {
            events = new ArrayList<Event>();
        }
        return this.events;
    }

}
