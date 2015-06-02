
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageEntityType�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageEntityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DEVICE"/>
 *     &lt;enumeration value="DOMAIN"/>
 *     &lt;enumeration value="GLOBAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageEntityType")
@XmlEnum
public enum MessageEntityType {

    DEVICE,
    DOMAIN,
    GLOBAL;

    public String value() {
        return name();
    }

    public static MessageEntityType fromValue(String v) {
        return valueOf(v);
    }

}
