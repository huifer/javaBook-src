package org.huifer.rbac.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TerminalEnums {
    PC("PC"),
    IOS("IOS"),
    ANDROID("ANDROID");
    private final String name;
}
