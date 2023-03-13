package com.mmd_cnu.MapMyDay.repository;

import com.mmd_cnu.MapMyDay.model.Location;
import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.model.MapStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.nio.ByteBuffer;
import java.util.*;

public class LocationJdbcRepository implements LocationRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LocationJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//    @Override
//    public MapData insert(MapData map) {
//        var update = jdbcTemplate.update("INSERT INTO maps(map_id, user_id, map_status, location_id_list, route_id_list)" + " VALUES (UNHEX(REPLACE(:mapId,'-','')), UNHEX(REPLACE(:userId,'-','')), :mapStatus, NULL, NULL)", toParamMap(map));
//
//        if(update != 1) {
//            throw new RuntimeException("Noting was inserted");
//        }
//
//        return map;
//    }
//
//    @Override
//    public MapData update(MapData map) {
//        String sql = "UPDATE maps SET map_status = :mapStatus " +
//                "WHERE map_id = UNHEX(REPLACE(:mapId,'-',''))";
//        var update = jdbcTemplate.update(sql, toParamMap(map));
//        if (update != 1) {
//            throw new RuntimeException("Nothing was updated");
//        }
//        return map;
//    }
//
//    @Override
//    public void delete(MapData map) {
//        String sql = "DELETE FROM maps WHERE map_id = UNHEX(REPLACE(:mapId,'-',''))";
//        var update = jdbcTemplate.update(sql, toParamMap(map));
//        if (update != 1) {
//            throw new RuntimeException("Nothing was updated");
//        }
//    }
//
//    @Override
//    public void deleteAll() {
//        jdbcTemplate.update("DELETE FROM maps", Collections.emptyMap());
//    }
//
//    @Override
//    public List<MapData> findByUserId(UUID userId) {
//        return jdbcTemplate.query("SELECT * FROM maps WHERE user_id = UNHEX(REPLACE(:userId,'-',''))",
//                Collections.singletonMap("userId", userId.toString().getBytes()),
//                mapRowMapper);
//    }
//
//    @Override
//    public Optional<MapData> findByMapId(UUID mapId) {
//        try {
//            return Optional.ofNullable(
//                    jdbcTemplate.queryForObject("SELECT * FROM maps WHERE map_id = UNHEX(REPLACE(:mapId,'-',''))",
//                            Collections.singletonMap("mapId", mapId.toString().getBytes()),
//                            mapRowMapper)
//            );
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//
//
//    }
//
//    private static final RowMapper<MapData> mapRowMapper = (resultSet, i) -> {
//        var mapId = toUUID(resultSet.getBytes("map_id"));
//        var userId = toUUID(resultSet.getBytes("user_id"));
//        var mapStatus = MapStatus.valueOf(resultSet.getString("map_status"));
//
//        return new MapData(mapId, userId, mapStatus);
//    };
//
//    private Map<String, Object> toParamMap(MapData map) {
//        var paramMap = new HashMap<String, Object>();
//        paramMap.put("mapId", map.getMapId().toString().getBytes());
//        paramMap.put("userId", map.getUserId().toString().getBytes());
//        paramMap.put("mapStatus", map.getMapStatus().toString());
//        return paramMap;
//    }
//
//    private static UUID toUUID(byte[] bytes) {
//        var byteBuffer = ByteBuffer.wrap(bytes);
//        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
//    }
    @Override
    public List<Location> findAll() {
        return null;
    }

    @Override
    public Location insert(Location location) {
        return null;
    }

    @Override
    public Location update(Location location) {
        return null;
    }

    @Override
    public void delete(Location location) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Location> findByMapId(UUID locationId) {
        return Optional.empty();
    }
}
