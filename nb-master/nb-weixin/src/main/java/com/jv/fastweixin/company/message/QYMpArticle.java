package com.jv.fastweixin.company.message;

import com.nb.fastweixin.api.entity.Article;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class QYMpArticle extends Article {

    public QYMpArticle(String thumbMediaId, String author, String title, String contentSourceUrl, String content, String digest, Integer showConverPic) {
        super(thumbMediaId, author, title, contentSourceUrl, content, digest, showConverPic);
    }

}
