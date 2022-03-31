package KotikiTest;

import dao.KotikiDao;
import dao.KotikiFriendsDao;
import dao.OwnersDao;
import entity.Colors;
import entity.KotikiEntity;
import entity.KotikiFriendsEntity;
import entity.OwnersEntity;
import kotiki_java.service.KotikiFriendsService;
import kotiki_java.service.KotikiService;
import kotiki_java.service.OwnerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Calendar;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KotikiTest {

    @InjectMocks
    private KotikiService kotikiService;

    @InjectMocks
    private OwnerService ownerService;

    @InjectMocks
    private KotikiFriendsService kotikiFriendsService;

    @Mock
    private KotikiDao kotikiDao;

    @Mock
    private OwnersDao ownerDao;

    @Mock
    private KotikiFriendsDao kotikiFriendsDao;

    @Test
    public void getCat() {
        KotikiEntity cat1 = new KotikiEntity("cat", "breed", Colors.Rainbow, new Date(Calendar.getInstance().getTime().getTime()), 1);

        when(kotikiDao.getById(1)).thenReturn(cat1);
        KotikiEntity cat2 = kotikiService.getById(1);
        verify(kotikiDao).getById(Mockito.anyInt());

        Assert.assertEquals(cat1, cat2);

    }

    @Test
    public void getOwner() {
        OwnersEntity owner1 = new OwnersEntity(new Date(Calendar.getInstance().getTime().getTime()), "bbb");
        when(ownerDao.getById(1)).thenReturn(owner1);
        OwnersEntity owner2 = ownerService.getById(1);
        verify(ownerDao).getById(Mockito.anyInt());

        Assert.assertEquals(owner1, owner2);
    }
@Test
public void addCat(){
    OwnersEntity owner = mock(OwnersEntity.class);
    ownerService.save(owner);
    when(ownerDao.getById(1)).thenReturn(owner);

    when(owner.getId()).thenReturn(1);

    KotikiEntity cat = new KotikiEntity("cat", "breed", Colors.Rainbow, new Date(Calendar.getInstance().getTime().getTime()), owner.getId());

    kotikiService.save(cat);
    when(kotikiDao.getById(1)).thenReturn(cat);

    verify(kotikiDao, times(1)).redeem(cat);

    //Правильному ли хозяину принадлежит кот.
    Assert.assertEquals(owner, ownerService.getById(kotikiService.getById(1).getOwnerId()));
}

    @Test
    public void getAllCatFriends() {
        KotikiEntity cat = mock(KotikiEntity.class);

        when(kotikiDao.getById(1)).thenReturn(cat);
        kotikiService.GetAllFriends(1);

        verify(kotikiService.getById(1), times(1)).getAllFriends();
    }

    @Test
    public void addCatRelationship(){
        KotikiEntity cat = new KotikiEntity("cat", "breed", Colors.Rainbow, new Date(Calendar.getInstance().getTime().getTime()), 1);
        KotikiEntity catFriend = new KotikiEntity("cat", "breed", Colors.Rainbow, new Date(Calendar.getInstance().getTime().getTime()), 1);
        kotikiService.save(cat);
        kotikiService.save(catFriend);

        when(kotikiDao.getById(1)).thenReturn(cat);
        when(kotikiDao.getById(2)).thenReturn(catFriend);

        kotikiService.addRelationship(1, 2);

        verify(kotikiFriendsDao, times(2)).redeem(any(KotikiFriendsEntity.class));

    }

}
