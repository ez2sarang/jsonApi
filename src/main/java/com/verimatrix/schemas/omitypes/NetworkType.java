
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkType�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
