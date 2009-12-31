/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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

import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

/**
 * ページャのサンプル
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
public class PagerClient {

    private static final String PATH = "examples/pager/PagerClient.dicon";

    public static void main(String[] args) {
        S2Container container = S2ContainerFactory.create(PATH);
        container.init();
        try {
            // 通常の検索
            BookDao dao = (BookDao) container.getComponent(BookDao.class);
            List employees = dao.findByCategory("Java");
            System.out.println("通常の検索----------");
            for (int i = 0; i < employees.size(); ++i) {
                int count = i + 1;
                System.out.println(count + "件目: " + employees.get(i));
            }

            // ページャによる検索
            CategoryPagerCondition dto = new CategoryPagerCondition();
            dto.setOffset(20);
            dto.setLimit(10);
            dto.setCategory("Java");
            employees = dao.findByCategoryPagerCondition(dto);
            System.out.println("ページャによる検索----------");
            System.out.println("offset=" + dto.getOffset());
            System.out.println("limit=" + dto.getLimit());
            System.out.println("count=" + dto.getCount());
            for (int i = 0; i < employees.size(); ++i) {
                int count = i + 1;
                System.out.println(count + "件目: " + employees.get(i));
            }

            // ページャによる検索
            dto.setOffset(20);
            dto.setLimit(10);
            dto.setCategory("Perl");
            employees = dao.findByCategoryPagerCondition(dto);
            System.out.println("ページャによる検索----------");
            System.out.println("offset=" + dto.getOffset());
            System.out.println("limit=" + dto.getLimit());
            System.out.println("count=" + dto.getCount());
            for (int i = 0; i < employees.size(); ++i) {
                int count = i + 1;
                System.out.println(count + "件目: " + employees.get(i));
            }

            // ページャによる検索
            dto.setOffset(20);
            dto.setLimit(10);
            dto.setCategory("C#");
            employees = dao.findByCategoryPagerCondition(dto);
            System.out.println("ページャによる検索----------");
            System.out.println("offset=" + dto.getOffset());
            System.out.println("limit=" + dto.getLimit());
            System.out.println("count=" + dto.getCount());
            for (int i = 0; i < employees.size(); ++i) {
                int count = i + 1;
                System.out.println(count + "件目: " + employees.get(i));
            }
        } finally {
            container.destroy();
        }
    }

}
