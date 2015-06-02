
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkType에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="NetworkType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IPTV"/>
 *     &lt;enumeration value="OTT"/>
 *     &lt;enumeration value="DVB"/>
 *     &lt;enumeration value="PLAYREADY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NetworkType")
@XmlEnum
public enum NetworkType {

    IPTV,
    OTT,
    DVB,
    PLAYREADY;

    public String value() {
        return name();
    }

    public static NetworkType fromValue(String v) {
        return valueOf(v);
    }

}
