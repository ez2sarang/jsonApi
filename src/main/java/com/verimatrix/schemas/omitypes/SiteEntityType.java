
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SiteEntityType�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
