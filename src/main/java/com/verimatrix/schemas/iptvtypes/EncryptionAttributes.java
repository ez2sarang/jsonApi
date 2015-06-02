
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EncryptionAttributes complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * description 속성의 값을 가져옵니다.
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
     * description 속성의 값을 설정합니다.
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
     * encryptionMode 속성의 값을 가져옵니다.
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
     * encryptionMode 속성의 값을 설정합니다.
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
     * pcrCorrection 속성의 값을 가져옵니다.
     * 
     */
    public boolean isPcrCorrection() {
        return pcrCorrection;
    }

    /**
     * pcrCorrection 속성의 값을 설정합니다.
     * 
     */
    public void setPcrCorrection(boolean value) {
        this.pcrCorrection = value;
    }

    /**
     * keyMutationInterval 속성의 값을 가져옵니다.
     * 
     */
    public int getKeyMutationInterval() {
        return keyMutationInterval;
    }

    /**
     * keyMutationInterval 속성의 값을 설정합니다.
     * 
     */
    public void setKeyMutationInterval(int value) {
        this.keyMutationInterval = value;
    }

    /**
     * keyInsertionInterval 속성의 값을 가져옵니다.
     * 
     */
    public int getKeyInsertionInterval() {
        return keyInsertionInterval;
    }

    /**
     * keyInsertionInterval 속성의 값을 설정합니다.
     * 
     */
    public void setKeyInsertionInterval(int value) {
        this.keyInsertionInterval = value;
    }

    /**
     * videoMark 속성의 값을 가져옵니다.
     * 
     */
    public boolean isVideoMark() {
        return videoMark;
    }

    /**
     * videoMark 속성의 값을 설정합니다.
     * 
     */
    public void setVideoMark(boolean value) {
        this.videoMark = value;
    }

    /**
     * controlWordPathProtection 속성의 값을 가져옵니다.
     * 
     */
    public boolean isControlWordPathProtection() {
        return controlWordPathProtection;
    }

    /**
     * controlWordPathProtection 속성의 값을 설정합니다.
     * 
     */
    public void setControlWordPathProtection(boolean value) {
        this.controlWordPathProtection = value;
    }

    /**
     * videoEncryptionLevel 속성의 값을 가져옵니다.
     * 
     */
    public int getVideoEncryptionLevel() {
        return videoEncryptionLevel;
    }

    /**
     * videoEncryptionLevel 속성의 값을 설정합니다.
     * 
     */
    public void setVideoEncryptionLevel(int value) {
        this.videoEncryptionLevel = value;
    }

    /**
     * audioEncryptionLevel 속성의 값을 가져옵니다.
     * 
     */
    public int getAudioEncryptionLevel() {
        return audioEncryptionLevel;
    }

    /**
     * audioEncryptionLevel 속성의 값을 설정합니다.
     * 
     */
    public void setAudioEncryptionLevel(int value) {
        this.audioEncryptionLevel = value;
    }

}
