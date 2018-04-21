package com.masdefect.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "victim")
@XmlAccessorType(XmlAccessType.FIELD)
public class VictimXMLDto {
    @XmlAttribute
    private String name;

    public VictimXMLDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
