package com.electrolux.task.services;

import com.electrolux.task.dto.ApplianceStatus;
import com.electrolux.task.model.Appliance;
import com.electrolux.task.repository.ApplianceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplianceServiceImplTest {

    @Mock
    private ApplianceRepository applianceRepository;

    @InjectMocks
    private ApplianceServiceImpl applianceService;


    @Test
    void shouldReturnApplianceStatusById() {
        when(applianceRepository.findById(1L)).thenReturn(Optional.of(new Appliance("", "",
                "", ApplianceStatus.OFFLINE.name(), LocalDateTime.now().minusMinutes(3))));

        Optional<Appliance> actual = applianceService.getApplianceStatusById(1L);
        Assertions.assertEquals(ApplianceStatus.OFFLINE.name(), actual.get().getStatus());
    }

    @Test
    void shouldUpdateTheApplianceStatusOffline() {
        when(applianceRepository.findById(1L)).thenReturn(Optional.of(new Appliance("", "",
                "", "", LocalDateTime.now().minusMinutes(3))));

        String actualStatus = applianceService.updateStatus(1L);
        Assertions.assertEquals(ApplianceStatus.OFFLINE.name(), actualStatus);
    }

    @Test
    void shouldUpdateTheApplianceStatusOnline() {

        when(applianceRepository.findById(1L)).thenReturn(Optional.of(new Appliance("", "",
                "", "", LocalDateTime.now().minusMinutes(1))));

        String actualStatus = applianceService.updateStatus(1L);
        Assertions.assertEquals(ApplianceStatus.ONLINE.name(), actualStatus);
    }

    @Test
    void shouldThrowExceptionWhenApplianceNotExist() {
        when(applianceRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalStateException.class, () -> applianceService.updateStatus(1L));
    }
}