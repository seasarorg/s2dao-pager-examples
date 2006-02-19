/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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

import org.seasar.dao.pager.PagerResultSetFactoryWrapper;
import org.seasar.extension.unit.S2TestCase;

/**
 * S2Pagerのテストです。
 * 
 * @author agata
 */
public class BookDaoBenchTest extends S2TestCase {

    private static String PATH = "BookDaoTest.dicon";

    private BookDao bookDao;

    private PagerResultSetFactoryWrapper resultSetFactoryWrapper;

    protected void setUp() throws Exception {
        include(PATH);
    }

    static long trueTime;

    static long falseTime;

    public void testBench_useAbsoluteTx() {
        trueTime = processBench(true);
    }

    public void testBench_notUseAbsoluteTx() {
        falseTime = processBench(false);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        System.out.println("-----------------------------");
        System.out.println("use absolute time =" + trueTime);
        System.out.println("not use absolute time =" + falseTime);
        System.out.println("-----------------------------");
    }

    private long processBench(boolean useAbsolute) {
        resultSetFactoryWrapper.setUseScrollCursor(useAbsolute);
        readXlsAllReplaceDb("BookDaoBench_BenchPrepare.xls");
        CategoryPagerCondition condition = new CategoryPagerCondition();
        condition.setLimit(10);
        condition.setOffset(9995);
        condition.setCategory("Java");

        long start = System.currentTimeMillis();
        List result = bookDao.findByCategoryPagerCondition(condition);
        long end = System.currentTimeMillis();
        // DataSet expected = readXls("BookDao_findByCategoryPagerConditionPage1Result.xls");
        assertEquals("取得レコード数", 5, result.size());
        assertEquals("総レコード数", 10000, condition.getCount());
        return end - start;
    }

}
