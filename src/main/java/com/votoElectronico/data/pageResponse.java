package com.votoElectronico.data;


import java.util.List;


import lombok.Data;

@Data
public class pageResponse<T> {
private Integer TotalItems;
private Boolean status;
private List<T> items;

}
