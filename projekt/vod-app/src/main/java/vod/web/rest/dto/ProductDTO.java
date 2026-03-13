package vod.web.rest.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private String type;
    private int bakerId;
    private float rating;
}
