package com.fiveguys.koguma.service.common;

import com.fiveguys.koguma.data.dto.LocationDTO;
import com.fiveguys.koguma.data.entity.Location;

import java.util.List;

public interface LocationService {

    List<LocationDTO> listLocation(Long id);
    Location addLocation(LocationDTO locationDTO);

    void deleteLocation(Long id);
    void updateSearchRange(Long id,int range);
    LocationDTO addShareLocation();
    //LocationFilter();
}
