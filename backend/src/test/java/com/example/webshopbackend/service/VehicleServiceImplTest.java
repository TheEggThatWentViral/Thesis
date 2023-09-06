package com.example.webshopbackend.service;

import com.example.webshopbackend.dao.*;
import com.example.webshopbackend.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private BasicInfoRepository basicInfoRepository;

    @Mock
    private InteriorInfoRepository interiorInfoRepository;

    @Mock
    private ExteriorInfoRepository exteriorInfoRepository;

    @Mock
    private EngineInfoRepository engineInfoRepository;
    private VehicleServiceImpl vehicleServiceImplTest;

    @BeforeEach
    void setup() {
        vehicleServiceImplTest = new VehicleServiceImpl(
                vehicleRepository,
                basicInfoRepository,
                interiorInfoRepository,
                exteriorInfoRepository,
                engineInfoRepository

        );
    }

    @Test
    void canSaveVehicle() {
        //given
        Vehicle vehicle = new Vehicle(
                null,
                VehicleType.CAR,
                new BasicInfo(
                        null,
                        "Toyota Corolla",
                        5000000,
                        "Toyota",
                        2014
                ),
                new InteriorInfo(
                        null,
                        5,
                        GearType.MANUAL,
                        5,
                        250,
                        "kaki"
                ),
                new ExteriorInfo(
                        null,
                        1200,
                        6.2,
                        3.5,
                        4,
                        "red"
                ),
                new EngineInfo(
                        null,
                        EngineType.TRADITIONAL,
                        110,
                        FuelType.DIESEL,
                        10.0,
                        WheelDrive.TWO_WHEEL_FRONT
                ),
                Arrays.asList(
                        new Review(
                                null,
                                5,
                                "It's not a luxury car but it is a good deal for the price.",
                                "JoskaBacsi64",
                                new GregorianCalendar(2018, Calendar.MARCH, 20).getTime()
                        ),
                        new Review(
                                null,
                                6,
                                "It's alright, feels good to drive.",
                                "VinDiesel999",
                                new GregorianCalendar(2020, Calendar.NOVEMBER, 3).getTime()
                        )
                )
        );

        //when
        vehicleServiceImplTest.saveVehicle(vehicle);

        //then
        ArgumentCaptor<BasicInfo> basicInfoArgumentCaptor = ArgumentCaptor.forClass(BasicInfo.class);
        verify(basicInfoRepository).save(basicInfoArgumentCaptor.capture());

        ArgumentCaptor<InteriorInfo> interiorInfoArgumentCaptor = ArgumentCaptor.forClass(InteriorInfo.class);
        verify(interiorInfoRepository).save(interiorInfoArgumentCaptor.capture());

        ArgumentCaptor<ExteriorInfo> exteriorInfoArgumentCaptor = ArgumentCaptor.forClass(ExteriorInfo.class);
        verify(exteriorInfoRepository).save(exteriorInfoArgumentCaptor.capture());

        ArgumentCaptor<EngineInfo> engineInfoArgumentCaptor = ArgumentCaptor.forClass(EngineInfo.class);
        verify(engineInfoRepository).save(engineInfoArgumentCaptor.capture());

        ArgumentCaptor<Vehicle> vehicleArgumentCaptor = ArgumentCaptor.forClass(Vehicle.class);
        verify(vehicleRepository).save(vehicleArgumentCaptor.capture());

        BasicInfo capturedBasicInfo = basicInfoArgumentCaptor.getValue();
        assertThat(capturedBasicInfo).isEqualTo(vehicle.getBasicInfo());

        InteriorInfo capturedInteriorInfo = interiorInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(vehicle.getInteriorInfo());

        ExteriorInfo capturedExteriorInfo = exteriorInfoArgumentCaptor.getValue();
        assertThat(capturedExteriorInfo).isEqualTo(vehicle.getExteriorInfo());

        EngineInfo capturedEngineInfo = engineInfoArgumentCaptor.getValue();
        assertThat(capturedEngineInfo).isEqualTo(vehicle.getEngineInfo());

        Vehicle capturedVehicle = vehicleArgumentCaptor.getValue();
        assertThat(capturedVehicle).isEqualTo(vehicle);
    }

    @Test
    void canDeleteVehicle() {
        //given
        Long id = 1L;

        Vehicle vehicle = new Vehicle(
                null,
                VehicleType.CAR,
                new BasicInfo(
                        null,
                        "Toyota Corolla",
                        5000000,
                        "Toyota",
                        2014
                ),
                new InteriorInfo(
                        null,
                        5,
                        GearType.MANUAL,
                        5,
                        250,
                        "kaki"
                ),
                new ExteriorInfo(
                        null,
                        1200,
                        6.2,
                        3.5,
                        4,
                        "red"
                ),
                new EngineInfo(
                        null,
                        EngineType.TRADITIONAL,
                        110,
                        FuelType.DIESEL,
                        10.0,
                        WheelDrive.TWO_WHEEL_FRONT
                ),
                Arrays.asList(
                        new Review(
                                null,
                                5,
                                "It's not a luxury car but it is a good deal for the price.",
                                "JoskaBacsi64",
                                new GregorianCalendar(2018, Calendar.MARCH, 20).getTime()
                        ),
                        new Review(
                                null,
                                6,
                                "It's alright, feels good to drive.",
                                "VinDiesel999",
                                new GregorianCalendar(2020, Calendar.NOVEMBER, 3).getTime()
                        )
                )
        );

        given(vehicleRepository.findVehicleById(id))
                .willReturn(vehicle);

        //when
        vehicleServiceImplTest.deleteVehicle(id);

        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(vehicleRepository).findVehicleById(idArgumentCaptor.capture());

        Long capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);

        ArgumentCaptor<BasicInfo> basicInfoArgumentCaptor = ArgumentCaptor.forClass(BasicInfo.class);
        verify(basicInfoRepository).delete(basicInfoArgumentCaptor.capture());

        ArgumentCaptor<InteriorInfo> interiorInfoArgumentCaptor = ArgumentCaptor.forClass(InteriorInfo.class);
        verify(interiorInfoRepository).delete(interiorInfoArgumentCaptor.capture());

        ArgumentCaptor<ExteriorInfo> exteriorInfoArgumentCaptor = ArgumentCaptor.forClass(ExteriorInfo.class);
        verify(exteriorInfoRepository).delete(exteriorInfoArgumentCaptor.capture());

        ArgumentCaptor<EngineInfo> engineInfoArgumentCaptor = ArgumentCaptor.forClass(EngineInfo.class);
        verify(engineInfoRepository).delete(engineInfoArgumentCaptor.capture());

        ArgumentCaptor<Vehicle> vehicleArgumentCaptor = ArgumentCaptor.forClass(Vehicle.class);
        verify(vehicleRepository).delete(vehicleArgumentCaptor.capture());

        BasicInfo capturedBasicInfo = basicInfoArgumentCaptor.getValue();
        assertThat(capturedBasicInfo).isEqualTo(vehicle.getBasicInfo());

        InteriorInfo capturedInteriorInfo = interiorInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(vehicle.getInteriorInfo());

        ExteriorInfo capturedExteriorInfo = exteriorInfoArgumentCaptor.getValue();
        assertThat(capturedExteriorInfo).isEqualTo(vehicle.getExteriorInfo());

        EngineInfo capturedEngineInfo = engineInfoArgumentCaptor.getValue();
        assertThat(capturedEngineInfo).isEqualTo(vehicle.getEngineInfo());

        Vehicle capturedVehicle = vehicleArgumentCaptor.getValue();
        assertThat(capturedVehicle).isEqualTo(vehicle);
    }

    @Test
    void canGetVehicle() {
        //given
        Long id = 1L;

        Vehicle vehicle = new Vehicle(
                null,
                VehicleType.CAR,
                new BasicInfo(
                        null,
                        "Toyota Corolla",
                        5000000,
                        "Toyota",
                        2014
                ),
                new InteriorInfo(
                        null,
                        5,
                        GearType.MANUAL,
                        5,
                        250,
                        "kaki"
                ),
                new ExteriorInfo(
                        null,
                        1200,
                        6.2,
                        3.5,
                        4,
                        "red"
                ),
                new EngineInfo(
                        null,
                        EngineType.TRADITIONAL,
                        110,
                        FuelType.DIESEL,
                        10.0,
                        WheelDrive.TWO_WHEEL_FRONT
                ),
                Arrays.asList(
                        new Review(
                                null,
                                5,
                                "It's not a luxury car but it is a good deal for the price.",
                                "JoskaBacsi64",
                                new GregorianCalendar(2018, Calendar.MARCH, 20).getTime()
                        ),
                        new Review(
                                null,
                                6,
                                "It's alright, feels good to drive.",
                                "VinDiesel999",
                                new GregorianCalendar(2020, Calendar.NOVEMBER, 3).getTime()
                        )
                )
        );

        given(vehicleRepository.findVehicleById(id))
                .willReturn(vehicle);

        //when
        vehicleServiceImplTest.getVehicle(id);

        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(vehicleRepository).findVehicleById(idArgumentCaptor.capture());

        Long capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllVehicle() {
        //when
        vehicleServiceImplTest.getAllVehicle();

        //then
        verify(vehicleRepository).findAll();
    }

    @Test
    void canGetVehicleByType() {
        //given
        String vehicleType = VehicleType.CAR;

        //when
        vehicleServiceImplTest.getVehicleByType(vehicleType);

        //then
        ArgumentCaptor<String> vehicleTypeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(vehicleRepository).findVehicleByVehicleType(vehicleTypeArgumentCaptor.capture());

        String capturedVehicleType = vehicleTypeArgumentCaptor.getValue();
        assertThat(capturedVehicleType).isEqualTo(vehicleType);
    }

    @Test
    void canGetVehicleByName() {
        //given
        String name = "carName";

        List<BasicInfo> basicInfos = new ArrayList<>();

        BasicInfo basicInfo = new BasicInfo(
                null,
                "Toyota Corolla",
                5000000,
                "Toyota",
                2014
        );

        basicInfos.add(basicInfo);

        given(basicInfoRepository.findByNameContains(name))
                .willReturn(basicInfos);

        //when
        vehicleServiceImplTest.getVehicleByName(name);

        //then
        ArgumentCaptor<String> nameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(basicInfoRepository).findByNameContains(nameArgumentCaptor.capture());

        String capturedVehicleType = nameArgumentCaptor.getValue();
        assertThat(capturedVehicleType).isEqualTo(name);

        ArgumentCaptor<BasicInfo> basicInfoArgumentCaptor = ArgumentCaptor.forClass(BasicInfo.class);
        verify(vehicleRepository).findVehicleByBasicInfo(basicInfoArgumentCaptor.capture());

        BasicInfo capturedBasicInfo = basicInfoArgumentCaptor.getValue();
        assertThat(capturedBasicInfo).isEqualTo(basicInfo);
    }

    @Test
    void canGetVehicleByPrice() {
        //given
        Integer min = 2000000;
        Integer max = 8000000;

        List<BasicInfo> basicInfos = new ArrayList<>();

        BasicInfo basicInfo = new BasicInfo(
                null,
                "Toyota Corolla",
                5000000,
                "Toyota",
                2014
        );

        basicInfos.add(basicInfo);

        given(basicInfoRepository.findByPriceBetween(min, max))
                .willReturn(basicInfos);

        //when
        vehicleServiceImplTest.getVehicleByPrice(min, max);

        //then
        ArgumentCaptor<Integer> minArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> maxArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(basicInfoRepository).findByPriceBetween(
                minArgumentCaptor.capture(),
                maxArgumentCaptor.capture()
        );

        Integer capturedMin = minArgumentCaptor.getValue();
        assertThat(capturedMin).isEqualTo(min);

        Integer capturedMax = maxArgumentCaptor.getValue();
        assertThat(capturedMax).isEqualTo(max);

        ArgumentCaptor<BasicInfo> basicInfoArgumentCaptor = ArgumentCaptor.forClass(BasicInfo.class);
        verify(vehicleRepository).findVehicleByBasicInfo(basicInfoArgumentCaptor.capture());

        BasicInfo capturedBasicInfo = basicInfoArgumentCaptor.getValue();
        assertThat(capturedBasicInfo).isEqualTo(basicInfo);
    }

    @Test
    void canGetVehicleByProducer() {
        //given
        String producer = "producerName";

        List<BasicInfo> basicInfos = new ArrayList<>();

        BasicInfo basicInfo = new BasicInfo(
                null,
                "Toyota Corolla",
                5000000,
                "Toyota",
                2014
        );

        basicInfos.add(basicInfo);

        given(basicInfoRepository.findByProducerContains(producer))
                .willReturn(basicInfos);

        //when
        vehicleServiceImplTest.getVehicleByProducer(producer);

        //then
        ArgumentCaptor<String> producerNameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(basicInfoRepository).findByProducerContains(producerNameArgumentCaptor.capture());

        String capturedProducerName = producerNameArgumentCaptor.getValue();
        assertThat(capturedProducerName).isEqualTo(producer);

        ArgumentCaptor<BasicInfo> basicInfoArgumentCaptor = ArgumentCaptor.forClass(BasicInfo.class);
        verify(vehicleRepository).findVehicleByBasicInfo(basicInfoArgumentCaptor.capture());

        BasicInfo capturedBasicInfo = basicInfoArgumentCaptor.getValue();
        assertThat(capturedBasicInfo).isEqualTo(basicInfo);
    }

    @Test
    void canGetVehicleByYearOfProduction() {
        //given
        Integer min = 2010;
        Integer max = 2016;

        List<BasicInfo> basicInfos = new ArrayList<>();

        BasicInfo basicInfo = new BasicInfo(
                null,
                "Toyota Corolla",
                5000000,
                "Toyota",
                2014
        );

        basicInfos.add(basicInfo);

        given(basicInfoRepository.findByYearOfProductionBetween(min, max))
                .willReturn(basicInfos);

        //when
        vehicleServiceImplTest.getVehicleByYearOfProduction(min, max);

        //then
        ArgumentCaptor<Integer> minArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> maxArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(basicInfoRepository).findByYearOfProductionBetween(
                minArgumentCaptor.capture(),
                maxArgumentCaptor.capture()
        );

        Integer capturedMin = minArgumentCaptor.getValue();
        assertThat(capturedMin).isEqualTo(min);

        Integer capturedMax = maxArgumentCaptor.getValue();
        assertThat(capturedMax).isEqualTo(max);

        ArgumentCaptor<BasicInfo> basicInfoArgumentCaptor = ArgumentCaptor.forClass(BasicInfo.class);
        verify(vehicleRepository).findVehicleByBasicInfo(basicInfoArgumentCaptor.capture());

        BasicInfo capturedBasicInfo = basicInfoArgumentCaptor.getValue();
        assertThat(capturedBasicInfo).isEqualTo(basicInfo);
    }

    @Test
    void canGetVehicleByPassengerCapacity() {
        //given
        Integer capacity = 4;

        List<InteriorInfo> interiorInfos = new ArrayList<>();

        InteriorInfo interiorInfo = new InteriorInfo(
                null,
                3,
                GearType.MANUAL,
                6,
                1500,
                "grey"
        );

        interiorInfos.add(interiorInfo);

        given(interiorInfoRepository.findByPassengerCapacity(capacity))
                .willReturn(interiorInfos);

        //when
        vehicleServiceImplTest.getVehicleByPassengerCapacity(capacity);

        //then
        ArgumentCaptor<Integer> capacityArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(interiorInfoRepository).findByPassengerCapacity(capacityArgumentCaptor.capture());

        Integer capturedCapacity = capacityArgumentCaptor.getValue();
        assertThat(capturedCapacity).isEqualTo(capacity);

        ArgumentCaptor<InteriorInfo> interiorInfoArgumentCaptor = ArgumentCaptor.forClass(InteriorInfo.class);
        verify(vehicleRepository).findVehicleByInteriorInfo(interiorInfoArgumentCaptor.capture());

        InteriorInfo capturedInteriorInfo = interiorInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(interiorInfo);
    }

    @Test
    void canGetVehicleByGearType() {
        //given
        String gearType = GearType.MANUAL;

        List<InteriorInfo> interiorInfos = new ArrayList<>();

        InteriorInfo interiorInfo = new InteriorInfo(
                null,
                3,
                GearType.MANUAL,
                6,
                1500,
                "grey"
        );

        interiorInfos.add(interiorInfo);

        given(interiorInfoRepository.findByGearType(gearType))
                .willReturn(interiorInfos);

        //when
        vehicleServiceImplTest.getVehicleByGearType(gearType);

        //then
        ArgumentCaptor<String> gearTypeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(interiorInfoRepository).findByGearType(gearTypeArgumentCaptor.capture());

        String capturedGearType = gearTypeArgumentCaptor.getValue();
        assertThat(capturedGearType).isEqualTo(gearType);

        ArgumentCaptor<InteriorInfo> interiorInfoArgumentCaptor = ArgumentCaptor.forClass(InteriorInfo.class);
        verify(vehicleRepository).findVehicleByInteriorInfo(interiorInfoArgumentCaptor.capture());

        InteriorInfo capturedInteriorInfo = interiorInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(interiorInfo);
    }

    @Test
    void canGetVehicleByTrunkCapacity() {
        //given
        Integer capacity = 1000;

        List<InteriorInfo> interiorInfos = new ArrayList<>();

        InteriorInfo interiorInfo = new InteriorInfo(
                null,
                3,
                GearType.MANUAL,
                6,
                1500,
                "grey"
        );

        interiorInfos.add(interiorInfo);

        given(interiorInfoRepository.findByTrunkCapacityIsGreaterThan(capacity))
                .willReturn(interiorInfos);

        //when
        vehicleServiceImplTest.getVehicleByTrunkCapacity(capacity);

        //then
        ArgumentCaptor<Integer> gearTypeArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(interiorInfoRepository).findByTrunkCapacityIsGreaterThan(gearTypeArgumentCaptor.capture());

        Integer capturedCapacity = gearTypeArgumentCaptor.getValue();
        assertThat(capturedCapacity).isEqualTo(capacity);

        ArgumentCaptor<InteriorInfo> interiorInfoArgumentCaptor = ArgumentCaptor.forClass(InteriorInfo.class);
        verify(vehicleRepository).findVehicleByInteriorInfo(interiorInfoArgumentCaptor.capture());

        InteriorInfo capturedInteriorInfo = interiorInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(interiorInfo);
    }

    @Test
    void canGetVehicleByInteriorColor() {
        //given
        String color = "blue";

        List<InteriorInfo> interiorInfos = new ArrayList<>();

        InteriorInfo interiorInfo = new InteriorInfo(
                null,
                3,
                GearType.MANUAL,
                6,
                1500,
                "grey"
        );

        interiorInfos.add(interiorInfo);

        given(interiorInfoRepository.findByInteriorColor(color))
                .willReturn(interiorInfos);

        //when
        vehicleServiceImplTest.getVehicleByInteriorColor(color);

        //then
        ArgumentCaptor<String> colorArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(interiorInfoRepository).findByInteriorColor(colorArgumentCaptor.capture());

        String capturedColor = colorArgumentCaptor.getValue();
        assertThat(capturedColor).isEqualTo(color);

        ArgumentCaptor<InteriorInfo> interiorInfoArgumentCaptor = ArgumentCaptor.forClass(InteriorInfo.class);
        verify(vehicleRepository).findVehicleByInteriorInfo(interiorInfoArgumentCaptor.capture());

        InteriorInfo capturedInteriorInfo = interiorInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(interiorInfo);
    }

    @Test
    void canGetVehicleByExteriorColor() {
        //given
        String color = "blue";

        List<ExteriorInfo> exteriorInfos = new ArrayList<>();

        ExteriorInfo exteriorInfo = new ExteriorInfo(
                null,
                100,
                3.0,
                0.8,
                0,
                "cyan"
        );


        exteriorInfos.add(exteriorInfo);

        given(exteriorInfoRepository.findByExteriorColor(color))
                .willReturn(exteriorInfos);

        //when
        vehicleServiceImplTest.getVehicleByExteriorColor(color);

        //then
        ArgumentCaptor<String> colorArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(exteriorInfoRepository).findByExteriorColor(colorArgumentCaptor.capture());

        String capturedColor = colorArgumentCaptor.getValue();
        assertThat(capturedColor).isEqualTo(color);

        ArgumentCaptor<ExteriorInfo> exteriorInfoArgumentCaptor = ArgumentCaptor.forClass(ExteriorInfo.class);
        verify(vehicleRepository).findVehicleByExteriorInfo(exteriorInfoArgumentCaptor.capture());

        ExteriorInfo capturedInteriorInfo = exteriorInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(exteriorInfo);
    }

    @Test
    void canGetVehicleByEngineType() {
        //given
        String engineType = EngineType.TRADITIONAL;

        List<EngineInfo> engineInfos = new ArrayList<>();

        EngineInfo engineInfo = new EngineInfo(
                null,
                EngineType.TRADITIONAL,
                110,
                FuelType.DIESEL,
                10.0,
                WheelDrive.TWO_WHEEL_FRONT
        );


        engineInfos.add(engineInfo);

        given(engineInfoRepository.findByEngineType(engineType))
                .willReturn(engineInfos);

        //when
        vehicleServiceImplTest.getVehicleByEngineType(engineType);

        //then
        ArgumentCaptor<String> engineTypeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(engineInfoRepository).findByEngineType(engineTypeArgumentCaptor.capture());

        String capturedEngineType = engineTypeArgumentCaptor.getValue();
        assertThat(capturedEngineType).isEqualTo(engineType);

        ArgumentCaptor<EngineInfo> engineInfoArgumentCaptor = ArgumentCaptor.forClass(EngineInfo.class);
        verify(vehicleRepository).findVehicleByEngineInfo(engineInfoArgumentCaptor.capture());

        EngineInfo capturedInteriorInfo = engineInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(engineInfo);
    }

    @Test
    void canGetVehicleByFuelType() {
        //given
        String fuelType = FuelType.DIESEL;

        List<EngineInfo> engineInfos = new ArrayList<>();

        EngineInfo engineInfo = new EngineInfo(
                null,
                EngineType.TRADITIONAL,
                110,
                FuelType.DIESEL,
                10.0,
                WheelDrive.TWO_WHEEL_FRONT
        );


        engineInfos.add(engineInfo);

        given(engineInfoRepository.findByFuelType(fuelType))
                .willReturn(engineInfos);

        //when
        vehicleServiceImplTest.getVehicleByFuelType(fuelType);

        //then
        ArgumentCaptor<String> engineTypeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(engineInfoRepository).findByFuelType(engineTypeArgumentCaptor.capture());

        String capturedFuelType= engineTypeArgumentCaptor.getValue();
        assertThat(capturedFuelType).isEqualTo(fuelType);

        ArgumentCaptor<EngineInfo> engineInfoArgumentCaptor = ArgumentCaptor.forClass(EngineInfo.class);
        verify(vehicleRepository).findVehicleByEngineInfo(engineInfoArgumentCaptor.capture());

        EngineInfo capturedInteriorInfo = engineInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(engineInfo);
    }

    @Test
    void getVehicleByWheelDrive() {
        //given
        WheelDrive wheelDrive = WheelDrive.TWO_WHEEL_BACK;

        List<EngineInfo> engineInfos = new ArrayList<>();

        EngineInfo engineInfo = new EngineInfo(
                null,
                EngineType.TRADITIONAL,
                110,
                FuelType.DIESEL,
                10.0,
                WheelDrive.TWO_WHEEL_FRONT
        );


        engineInfos.add(engineInfo);

        given(engineInfoRepository.findByWheelDrive(wheelDrive))
                .willReturn(engineInfos);

        //when
        vehicleServiceImplTest.getVehicleByWheelDrive(wheelDrive);

        //then
        ArgumentCaptor<WheelDrive> engineTypeArgumentCaptor = ArgumentCaptor.forClass(WheelDrive.class);
        verify(engineInfoRepository).findByWheelDrive(engineTypeArgumentCaptor.capture());

        WheelDrive capturedWheelDrive = engineTypeArgumentCaptor.getValue();
        assertThat(capturedWheelDrive).isEqualTo(wheelDrive);

        ArgumentCaptor<EngineInfo> engineInfoArgumentCaptor = ArgumentCaptor.forClass(EngineInfo.class);
        verify(vehicleRepository).findVehicleByEngineInfo(engineInfoArgumentCaptor.capture());

        EngineInfo capturedInteriorInfo = engineInfoArgumentCaptor.getValue();
        assertThat(capturedInteriorInfo).isEqualTo(engineInfo);
    }
}