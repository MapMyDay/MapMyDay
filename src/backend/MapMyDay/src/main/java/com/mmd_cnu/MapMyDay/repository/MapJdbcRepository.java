package com.mmd_cnu.MapMyDay.repository;

import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.model.MapStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.*;

@Repository
public class MapJdbcRepository implements MapRepository{
    private  final NamedParameterJdbcTemplate jdbcTemplate;

    public MapJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MapData> findAll() {
        return jdbcTemplate.query("select * from maps", mapRowMapper);
    }

    @Override
    public MapData insert(MapData map) {
        var update = jdbcTemplate.update("INSERT INTO maps(map_id, user_id, map_status)" + " VALUES (UNHEX(REPLACE(:mapId,'-','')), UNHEX(REPLACE(:userId,'-','')), :mapStatus)", toParamMap(map));

        if(update != 1) {
            throw new RuntimeException("Noting was inserted");
        }

        return map;
    }

    @Override
    public MapData update(MapData map) {
        return null;
    }

    @Override
    public List<MapData> findByUserId(UUID userId) {
        return jdbcTemplate.query("SELECT * FROM maps WHERE user_id = UNHEX(REPLACE(:userId,'-',''))",
                Collections.singletonMap("userId", userId.toString().getBytes()),
                mapRowMapper);
    }

    @Override
    public Optional<MapData> findByMapId(UUID mapId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM maps WHERE map_id = UNHEX(REPLACE(:mapId,'-',''))",
                            Collections.singletonMap("mapId", mapId.toString().getBytes()),
                            mapRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }


    }

    private static final RowMapper<MapData> mapRowMapper = (resultSet, i) -> {
        var mapId = toUUID(resultSet.getBytes("map_id"));
        var userId = toUUID(resultSet.getBytes("user_id"));
        var mapStatus = MapStatus.valueOf(resultSet.getString("map_status"));

        return new MapData(mapId, userId, mapStatus);
    };

    private Map<String, Object> toParamMap(MapData map) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("mapId", map.getMapId().toString().getBytes());
        paramMap.put("userId", map.getMapId().toString().getBytes());
        paramMap.put("mapStatus", map.getMapStatus().toString());
        return paramMap;
    }

    private static UUID toUUID(byte[] bytes) {
        var byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }
}
