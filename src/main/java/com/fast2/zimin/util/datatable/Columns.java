package com.fast2.zimin.util.datatable;

/**
 * Created by ez2sarang on 2014. 11. 11..
 */
public class Columns {
    private String data;
    private String name;
    private boolean searchable;
    private boolean orderable;
    private Search search = new Search();

    public Columns() {
    }
    public Columns(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "Columns{" +
                "data='" + data + '\'' +
                ", name='" + name + '\'' +
                ", searchable=" + searchable +
                ", orderable=" + orderable +
                ", search=" + search +
                '}';
    }
}
