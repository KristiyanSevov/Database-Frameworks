package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.models.dto.DistrictsJSONImportDto;
import org.softuni.mostwanted.models.entities.District;
import org.softuni.mostwanted.models.entities.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {
    private final ModelParser mapper;
    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;

    @Autowired
    public DistrictServiceImpl(ModelParser mapper,
                               DistrictRepository districtRepository,
                               TownRepository townRepository) {
        this.mapper = mapper;
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void create(DistrictsJSONImportDto model) {
        if (!ValidationUtil.isValid(model)){
            throw new IllegalArgumentException();
        }
        Town town = townRepository.findByName(model.getTownName());
        District district = districtRepository.findByName(model.getName());
        if (district != null){
            throw new IllegalStateException("duplicate data");
        }
        district = mapper.convert(model, District.class);
        district.setTown(town);
        districtRepository.save(district);
    }
}
