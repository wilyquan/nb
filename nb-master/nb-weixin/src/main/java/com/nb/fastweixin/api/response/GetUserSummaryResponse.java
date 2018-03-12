package com.nb.fastweixin.api.response;

import java.util.List;

import com.nb.fastweixin.api.entity.UserSummary;

/**
 * @author peiyu
 */
public class GetUserSummaryResponse extends BaseResponse {

    private List<UserSummary> list;

    public List<UserSummary> getList() {
        return list;
    }

    public void setList(List<UserSummary> list) {
        this.list = list;
    }
}
