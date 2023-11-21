package com.material.chain.base.interceptor;


import cn.hutool.core.util.StrUtil;
import com.material.chain.base.exception.ApiException;
import com.material.chain.base.page.PageList;
import com.material.chain.base.page.PageParam;
import com.material.chain.base.page.ThreadPagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * mybatis mysql 分页拦截器
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)}
)
@Slf4j
public class PageInterceptor implements Interceptor {
    private Integer maxPageSize;

    public PageInterceptor() {
        this.maxPageSize = 100;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("进入mybatis拦截器！！！");
        PageParam param = ThreadPagingUtil.get();
        if (param != null && param.getOpenPage()) {
            try {
                int start = 0;
                Integer limit = param.getPageSize();
                Integer currentPage = param.getPageNo();
                if (currentPage > 1) {
                    start = (currentPage - 1) * limit;
                }
                if (limit > maxPageSize) {
                    limit = maxPageSize;
                }
                String sql = getSqlByInvocation(invocation);
                if (StrUtil.isEmpty(sql)) {
                    return invocation.proceed();
                }

                sql = sql.trim();
                if (!sql.toLowerCase().startsWith("select")) {
                    throw new ApiException("非查询语句不能开启 ThreadPagingUtil 分页");
                }

                sql = "select SQL_CALC_FOUND_ROWS" + sql.substring(6);

                sql += " limit " + start + ", " + limit;

                resetSql2Invocation(invocation, sql);

                // 数据对象
                Object obj1 = invocation.proceed();

                if (!(obj1 instanceof List)) {
                    throw new ApiException("分页查询必须返回 List 对象");
                }

                List<?> list = (List<?>) obj1;

                if (CollectionUtils.isNotEmpty(list)) {

                    Executor ce = (Executor) invocation.getTarget();

                    Transaction transaction = ce.getTransaction();
                    ResultSet rs = transaction.getConnection().createStatement().executeQuery("select FOUND_ROWS()");

                    int count = 0;
                    while (rs.next()) {
                        count = rs.getInt(1);
                        break;
                    }

                    int pageSize = limit;

                    int left = count % pageSize;
                    int totalPage = left == 0 ? count / pageSize : count / pageSize + 1;

                    PageList<?> pageList = new PageList<>(list);

                    pageList.setTotal(count);
                    pageList.setPageNo(currentPage);

                    pageList.setHasNext(currentPage < totalPage);
                    pageList.setHasPre(currentPage > 1);
                    pageList.setSize(pageSize);
                    pageList.setPageSize(totalPage);
                    return pageList;
                } else {
                    PageList<?> pageList = new PageList<>(list);
                    pageList.setTotal(0);
                    pageList.setPageNo(currentPage);

                    pageList.setHasNext(false);
                    pageList.setHasPre(false);
                    pageList.setSize(limit);
                    pageList.setPageSize(0);
                    return pageList;
                }
            } finally {
                ThreadPagingUtil.clear();
            }

        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object obj) {
        return Plugin.wrap(obj, this);
    }

    @Override
    public void setProperties(Properties arg0) {
        // doSomething
    }

    /**
     * 获取sql语句
     *
     * @param invocation
     * @return
     */
    private String getSqlByInvocation(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        if (args.length == 6) {
            boundSql = (BoundSql) args[5];
        }
        return boundSql.getSql();
    }

    /**
     * 包装sql后，重置到invocation中
     *
     * @param invocation
     * @param sql
     * @throws SQLException
     */
    private void resetSql2Invocation(Invocation invocation, String sql) throws SQLException {
        final Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = statement.getBoundSql(parameterObject);
        if (invocation.getArgs().length == 6) {
            boundSql = (BoundSql) invocation.getArgs()[5];
        }
        MappedStatement newStatement = newMappedStatement(statement, new BoundSqlSqlSource(boundSql));
        MetaObject msObject = MetaObject.forObject(newStatement, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        msObject.setValue("sqlSource.boundSql.sql", sql);
        args[0] = newStatement;
    }

    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    // 定义一个内部辅助类，作用是包装sq
    class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
