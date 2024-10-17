package com.coinsystem.system.mappers;

import com.coinsystem.system.DTO.VantageDTO;
import com.coinsystem.system.model.Vantage;

public class VantageMapper {
    public static Vantage vantageDtoToModel(VantageDTO vantageDTO) {
        return new Vantage(vantageDTO.name(), vantageDTO.description(), vantageDTO.value());
    }

}
