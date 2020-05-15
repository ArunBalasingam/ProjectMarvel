package com.example.projectmarvel;

import java.util.List;

public class Data {
    private Integer offset;
    private Integer limit;
    private Integer total;

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getCount() {
        return count;
    }

    public List<Results> getResults() {
        return results;
    }

    private Integer count;
    private List<Results> results;
}

