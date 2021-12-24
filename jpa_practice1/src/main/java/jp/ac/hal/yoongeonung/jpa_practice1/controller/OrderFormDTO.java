package jp.ac.hal.yoongeonung.jpa_practice1.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderFormDTO {
    private Long memberId;
    private Long itemId;
    private int count;
}
