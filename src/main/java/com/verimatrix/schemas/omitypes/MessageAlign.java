
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageAlign에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageAlign">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LEFT"/>
 *     &lt;enumeration value="RIGHT"/>
 *     &lt;enumeration value="CENTER"/>
 *     &lt;enumeration value="JUST"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageAlign")
@XmlEnum
public enum MessageAlign {

    LEFT,
    RIGHT,
    CENTER,
    JUST;

    public String value() {
        return name();
    }

    public static MessageAlign fromValue(String v) {
        return valueOf(v);
    }

}
