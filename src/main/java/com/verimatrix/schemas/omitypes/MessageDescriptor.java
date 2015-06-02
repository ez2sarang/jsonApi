
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageDescriptor complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="MessageDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="displayMode" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageDisplayMode"/>
 *         &lt;element name="displayDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="xOrigin" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageOrigin"/>
 *         &lt;element name="xPosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="xAnchor" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageAnchor"/>
 *         &lt;element name="yOrigin" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageOrigin"/>
 *         &lt;element name="yPosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="yAnchor" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageAnchor"/>
 *         &lt;element name="textAlign" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageAlign"/>
 *         &lt;element name="bgColor" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageColor"/>
 *         &lt;element name="alpha" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}MessageAlpha"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageDescriptor", propOrder = {
    "displayMode",
    "displayDuration",
    "xOrigin",
    "xPosition",
    "xAnchor",
    "yOrigin",
    "yPosition",
    "yAnchor",
    "textAlign",
    "bgColor",
    "alpha"
})
public class MessageDescriptor {

    @XmlElement(required = true)
    protected MessageDisplayMode displayMode;
    protected int displayDuration;
    @XmlElement(required = true)
    protected MessageOrigin xOrigin;
    protected int xPosition;
    @XmlElement(required = true)
    protected MessageAnchor xAnchor;
    @XmlElement(required = true)
    protected MessageOrigin yOrigin;
    protected int yPosition;
    @XmlElement(required = true)
    protected MessageAnchor yAnchor;
    @XmlElement(required = true)
    protected MessageAlign textAlign;
    protected int bgColor;
    protected int alpha;

    /**
     * displayMode 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageDisplayMode }
     *     
     */
    public MessageDisplayMode getDisplayMode() {
        return displayMode;
    }

    /**
     * displayMode 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageDisplayMode }
     *     
     */
    public void setDisplayMode(MessageDisplayMode value) {
        this.displayMode = value;
    }

    /**
     * displayDuration 속성의 값을 가져옵니다.
     * 
     */
    public int getDisplayDuration() {
        return displayDuration;
    }

    /**
     * displayDuration 속성의 값을 설정합니다.
     * 
     */
    public void setDisplayDuration(int value) {
        this.displayDuration = value;
    }

    /**
     * xOrigin 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageOrigin }
     *     
     */
    public MessageOrigin getXOrigin() {
        return xOrigin;
    }

    /**
     * xOrigin 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageOrigin }
     *     
     */
    public void setXOrigin(MessageOrigin value) {
        this.xOrigin = value;
    }

    /**
     * xPosition 속성의 값을 가져옵니다.
     * 
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * xPosition 속성의 값을 설정합니다.
     * 
     */
    public void setXPosition(int value) {
        this.xPosition = value;
    }

    /**
     * xAnchor 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageAnchor }
     *     
     */
    public MessageAnchor getXAnchor() {
        return xAnchor;
    }

    /**
     * xAnchor 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageAnchor }
     *     
     */
    public void setXAnchor(MessageAnchor value) {
        this.xAnchor = value;
    }

    /**
     * yOrigin 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageOrigin }
     *     
     */
    public MessageOrigin getYOrigin() {
        return yOrigin;
    }

    /**
     * yOrigin 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageOrigin }
     *     
     */
    public void setYOrigin(MessageOrigin value) {
        this.yOrigin = value;
    }

    /**
     * yPosition 속성의 값을 가져옵니다.
     * 
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * yPosition 속성의 값을 설정합니다.
     * 
     */
    public void setYPosition(int value) {
        this.yPosition = value;
    }

    /**
     * yAnchor 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageAnchor }
     *     
     */
    public MessageAnchor getYAnchor() {
        return yAnchor;
    }

    /**
     * yAnchor 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageAnchor }
     *     
     */
    public void setYAnchor(MessageAnchor value) {
        this.yAnchor = value;
    }

    /**
     * textAlign 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageAlign }
     *     
     */
    public MessageAlign getTextAlign() {
        return textAlign;
    }

    /**
     * textAlign 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageAlign }
     *     
     */
    public void setTextAlign(MessageAlign value) {
        this.textAlign = value;
    }

    /**
     * bgColor 속성의 값을 가져옵니다.
     * 
     */
    public int getBgColor() {
        return bgColor;
    }

    /**
     * bgColor 속성의 값을 설정합니다.
     * 
     */
    public void setBgColor(int value) {
        this.bgColor = value;
    }

    /**
     * alpha 속성의 값을 가져옵니다.
     * 
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     * alpha 속성의 값을 설정합니다.
     * 
     */
    public void setAlpha(int value) {
        this.alpha = value;
    }

}
