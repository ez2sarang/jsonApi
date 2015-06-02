
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SiteEntityType에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="SiteEntityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RETAILER"/>
 *     &lt;enumeration value="WHOLESALER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SiteEntityType")
@XmlEnum
public enum SiteEntityType {

    RETAILER,
    WHOLESALER;

    public String value() {
        return name();
    }

    public static SiteEntityType fromValue(String v) {
        return valueOf(v);
    }

}
