package com.it.mybatis.vo;

import java.util.List;

/*查询条件*/
public class AdminVo {
    private AdimCustomer adimCustomer;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    //多个id条件的查询
    private List<Integer> ids;

    public AdminVo() {
    }

    public AdimCustomer getAdimCustomer() {
        return adimCustomer;
    }

    public void setAdimCustomer(AdimCustomer adimCustomer) {
        this.adimCustomer = adimCustomer;
    }

    @Override
    public String toString() {
        return "AdminVo{" +
                "adimCustomer=" + adimCustomer +
                '}';
    }
}
