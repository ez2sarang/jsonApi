
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageQuadrant에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageQuadrant">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CENTER"/>
 *     &lt;enumeration value="NORTH"/>
 *     &lt;enumeration value="NORTH_EAST"/>
 *     &lt;enumeration value="EAST"/>
 *     &lt;enumeration value="SOUTH_EAST"/>
 *     &lt;enumeration value="SOUTH"/>
 *     &lt;enumeration value="SOUTH_WEST"/>
 *     &lt;enumeration value="WEST"/>
 *     &lt;enumeration value="NORTH_WEST"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageQuadrant")
@XmlEnum
public enum MessageQuadrant {

    CENTER,
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    public String value() {
        return name();
    }

    public static MessageQuadrant fromValue(String v) {
        return valueOf(v);
    }

}
