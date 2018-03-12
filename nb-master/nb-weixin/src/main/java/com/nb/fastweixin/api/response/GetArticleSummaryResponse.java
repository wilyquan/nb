package com.nb.fastweixin.api.response;

import java.util.List;

import com.nb.fastweixin.api.entity.ArticleSummary;

/**
 * @author peiyu
 */
public class GetArticleSummaryResponse extends BaseResponse {

    private List<ArticleSummary> list;

    public List<ArticleSummary> getList() {
        return list;
    }

    public void setList(List<ArticleSummary> list) {
        this.list = list;
    }
}
