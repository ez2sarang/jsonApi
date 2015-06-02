
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EncryptionAttributes complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="EncryptionAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="encryptionMode" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}EncryptionMode"/>
 *         &lt;element name="pcrCorrection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="keyMutationInterval" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}KeyInterval"/>
 *         &lt;element name="keyInsertionInterval" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}KeyInterval"/>
 *         &lt;element name="videoMark" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="controlWordPathProtection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="videoEncryptionLevel" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}EncryptionPercent"/>
 *         &lt;element name="audioEncryptionLevel" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}EncryptionPercent"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptionAttributes", propOrder = {
    "description",
    "encryptionMode",
    "pcrCorrection",
    "keyMutationInterval",
    "keyInsertionInterval",
    "videoMark",
    "controlWordPathProtection",
    "videoEncryptionLevel",
    "audioEncryptionLevel"
})
public class EncryptionAttributes {

    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected EncryptionMode encryptionMode;
    protected boolean pcrCorrection;
    protected int keyMutationInterval;
    protected int keyInsertionInterval;
    protected boolean videoMark;
    protected boolean controlWordPathProtection;
    protected int videoEncryptionLevel;
    protected int audioEncryptionLevel;

    /**
     * description �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * description �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * encryptionMode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionMode }
     *     
     */
    public EncryptionMode getEncryptionMode() {
        return encryptionMode;
    }

    /**
     * encryptionMode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionMode }
     *     
     */
    public void setEncryptionMode(EncryptionMode value) {
        this.encryptionMode = value;
    }

    /**
     * pcrCorrection �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isPcrCorrection() {
        return pcrCorrection;
    }

    /**
     * pcrCorrection �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setPcrCorrection(boolean value) {
        this.pcrCorrection = value;
    }

    /**
     * keyMutationInterval �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getKeyMutationInterval() {
        return keyMutationInterval;
    }

    /**
     * keyMutationInterval �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setKeyMutationInterval(int value) {
        this.keyMutationInterval = value;
    }

    /**
     * keyInsertionInterval �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getKeyInsertionInterval() {
        return keyInsertionInterval;
    }

    /**
     * keyInsertionInterval �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setKeyInsertionInterval(int value) {
        this.keyInsertionInterval = value;
    }

    /**
     * videoMark �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isVideoMark() {
        return videoMark;
    }

    /**
     * videoMark �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setVideoMark(boolean value) {
        this.videoMark = value;
    }

    /**
     * controlWordPathProtection �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isControlWordPathProtection() {
        return controlWordPathProtection;
    }

    /**
     * controlWordPathProtection �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setControlWordPathProtection(boolean value) {
        this.controlWordPathProtection = value;
    }

    /**
     * videoEncryptionLevel �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getVideoEncryptionLevel() {
        return videoEncryptionLevel;
    }

    /**
     * videoEncryptionLevel �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setVideoEncryptionLevel(int value) {
        this.videoEncryptionLevel = value;
    }

    /**
     * audioEncryptionLevel �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getAudioEncryptionLevel() {
        return audioEncryptionLevel;
    }

    /**
     * audioEncryptionLevel �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setAudioEncryptionLevel(int value) {
        this.audioEncryptionLevel = value;
    }

}
