package ru.itmo.kotikilab3;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import ru.itmo.kotikilab3.controller.CatController;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Assert;

import ru.itmo.kotikilab3.entity.Colors;
import ru.itmo.kotikilab3.entity.KotikiEntity;
import ru.itmo.kotikilab3.entity.KotikiFriendsEntity;
import ru.itmo.kotikilab3.entity.OwnersEntity;
import ru.itmo.kotikilab3.repository.CatRepository;
import ru.itmo.kotikilab3.repository.OwnerRepository;
import ru.itmo.kotikilab3.service.KotikiFriendsService;
import ru.itmo.kotikilab3.service.KotikiService;
import ru.itmo.kotikilab3.service.OwnerService;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Optional;

@SpringBootTest
class KotikiLab3ApplicationTests {

    @Autowired
    private CatController catController;

    @Mock
    private CatRepository catRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private KotikiService kotikiService;

    @InjectMocks
    private OwnerService ownerService;

    @InjectMocks
    private KotikiFriendsService kotikiFriendsService;

    @Before("")
    public void setup() {
        when(catRepository.saveAndFlush(any(KotikiEntity.class))).thenReturn(null);
    }

    @Test
    public void findCat() {
        KotikiEntity cat1 = new KotikiEntity("drug", "cherniy",Colors.RAINBOW, new Date(Calendar.getInstance().getTime().getTime()),null);
        when(catRepository.findById(1)).thenReturn(cat1);

        KotikiEntity cat2 = kotikiService.findById(1);
        verify(catRepository).findById(Mockito.anyInt());

        Assert.assertEquals(cat1, cat2);
    }
    @Test
    void findOwner() {

        OwnersEntity owner1 = new OwnersEntity( new Date(Calendar.getInstance().getTime().getTime()), "owner");
            owner1.setId(20);

        when(ownerRepository.findById(20)).thenReturn(owner1);

            OwnersEntity owner2 = ownerService.findById(20);
        verify(ownerRepository).findById(Mockito.anyInt());

        Assert.assertEquals(owner1, owner2);
        }
    @Test
    public void addCat(){
        OwnersEntity owner = mock(OwnersEntity.class);
        when(ownerRepository.findById(1)).thenReturn(owner);

        when(owner.getId()).thenReturn(1);

        KotikiEntity cat = new KotikiEntity("cat", "breed", Colors.RAINBOW, new Date(Calendar.getInstance().getTime().getTime()), owner.getId());

        when(catRepository.findById(1)).thenReturn(cat);

        Assert.assertEquals(owner, ownerService.findById(kotikiService.findById(1).getOwnerId()));
    }



    }

