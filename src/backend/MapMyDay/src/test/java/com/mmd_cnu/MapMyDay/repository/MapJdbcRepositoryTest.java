package com.mmd_cnu.MapMyDay.repository;

import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.model.MapStatus;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_6_36;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class MapJdbcRepositoryTest {
    static EmbeddedMysql embeddedMysql;


    @BeforeAll
    static void setup() {
        var config = aMysqldConfig(v5_6_36)
                .withCharset(UTF8)
                .withPort(2215)
                .withUser("test", "test1234!")
                .withTimeZone("Asia/Seoul")
                .build();

        embeddedMysql = anEmbeddedMysql(config)
                .addSchema("test-map-mgmt", ScriptResolver.classPathScript("schema.sql"))
                .start();
    }

    @AfterAll
    static void cleanup() {
        embeddedMysql.stop();
    }

    @Autowired
    MapRepository mapRepository;

    private static final MapData newMap = new MapData(UUID.randomUUID(), UUID.randomUUID(), MapStatus.INCOMPLETE);

    @Test
    @Order(1)
    @DisplayName("관광 코스 전체 정보를 담은 map Data를 추가할 수 있다.")
    void testInsert() {
        mapRepository.insert(newMap);
        var all = mapRepository.findAll();
        System.out.println();
        assertThat(all.isEmpty(), is(false));
    }

    @Test
    @Order(2)
    @DisplayName("map id를 통해 map Data를 조회할 수 있다.")
    void testFindByMapId() {
        var map = mapRepository.findByMapId(newMap.getMapId());
        assertThat(map.isEmpty(), is(false));
    }

    @Test
    @Order(3)
    @DisplayName("user id를 통해 다수의 map Data를 조회할 수 있다.")
    void testFindByUserId() {
        var map = mapRepository.findByUserId(newMap.getUserId());
        assertThat(map.isEmpty(), is(false));
    }

    @Test
    @Order(4)
    @DisplayName("map update")
    void testSetMapStatus() {
        newMap.setMapStatus(MapStatus.COMPLETE);
        mapRepository.update(newMap);
        assertThat(mapRepository.findByMapId(newMap.getMapId()).get().getMapStatus(), is(MapStatus.COMPLETE));
    }

    @Test
    @Order(5)
    @DisplayName("map delete")
    void testDelete() {
        mapRepository.delete(newMap);
        assertThat(mapRepository.findAll(), is(true));
    }

    @Test
    @Order(6)
    @DisplayName("map deleteAll")
    void testDeleteAll() {
        mapRepository.insert(newMap);
        mapRepository.deleteAll();
        var all = mapRepository.findAll();
        assertThat(all.isEmpty(), is(true));
    }
}