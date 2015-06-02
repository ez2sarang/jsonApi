
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>VODEncryptionAttributes complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="VODEncryptionAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tag1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tag2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tag3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fileType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="copyControlAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}CopyControlAttributes"/>
 *         &lt;element name="encryptionAttributes" type="{http://www.verimatrix.com/schemas/IPTVtypes.xsd}EncryptionAttributes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VODEncryptionAttributes", propOrder = {
    "name",
    "tag1",
    "tag2",
    "tag3",
    "fileName",
    "fileSize",
    "fileType",
    "targetPath",
    "copyControlAttributes",
    "encryptionAttributes"
})
public class VODEncryptionAttributes {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String tag1;
    @XmlElement(required = true)
    protected String tag2;
    @XmlElement(required = true)
    protected String tag3;
    @XmlElement(required = true)
    protected String fileName;
    @XmlElement(required = true)
    protected String fileSize;
    @XmlElement(required = true)
    protected String fileType;
    @XmlElement(required = true)
    protected String targetPath;
    @XmlElement(required = true)
    protected CopyControlAttributes copyControlAttributes;
    @XmlElement(required = true)
    protected EncryptionAttributes encryptionAttributes;

    /**
     * name �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * name �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * tag1 �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag1() {
        return tag1;
    }

    /**
     * tag1 �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag1(String value) {
        this.tag1 = value;
    }

    /**
     * tag2 �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag2() {
        return tag2;
    }

    /**
     * tag2 �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag2(String value) {
        this.tag2 = value;
    }

    /**
     * tag3 �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag3() {
        return tag3;
    }

    /**
     * tag3 �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag3(String value) {
        this.tag3 = value;
    }

    /**
     * fileName �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * fileName �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * fileSize �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * fileSize �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileSize(String value) {
        this.fileSize = value;
    }

    /**
     * fileType �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * fileType �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileType(String value) {
        this.fileType = value;
    }

    /**
     * targetPath �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetPath() {
        return targetPath;
    }

    /**
     * targetPath �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetPath(String value) {
        this.targetPath = value;
    }

    /**
     * copyControlAttributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link CopyControlAttributes }
     *     
     */
    public CopyControlAttributes getCopyControlAttributes() {
        return copyControlAttributes;
    }

    /**
     * copyControlAttributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link CopyControlAttributes }
     *     
     */
    public void setCopyControlAttributes(CopyControlAttributes value) {
        this.copyControlAttributes = value;
    }

    /**
     * encryptionAttributes �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionAttributes }
     *     
     */
    public EncryptionAttributes getEncryptionAttributes() {
        return encryptionAttributes;
    }

    /**
     * encryptionAttributes �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionAttributes }
     *     
     */
    public void setEncryptionAttributes(EncryptionAttributes value) {
        this.encryptionAttributes = value;
    }

}
