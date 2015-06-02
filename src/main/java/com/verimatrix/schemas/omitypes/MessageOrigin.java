
package com.verimatrix.schemas.omitypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageOrigin�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageOrigin">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ORIGL"/>
 *     &lt;enumeration value="ORIGR"/>
 *     &lt;enumeration value="ORIGTOP"/>
 *     &lt;enumeration value="ORIGDWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageOrigin")
@XmlEnum
public enum MessageOrigin {

    ORIGL,
    ORIGR,
    ORIGTOP,
    ORIGDWN;

    public String value() {
        return name();
    }

    public static MessageOrigin fromValue(String v) {
        return valueOf(v);
    }

}
