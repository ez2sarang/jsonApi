
package com.verimatrix.schemas.iptvtypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EncryptionMode�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * <p>
 * <pre>
 * &lt;simpleType name="EncryptionMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AES"/>
 *     &lt;enumeration value="RC4"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EncryptionMode")
@XmlEnum
public enum EncryptionMode {

    AES("AES"),
    @XmlEnumValue("RC4")
    RC_4("RC4");
    private final String value;

    EncryptionMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EncryptionMode fromValue(String v) {
        for (EncryptionMode c: EncryptionMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
