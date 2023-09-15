package com.iac.shipwar.models.dataclass;

import java.nio.ByteBuffer;

import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Row;

public record AttackCoordinates(
    Row row,
    Column column) {
 
}
