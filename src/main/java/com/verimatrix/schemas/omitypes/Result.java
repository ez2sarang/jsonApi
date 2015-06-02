
package com.verimatrix.schemas.omitypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Result complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="Result">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="resultText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultData" type="{http://www.verimatrix.com/schemas/OMItypes.xsd}ResultData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Result", propOrder = {
    "resultId",
    "resultCode",
    "resultText",
    "resultData"
})
public class Result {

    @XmlElement(required = true)
    protected String resultId;
    protected int resultCode;
    @XmlElement(required = true)
    protected String resultText;
    protected List<ResultData> resultData;

    /**
     * resultId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultId() {
        return resultId;
    }

    /**
     * resultId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultId(String value) {
        this.resultId = value;
    }

    /**
     * resultCode 속성의 값을 가져옵니다.
     * 
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * resultCode 속성의 값을 설정합니다.
     * 
     */
    public void setResultCode(int value) {
        this.resultCode = value;
    }

    /**
     * resultText 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultText() {
        return resultText;
    }

    /**
     * resultText 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultText(String value) {
        this.resultText = value;
    }

    /**
     * Gets the value of the resultData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultData }
     * 
     * 
     */
    public List<ResultData> getResultData() {
        if (resultData == null) {
            resultData = new ArrayList<ResultData>();
        }
        return this.resultData;
    }

}
