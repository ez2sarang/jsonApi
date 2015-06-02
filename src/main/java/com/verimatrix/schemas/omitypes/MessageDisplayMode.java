
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageDisplayMode에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageDisplayMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FX_NEXTSTB"/>
 *     &lt;enumeration value="RM_NEXTSTB"/>
 *     &lt;enumeration value="FX_NEXTCA"/>
 *     &lt;enumeration value="RM_NEXTCA"/>
 *     &lt;enumeration value="FX_STILLCA"/>
 *     &lt;enumeration value="RM_STILLCA"/>
 *     &lt;enumeration value="FX_IMMED"/>
 *     &lt;enumeration value="RM_IMMED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageDisplayMode")
@XmlEnum
public enum MessageDisplayMode {

    FX_NEXTSTB,
    RM_NEXTSTB,
    FX_NEXTCA,
    RM_NEXTCA,
    FX_STILLCA,
    RM_STILLCA,
    FX_IMMED,
    RM_IMMED;

    public String value() {
        return name();
    }

    public static MessageDisplayMode fromValue(String v) {
        return valueOf(v);
    }

}
