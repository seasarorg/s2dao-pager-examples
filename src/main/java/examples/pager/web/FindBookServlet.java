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
package examples.pager.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.dao.pager.PagerSupport;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import examples.pager.BookDao;
import examples.pager.CategoryPagerCondition;

/**
 * 書籍検索Servlet
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 */
public class FindBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookDao bookDao;

    /** ページャサポートクラス */
    private PagerSupport pager = new PagerSupport(10,
            CategoryPagerCondition.class, "categoryPagerCondition");

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookDao = (BookDao) SingletonS2ContainerFactory.getContainer()
                .getComponent(BookDao.class);
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // パラメータoffsetを元にページャのoffset位置を更新
        pager.updateOffset(request);

        // ページャの条件保持オブジェクトをセッションから取得
        // 存在しない場合は、PagerSupportのコンストラクタで
        // 渡されたクラスが新規に作成されます。
        CategoryPagerCondition dto = (CategoryPagerCondition) pager
                .getPagerCondition(request);

        // 条件保持オブジェクト中の独自の検索条件をセット
        // この場合、書籍カテゴリを表すcateogry
        String category = request.getParameter("category");
        if (category != null && category.length() != 0) {
            dto.setCategory(category);
        }

        // ページャ対応の検索を実行
        List books = bookDao.findByCategoryPagerCondition(dto);
        request.setAttribute("books", books);

        // 結果ページにフォワード
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

}
