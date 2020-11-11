package com.evision.finance.eremittance.authenticator.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"content", "first", "previous", "pageNumber", "next", "last", "pageSize", "currPageRecords", "totalRecords", "totalPages", "empty"})
public class PageVO<V> implements Serializable {
    private List<V> content;

    private Boolean first;

    private Boolean previous;

    private Integer pageNumber;
    private Boolean next;
    private Boolean last;
    private Integer pageSize;
    private Integer currPageRecords;
    private Long totalRecords;
    private Integer totalPages;
    private Boolean empty;

    public Integer getPageNumber() {
        return this.pageNumber + 1;
    }
}
