package com.nb.fastweixin.api.response;

import java.util.List;

import com.nb.fastweixin.api.entity.UserRead;

/**
 * @author peiyu
 */
public class GetUserReadResponse extends BaseResponse {

    private List<UserRead> list;

    public List<UserRead> getList() {
        return list;
    }

    public void setList(List<UserRead> list) {
        this.list = list;
    }
}
