CREATE TABLE maps
(
    map_id BINARY(16) PRIMARY KEY,
    user_id BINARY(16) NOT NULL,
    map_status VARCHAR(10) NOT NULL,
    location_id_list JSON,
    route_id_list JSON
)