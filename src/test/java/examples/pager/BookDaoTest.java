/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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

import org.seasar.extension.dataset.DataSet;
import org.seasar.extension.unit.S2TestCase;

/**
 * S2Pagerのテストです。
 * 
 * @author agata
 */
public class BookDaoTest extends S2TestCase {

    private static String PATH = "BookDaoTest.dicon";

    private BookDao bookDao;

    public BookDaoTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        include(PATH);
    }

    public void testFindByCategoryTx() {
        readXlsAllReplaceDb("BookDao_Prepare.xls");
        List result = bookDao.findByCategory("Java");

        DataSet expected = readXls("BookDao_findByCategoryResult.xls");
        assertEquals(expected, result);
    }

    public void testFindByPagerConditionTx() throws Exception {
        readXlsAllReplaceDb("BookDao_Prepare.xls");

        CategoryPagerCondition condition = new CategoryPagerCondition();
        condition.setLimit(10);
        condition.setOffset(0);
        condition.setCategory("Java");
        List result = bookDao.findByCategoryPagerCondition(condition);
        DataSet expected = readXls("BookDao_findByCategoryPagerConditionPage1Result.xls");
        assertEquals("1ページ目", expected, result);
        assertEquals("総レコード数", 49, condition.getCount());

        condition.setOffset(10);
        result = bookDao.findByCategoryPagerCondition(condition);
        expected = readXls("BookDao_findByCategoryPagerConditionPage2Result.xls");
        assertEquals("2ページ目", expected, result);
        assertEquals("総レコード数", 49, condition.getCount());

        condition.setOffset(20);
        condition.setLimit(20);
        result = bookDao.findByCategoryPagerCondition(condition);
        expected = readXls("BookDao_findByCategoryPagerConditionPage3Result.xls");
        assertEquals("3ページ目", expected, result);
        assertEquals("総レコード数", 49, condition.getCount());

        condition.setOffset(40);
        result = bookDao.findByCategoryPagerCondition(condition);
        expected = readXls("BookDao_findByCategoryPagerConditionPage4Result.xls");
        assertEquals("4ページ目", expected, result);
        assertEquals("総レコード数", 49, condition.getCount());

        condition.setOffset(50);
        result = bookDao.findByCategoryPagerCondition(condition);
        expected = readXls("BookDao_findByCategoryPagerConditionPage4Result.xls");
        assertEquals("範囲外のため0", 0, result.size());
        assertEquals("総レコード数", 49, condition.getCount());
    }

}
