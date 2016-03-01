package com.pospa.ptracker.persistence;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.pospa.ptracker.model.Payment;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { Storage.class })
public class StorageTest {

    @Mock
    private static ConcurrentHashMap<String, BigDecimal> storageMap;

    @InjectMocks
    private Storage storage = new Storage();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPersist() {

    }

    @Test
    public void testGetAll() {

    }

    @Test
    public void testGetFound() {
        //given
        String currencyCode = "EUR";
        Payment payment = new Payment(currencyCode, BigDecimal.TEN);
        PowerMockito.when(storageMap.containsKey(currencyCode)).thenReturn(true);
        when(storageMap.get(currencyCode)).thenReturn(payment.getAmount());

        //when
        Optional<Payment> result = storage.get(currencyCode);

        //then
        assertThat(result, is(Optional.of(payment)));
    }

    @Test
    public void testGetEmpty() {
        //given
        String currencyCode = "USD";
        when(storageMap.containsKey(currencyCode)).thenReturn(false);

        //when
        Optional<Payment> result = storage.get(currencyCode);

        //then
        assertThat(result, is(Optional.empty()));
    }
}