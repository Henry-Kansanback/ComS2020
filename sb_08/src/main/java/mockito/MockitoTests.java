package mockito;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.proj.cs309.user.UsersService;
import com.proj.cs309.user.SignUpsService;
import com.proj.cs309.user.EventsService;
import com.proj.cs309.user.Users;
import com.proj.cs309.user.SignUps;
import com.proj.cs309.user.Events;
import com.proj.cs309.user.UserController;
import com.proj.cs309.user.UserRepo;
import com.proj.cs309.user.SignedRepo;
import com.proj.cs309.user.Coupons;
import com.proj.cs309.user.CouponsService;
import com.proj.cs309.user.CouponsRepo;
import com.proj.cs309.user.EventRepo;




public class MockitoTests 
{
	@InjectMocks
	UsersService UserService;
	
	@InjectMocks
	SignUpsService SignUpsService;
	
	@InjectMocks
	EventsService EventsService;
	
	@InjectMocks
	CouponsService CouponsService;

	@Mock
	CouponsRepo CouponsRepo;

	@Mock
	UserRepo repo;
	
	@Mock
	SignedRepo signedRepo;
	
	@Mock
	EventRepo eventRepo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * Authored By Zack Larson
	 */
	@Test
	public void businessUserInputTest() {
	
	  	//Input mock
	  	Users mockUser = Mockito.mock(Users.class);
		
		  when(repo.findByUsername("jDoe")).thenReturn(mockUser);
		  when(mockUser.getBusinessQ1()).thenReturn("Answer1");
		  when(mockUser.getBusinessQ2()).thenReturn("Answer2");
		  when(mockUser.getBusinessQ3()).thenReturn("Answer3");
		  when(mockUser.getBusinessQ4()).thenReturn("Answer4");
		  when(mockUser.getBusinessQ5()).thenReturn("Answer5");
		  when(repo.save(mockUser)).thenReturn(mockUser);
		  assertEquals("Success",UserService.bq1("jDoe", "Answer1"));
		  assertEquals("Success",UserService.bq2("jDoe", "Answer2"));
		  assertEquals("Success",UserService.bq3("jDoe", "Answer3"));
		  assertEquals("Success",UserService.bq4("jDoe", "Answer4"));
		  assertEquals("Success",UserService.bq5("jDoe", "Answer5"));
	}

	/*
	 * Authored By Zack Larson
	 */
	@Test
	public void eventPlannerUserInputTest() {
	
	  	Users mockUser = Mockito.mock(Users.class);
		  when(repo.findByUsername("Zack")).thenReturn(mockUser);
		  when(mockUser.getEventPlannerQ1()).thenReturn("Answer1");
		  when(mockUser.getEventPlannerQ2()).thenReturn("Answer2");
		  when(mockUser.getEventPlannerQ3()).thenReturn("Answer3");
		  when(mockUser.getEventPlannerQ4()).thenReturn("Answer4");
		  when(mockUser.getEventPlannerQ5()).thenReturn("Answer5");
		  when(mockUser.getEventPlannerQ6()).thenReturn("Answer6");
		  when(mockUser.getEventPlannerQ7()).thenReturn("Answer7");
		  when(mockUser.getEventPlannerQ8()).thenReturn("Answer8");
		  when(mockUser.getEventPlannerQ9()).thenReturn("Answer9");
		  when(mockUser.getEventPlannerQ10()).thenReturn("Answer10");
		  when(mockUser.getEventPlannerQ11()).thenReturn("Answer11");
		  when(repo.save(mockUser)).thenReturn(mockUser);
		  assertEquals("Success",UserService.epq1("Zack", "Answer1"));
		  assertEquals("Success",UserService.epq2("Zack", "Answer2"));
		  assertEquals("Success",UserService.epq3("Zack", "Answer3"));
		  assertEquals("Success",UserService.epq4("Zack", "Answer4"));
		  assertEquals("Success",UserService.epq5("Zack", "Answer5"));
		  assertEquals("Success",UserService.epq6("Zack", "Answer6"));
		  assertEquals("Success",UserService.epq7("Zack", "Answer7"));
		  assertEquals("Success",UserService.epq8("Zack", "Answer8"));
		  assertEquals("Success",UserService.epq9("Zack", "Answer9"));
		  assertEquals("Success",UserService.epq10("Zack", "Answer10"));
		  assertEquals("Success",UserService.epq11("Zack", "Answer11"));
	}
	
	/*
	 * Authored By Zack Larson
	 */
	@Test
	public void getBusinessUserInfoTest() {
		List<Users> l = new ArrayList<Users>();
		Users u = new Users("jDoe", "123456", "Des Moines","Iowa", "Event Planner", "Answer1","Answer2",
				"Answer3","Answer4","Answer5");
		Users u2 = new Users("jDoe", "123456", "Des Moines","Iowa", "Business", "Answer1","Answer2",
				"Answer3","Answer4","Answer5");
		l.add(u);
		l.add(u2);
	    
		  when(UserService.getAllUsers()).thenReturn(l);

	  	assertEquals(2, l.size());
	  	List<List<String>> BusinessInfoList = new ArrayList<List<String>>();
	  	List<String> businessInfo = new ArrayList<String>();
		  for(Users user:UserService.getAllUsers())
		  {
			  if(user.getTypeOfUser() != null && user.getTypeOfUser().equals("Business")) {
			  
			  businessInfo.add(user.getBusinessQ1());
			  businessInfo.add(user.getBusinessQ2());
			  businessInfo.add(user.getBusinessQ3());
			  businessInfo.add(user.getBusinessQ4());
			  businessInfo.add(user.getBusinessQ5());
			  BusinessInfoList.add(businessInfo);
			  }
		  }
		  
		assertEquals(1,BusinessInfoList.size());
		  
		assertEquals(businessInfo.get(0), u2.getBusinessQ1());
		assertEquals(businessInfo.get(1), u2.getBusinessQ2());
		assertEquals(businessInfo.get(2), u2.getBusinessQ3());
		assertEquals(businessInfo.get(3), u2.getBusinessQ4());
		assertEquals(businessInfo.get(4), u2.getBusinessQ5());
	}
	
	/*
	 * Authored By Zack Larson
	 */
	@Test
	public void loginTest() {
		Users u = Mockito.mock(Users.class);
		
		  when(repo.findByUsername("jDoe")).thenReturn(u);
		  when(u.getUsername()).thenReturn("jDoe");
		  when(u.getPassword()).thenReturn("123456");
		  when(u.getId()).thenReturn(2555);
	  	
		  Users loginAnswer = UserService.login(u);
	  	
	  	assertEquals(u,loginAnswer);
	}
	
	/*
	 * Authored By Eric Reusch
	 */
	@Test
	public void eventsCanIncrementSigned() {
	  	Events e = new Events();
	  	e.setId(1);
	  	assertEquals((Integer)0,  e.getSigned());
	  	e.incrementSigned();
	  	assertEquals((Integer)1, e.getSigned());
	}
	
	/*
	 * Authored By Eric Reusch
	 */
	@Test
	public void getSignUpInfo() {
		SignUps s = new SignUps(1, 4, 10);
		assertEquals((Integer)1, s.getEventId());
		assertEquals((Integer)4, s.getBusinessId());
		assertEquals((Integer)10, s.getSpotsTotal());
		
		s.setBusinessId(3);
		s.setEventId(2);
		s.setSpotsTotal(7);
		
		assertEquals((Integer)2, s.getEventId());
		assertEquals((Integer)3, s.getBusinessId());
		assertEquals((Integer)7, s.getSpotsTotal());
	}
	
	/*
	 * Authored By Eric Reusch
	 */
	@Test
	public void getEventinfo() {
		Events e = new Events("Fireworks", "Fireworks in LZ", "Lake Zurich", "Illinois", "10/10/10", 3);
		assertEquals("Fireworks", e.getEventName());
		assertEquals("Fireworks in LZ", e.getEventDescription());
		assertEquals("Lake Zurich", e.getCity());
		assertEquals("Illinois", e.getState());
		assertEquals("10/10/10", e.getEventTime());
		
		e.setEventDescription("Fireworks later in the night!");
		e.setEventTime("10/12/10");
		
		assertEquals("Fireworks later in the night!", e.getEventDescription());
		assertEquals("10/12/10", e.getEventTime());
	}
	
	/**
	 * Authored by Eric Reusch
	 */
	@Test
	public void getEventsByTest() {
		List<Events> l = new ArrayList<>();
		List<Integer> ret = new ArrayList<>();
		Events a = new Events(1);
		Events b = new Events(3);
		Events c = new Events(1);
		ret.add(a.getId());
		ret.add(c.getId());
		l.add(a);
		l.add(b);
		l.add(c);
		
		when(eventRepo.findAll()).thenReturn(l);
		assertEquals(EventsService.getEventsBy(1), ret);
	}
	
	
	
	/*
	 * Authored By Zack Larson
	 */
	@Test
	public void couponTestForAllCoupons() {
	
	  	//Input mock
	  	Coupons mockCoupon = Mockito.mock(Coupons.class);
		
	  	List<Coupons> l = new ArrayList<Coupons>();
		Coupons c = new Coupons(19,20,"Coupon Description testing");
		l.add(c);
		
		  when(mockCoupon.getBusinessUserId()).thenReturn(1);
		  when(mockCoupon.getParkingUserId()).thenReturn(2);
		  when(mockCoupon.getCouponDescription()).thenReturn("Coupon Description testing");
		  when(CouponsRepo.save(mockCoupon)).thenReturn(mockCoupon);
		  when(CouponsRepo.findAll()).thenReturn(l);
		  assertEquals(1,CouponsService.getAllCoupons().size());
		  assertEquals(Integer.valueOf(19),CouponsService.getAllCoupons().get(0).getBusinessUserId());
		  assertEquals(Integer.valueOf(20),CouponsService.getAllCoupons().get(0).getParkingUserId());
		  assertEquals("Coupon Description testing",CouponsService.getAllCoupons().get(0).getCouponDescription());
	}
	
	/*
	 * Authored By Zack Larson
	 */
	@Test
	public void couponTestForCouponsByUser() {
	
		List<Coupons> l = new ArrayList<Coupons>();
		Coupons c = new Coupons(1,2,"This is coupon 1");
		Coupons c2 = new Coupons(3,4,"This is coupon 2");
		Coupons c3 = new Coupons(3,4,"This is coupon 3");
		l.add(c);
		l.add(c2);
		l.add(c3);
	    
		  when(CouponsRepo.findAll()).thenReturn(l);
		  
		  List<Coupons> ret = CouponsService.getListOfCouponsByParkingUser(2);
		  List<Coupons> ret2 = CouponsService.getListOfCouponsByParkingUser(4);
		
		  assertEquals(1,ret.size());
		  assertEquals(2,ret2.size());
		  assertEquals(Integer.valueOf(1),ret.get(0).getBusinessUserId());
		  assertEquals(Integer.valueOf(3),ret2.get(0).getBusinessUserId());
		  assertEquals(Integer.valueOf(3),ret2.get(1).getBusinessUserId());
		  assertEquals(Integer.valueOf(2),ret.get(0).getParkingUserId());
		  assertEquals(Integer.valueOf(4),ret2.get(0).getParkingUserId());
		  assertEquals(Integer.valueOf(4),ret2.get(1).getParkingUserId());
		  assertEquals("This is coupon 1",ret.get(0).getCouponDescription());
		  assertEquals("This is coupon 2",ret2.get(0).getCouponDescription());
		  assertEquals("This is coupon 3",ret2.get(1).getCouponDescription());
	}

}
