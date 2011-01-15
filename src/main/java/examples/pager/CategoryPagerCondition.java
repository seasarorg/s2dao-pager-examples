/*
 * Copyright 2004-2011 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package examples.pager;

import java.io.Serializable;

import org.seasar.dao.pager.DefaultPagerCondition;

/**
 * 検索条件DTO。
 * 独自の検索条件はこのクラスのようにPagerConditionインターフェイスを実装する
 * クラスで実現します。通常はDefaultPagerConditionを継承するとよいでしょう。
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
public class CategoryPagerCondition extends DefaultPagerCondition implements
        Serializable {

    private static final long serialVersionUID = 1L;

    /** カテゴリー(検索条件) */
    private String category;

    /**
     * @return Returns the category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
