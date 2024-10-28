package models.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public enum OrderStatusEnum {

    OPEN ( "OPEN"),
    IN_PROGRESS ( "IN_PROGRESS"),
    CLOSED ( "CLOSED"),
    CANCELED ( "CANCELED");

    @Getter
    private final String description;

    public static OrderStatusEnum toEnum(final String description) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(orderStatusEnum -> orderStatusEnum.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid description: " + description));
    }




}
