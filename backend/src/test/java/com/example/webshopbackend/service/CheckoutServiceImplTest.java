package com.example.webshopbackend.service;

import com.example.webshopbackend.dao.AddressRepository;
import com.example.webshopbackend.dao.CheckoutRepository;
import com.example.webshopbackend.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceImplTest {

    @Mock
    private CheckoutRepository checkoutRepository;

    @Mock
    private AddressRepository addressRepository;
    private CheckoutServiceImpl checkoutServiceImplTest;

    @BeforeEach
    void setup() {
        checkoutServiceImplTest = new CheckoutServiceImpl(
                checkoutRepository,
                addressRepository
        );
    }

    @Test
    void saveCheckout() {
        //given
        Vehicle vehicle = new Vehicle(
                null,
                VehicleType.MOTORCYCLE,
                new BasicInfo(
                        null,
                        "Vespa",
                        1000000,
                        "Vespa",
                        2018
                ),
                new InteriorInfo(
                        null,
                        2,
                        GearType.AUTOMATIC,
                        0,
                        10,
                        "dirty white"
                ),
                new ExteriorInfo(
                        null,
                        100,
                        3.0,
                        0.8,
                        0,
                        "cyan"
                ),
                new EngineInfo(
                        null,
                        EngineType.ELECTRIC,
                        60,
                        FuelType.ELECTRIC_POWER,
                        10.0,
                        WheelDrive.TWO_WHEEL_BACK
                ),
                Arrays.asList(
                        new Review(
                                null,
                                4,
                                "It's really weak, and too slow for me...",
                                "AbdrakaDaniel",
                                new GregorianCalendar(2021, Calendar.JANUARY, 17).getTime()
                        ),
                        new Review(
                                null,
                                9,
                                "I really love it, so smooth!",
                                "SamuraiJack11",
                                new GregorianCalendar(2022, Calendar.AUGUST, 30).getTime()
                        )
                )
        );

        Address address = new Address(
                null,
                "Hungary",
                "zalaegerszeg",
                "2341",
                "Petőfu utca",
                "10."
        );

        Checkout checkout = new Checkout(
                null,
                vehicle,
                address,
                "Marcell",
                "Nagy"
        );

        //when
        checkoutServiceImplTest.saveCheckout(checkout);

        //then
        ArgumentCaptor<Address> addressArgumentCaptor = ArgumentCaptor.forClass(Address.class);
        verify(addressRepository).save(addressArgumentCaptor.capture());

        Address capturedAddress = addressArgumentCaptor.getValue();
        assertThat(capturedAddress).isEqualTo(address);

        ArgumentCaptor<Checkout> checkoutArgumentCaptor = ArgumentCaptor.forClass(Checkout.class);
        verify(checkoutRepository).save(checkoutArgumentCaptor.capture());

        Checkout capturedCheckout = checkoutArgumentCaptor.getValue();
        assertThat(capturedCheckout).isEqualTo(checkout);
    }

    @Test
    void getCheckout() {
        //given
        Long id = 1L;

        //when
        checkoutServiceImplTest.getCheckout(id);

        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(checkoutRepository).findCheckoutById(idArgumentCaptor.capture());

        Long capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void deleteCheckout() {
        //given
        Long id = 1L;

        Vehicle vehicle = new Vehicle(
                null,
                VehicleType.MOTORCYCLE,
                new BasicInfo(
                        null,
                        "Vespa",
                        1000000,
                        "Vespa",
                        2018
                ),
                new InteriorInfo(
                        null,
                        2,
                        GearType.AUTOMATIC,
                        0,
                        10,
                        "dirty white"
                ),
                new ExteriorInfo(
                        null,
                        100,
                        3.0,
                        0.8,
                        0,
                        "cyan"
                ),
                new EngineInfo(
                        null,
                        EngineType.ELECTRIC,
                        60,
                        FuelType.ELECTRIC_POWER,
                        10.0,
                        WheelDrive.TWO_WHEEL_BACK
                ),
                Arrays.asList(
                        new Review(
                                null,
                                4,
                                "It's really weak, and too slow for me...",
                                "AbdrakaDaniel",
                                new GregorianCalendar(2021, Calendar.JANUARY, 17).getTime()
                        ),
                        new Review(
                                null,
                                9,
                                "I really love it, so smooth!",
                                "SamuraiJack11",
                                new GregorianCalendar(2022, Calendar.AUGUST, 30).getTime()
                        )
                )
        );

        Address address = new Address(
                null,
                "Hungary",
                "zalaegerszeg",
                "2341",
                "Petőfu utca",
                "10."
        );

        Checkout checkout = new Checkout(
                null,
                vehicle,
                address,
                "Marcell",
                "Nagy"
        );

        given(checkoutRepository.findCheckoutById(id))
                .willReturn(checkout);

        given(addressRepository.findAddressById(null))
                .willReturn(address);

        //when
        checkoutServiceImplTest.deleteCheckout(id);

        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(checkoutRepository).findCheckoutById(idArgumentCaptor.capture());

        Long capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);

        ArgumentCaptor<Long> addressIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(addressRepository).findAddressById(addressIdArgumentCaptor.capture());

        Long capturedAddressId = addressIdArgumentCaptor.getValue();
        assertThat(capturedAddressId).isEqualTo(address.getId());

        ArgumentCaptor<Address> addressArgumentCaptor = ArgumentCaptor.forClass(Address.class);
        verify(addressRepository).delete(addressArgumentCaptor.capture());

        Address capturedAddress = addressArgumentCaptor.getValue();
        assertThat(capturedAddress).isEqualTo(address);

        ArgumentCaptor<Checkout> checkoutArgumentCaptor = ArgumentCaptor.forClass(Checkout.class);
        verify(checkoutRepository).delete(checkoutArgumentCaptor.capture());

        Checkout capturedCheckout = checkoutArgumentCaptor.getValue();
        assertThat(capturedCheckout).isEqualTo(checkout);
    }
}