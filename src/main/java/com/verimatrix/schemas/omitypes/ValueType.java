
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ValueType에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="ValueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STRING"/>
 *     &lt;enumeration value="DATETIME"/>
 *     &lt;enumeration value="NUMBER"/>
 *     &lt;enumeration value="FLOAT"/>
 *     &lt;enumeration value="BOOLEAN"/>
 *     &lt;enumeration value="DURATION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ValueType")
@XmlEnum
public enum ValueType {

    STRING,
    DATETIME,
    NUMBER,
    FLOAT,
    BOOLEAN,
    DURATION;

    public String value() {
        return name();
    }

    public static ValueType fromValue(String v) {
        return valueOf(v);
    }

}
