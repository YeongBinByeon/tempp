package com.yeongbin.stock.service;

import com.yeongbin.stock.dto.UserDepositDto;
import com.yeongbin.stock.entity.AccountTransactionDetail;
import com.yeongbin.stock.entity.User;
import com.yeongbin.stock.repository.AccountRepository;
import com.yeongbin.stock.repository.AccountTransactionDetailRepository;
import com.yeongbin.stock.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatisticsServiceTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountTransactionDetailRepository accountTransactionDetailRepository;
    @Autowired
    private StatisticsService statisticsService;


    /*
    ID  	AGE  	NAME  	REGISTERED_DATE
    1	63	Liam	2016-11-05
    2	38	Noah	2017-10-12
    3	19	Oliver	2016-04-01

    ACCOUNT_NUMBER  	USER_ID
    1000-01	11
    1000-02	6
    1000-03	19

    ID  	DEPOSIT_DATE  	DEPOSIT_MONEY  	IS_DEPOSIT_OPERATION  	ACCOUNT_NUMBER
    1	2019-08-26	336700	Y	1000-01
    51	2019-04-11	85900	N	1000-01
    101	2019-04-08	63200	N	1000-01
    151	2019-02-22	61400	Y	1000-01
     */
    public List<AccountTransactionDetail> createAccountTransactionDetailList(){
        return null;
    }

    @Autowired
    DataSource dataSource;

    @BeforeAll
    public void init() {
        try (Connection conn = dataSource.getConnection()) {
            // 자신의 script path 넣어주면 됨
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("/db/h2/data.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @DisplayName("성공 테스트 : 추가 REST API 1 사용자를 입력받아, 사용자의 계좌별 예치금을 출력하시오")
    @Test
    void getAccountsDepositsSuccessTest() {
        List<Map.Entry<String, Long>> responseList = statisticsService.getAccountsDeposits(1L);

        List<Map.Entry<String, Long>> answerList = new ArrayList<>();
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("1000-22", 1507600L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("1000-46", 1181000L));

        Assertions.assertEquals(answerList.get(0).getKey(), responseList.get(0).getKey());
        Assertions.assertEquals(answerList.get(0).getValue(), responseList.get(0).getValue());
        Assertions.assertEquals(answerList.get(1).getKey(), responseList.get(1).getKey());
        Assertions.assertEquals(answerList.get(1).getValue(), responseList.get(1).getValue());
    }

    @DisplayName("실패 테스트 : 추가 REST API 1 사용자를 입력받아, 사용자의 계좌별 예치금을 출력하시오")
    @Test
    void getAccountsDepositsFailTest() {
        List<Map.Entry<String, Long>> responseList = statisticsService.getAccountsDeposits(1L);

        List<Map.Entry<String, Long>> answerList = new ArrayList<>();
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("1000-22", 150760L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("1000-46", 11881000L));

        Assertions.assertEquals(answerList.get(0).getKey(), responseList.get(0).getKey());
        Assertions.assertNotEquals(answerList.get(0).getValue(), responseList.get(0).getValue());
        Assertions.assertEquals(answerList.get(1).getKey(), responseList.get(1).getKey());
        Assertions.assertNotEquals(answerList.get(1).getValue(), responseList.get(1).getValue());
    }

    @Test
    @DisplayName("성공 테스트 : 사용자 나이대 별로, 평균 예치금을 출력하시오")
    void getAgeAverageDepositsSuccessTest() {
        List<Map.Entry<String, Long>> responseList = statisticsService.getAgeAverageDeposits();

        List<Map.Entry<String, Long>> answerList = new ArrayList<>();
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 11~20", 4085416L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 21~30", 491900L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 31~40", 5133450L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 41~50", 8623400L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 51~60", 1191300L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 61~70", 1419075L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 71~80", 3496200L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 81~90", 0L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 91~100", 0L));

        for(int i=0; i<9; i++){
            Assertions.assertEquals(responseList.get(i).getKey(), answerList.get(i).getKey());
            Assertions.assertEquals(responseList.get(i).getValue(), answerList.get(i).getValue());
        }
    }

    @Test
    @DisplayName("실패 테스트 : 사용자 나이대 별로, 평균 예치금을 출력하시오")
    void getAgeAverageDepositsFailTest() {
        List<Map.Entry<String, Long>> responseList = statisticsService.getAgeAverageDeposits();

        List<Map.Entry<String, Long>> answerList = new ArrayList<>();
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 11~20", 485416L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 21~30", 4913900L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 31~40", 51323450L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 41~50", 823400L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 51~60", 11915300L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 61~70", 14191075L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 71~80", 349200L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 81~90", -1L));
        answerList.add(new AbstractMap.SimpleEntry<String,Long>("연령대 91~100", -5L));

        for(int i=0; i<9; i++){
            Assertions.assertEquals(responseList.get(i).getKey(), answerList.get(i).getKey());
            Assertions.assertNotEquals(responseList.get(i).getValue(), answerList.get(i).getValue());
        }

    }

    @Test
    @DisplayName("성공 테스트 : 년도를 입력받아, 해당년도의 예치금 총액을 출력하시오")
    void getYearSumDepositsSuccessTest() {
        Long yearSumDeposits2019 = statisticsService.getYearSumDeposits(2019);
        Long yearSumDeposits2020 = statisticsService.getYearSumDeposits(2020);
        Long yearSumDeposits2021 = statisticsService.getYearSumDeposits(2021);

        Assertions.assertEquals(21619200L, yearSumDeposits2019);
        Assertions.assertEquals(29548900L, yearSumDeposits2020);
        Assertions.assertEquals(14341100L, yearSumDeposits2021);
    }

    @Test
    @DisplayName("실패 테스트 : 년도를 입력받아, 해당년도의 예치금 총액을 출력하시오")
    void getYearSumDepositsFailTest() {
        Long yearSumDeposits2019 = statisticsService.getYearSumDeposits(2019);
        Long yearSumDeposits2020 = statisticsService.getYearSumDeposits(2020);
        Long yearSumDeposits2021 = statisticsService.getYearSumDeposits(2021);

        Assertions.assertNotEquals(2161200L, yearSumDeposits2019);
        Assertions.assertNotEquals(2954890L, yearSumDeposits2020);
        Assertions.assertNotEquals(1434100L, yearSumDeposits2021);
    }


    /*
    http://127.0.0.1:9093/userlist-many-deposit-during-date?start=20190101&end=20201231
    [{"id":6,"name":"James","totalDeposit":2440500},{"id":5,"name":"William","totalDeposit":1429500},
    {"id":14,"name":"Charlotte","totalDeposit":1204400},{"id":13,"name":"Ava","totalDeposit":1176400},
    {"id":20,"name":"Harper","totalDeposit":1168700},{"id":9,"name":"Henry","totalDeposit":945200},
    {"id":3,"name":"Oliver","totalDeposit":859700},{"id":19,"name":"Evelyn","totalDeposit":736900},
    {"id":12,"name":"Emma","totalDeposit":671900},{"id":1,"name":"Liam","totalDeposit":640900},
    {"id":11,"name":"Olivia","totalDeposit":526700},{"id":15,"name":"Sophia","totalDeposit":398300},
    {"id":10,"name":"Alexander","totalDeposit":390500},{"id":17,"name":"Isabella","totalDeposit":361400},
    {"id":2,"name":"Noah","totalDeposit":301100},{"id":18,"name":"Mia","totalDeposit":173300},
    {"id":4,"name":"Elijah","totalDeposit":131100},{"id":16,"name":"Amelia","totalDeposit":29300},
    {"id":8,"name":"Lucas","totalDeposit":-354500},{"id":7,"name":"Benjamin","totalDeposit":-789300}]
     */
    @DisplayName("성공 테스트 : 기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력하시오")
    @Test
    void getUserListManyDepositDuringDateSuccessTest() {
        List<UserDepositDto> userDepositDtoList =
                statisticsService.getUserListManyDepositDuringDate(LocalDate.of(2019,1,1),
                LocalDate.of(2020,12,31));
        Assertions.assertEquals(userDepositDtoList.size(), 20);
        Assertions.assertEquals(userDepositDtoList.get(0).getId(), 6L);
        Assertions.assertEquals(userDepositDtoList.get(0).getTotalDeposit(), 2440500L);

        Assertions.assertEquals(userDepositDtoList.get(8).getId(), 12L);
        Assertions.assertEquals(userDepositDtoList.get(8).getTotalDeposit(), 671900L);

        Assertions.assertEquals(userDepositDtoList.get(18).getId(), 8L);
        Assertions.assertEquals(userDepositDtoList.get(18).getTotalDeposit(), -354500L);
    }

    @DisplayName("실패 테스트 : 기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력하시오")
    @Test
    void getUserListManyDepositDuringDateFailTest() {
        List<UserDepositDto> userDepositDtoList =
                statisticsService.getUserListManyDepositDuringDate(LocalDate.of(2019,1,1),
                        LocalDate.of(2020,12,31));
        Assertions.assertNotEquals(userDepositDtoList.size(), 19);
        Assertions.assertNotEquals(userDepositDtoList.get(0).getId(), 5L);
        Assertions.assertNotEquals(userDepositDtoList.get(0).getTotalDeposit(), 2440501L);

        Assertions.assertNotEquals(userDepositDtoList.get(8).getId(), 13L);
        Assertions.assertNotEquals(userDepositDtoList.get(8).getTotalDeposit(), 671901L);

        Assertions.assertNotEquals(userDepositDtoList.get(18).getId(), 7L);
        Assertions.assertNotEquals(userDepositDtoList.get(18).getTotalDeposit(), -354501L);
    }
}