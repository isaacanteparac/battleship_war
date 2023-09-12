package com.iac.shipwar.data;

import com.iac.shipwar.data.enums.ShipSize;

public record ShipCharacteristic(
        ShipSize name,
        String screenName,
        String color,
        int size,
        int number_
        ) {
}
