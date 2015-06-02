
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageDescriptor complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * displayMode �Ӽ��� ���� �����ɴϴ�.
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
     * displayMode �Ӽ��� ���� �����մϴ�.
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
     * displayDuration �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getDisplayDuration() {
        return displayDuration;
    }

    /**
     * displayDuration �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setDisplayDuration(int value) {
        this.displayDuration = value;
    }

    /**
     * xOrigin �Ӽ��� ���� �����ɴϴ�.
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
     * xOrigin �Ӽ��� ���� �����մϴ�.
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
     * xPosition �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * xPosition �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setXPosition(int value) {
        this.xPosition = value;
    }

    /**
     * xAnchor �Ӽ��� ���� �����ɴϴ�.
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
     * xAnchor �Ӽ��� ���� �����մϴ�.
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
     * yOrigin �Ӽ��� ���� �����ɴϴ�.
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
     * yOrigin �Ӽ��� ���� �����մϴ�.
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
     * yPosition �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * yPosition �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setYPosition(int value) {
        this.yPosition = value;
    }

    /**
     * yAnchor �Ӽ��� ���� �����ɴϴ�.
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
     * yAnchor �Ӽ��� ���� �����մϴ�.
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
     * textAlign �Ӽ��� ���� �����ɴϴ�.
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
     * textAlign �Ӽ��� ���� �����մϴ�.
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
     * bgColor �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getBgColor() {
        return bgColor;
    }

    /**
     * bgColor �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setBgColor(int value) {
        this.bgColor = value;
    }

    /**
     * alpha �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     * alpha �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setAlpha(int value) {
        this.alpha = value;
    }

}
