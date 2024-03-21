package io.github.genie.sql.builder;

import io.github.genie.sql.api.Column;
import io.github.genie.sql.api.Constant;
import io.github.genie.sql.api.Expression;
import io.github.genie.sql.api.From;
import io.github.genie.sql.api.From.Entity;
import io.github.genie.sql.api.From.SubQuery;
import io.github.genie.sql.api.Lists;
import io.github.genie.sql.api.LockModeType;
import io.github.genie.sql.api.Operation;
import io.github.genie.sql.api.Operator;
import io.github.genie.sql.api.Order;
import io.github.genie.sql.api.QueryStructure;
import io.github.genie.sql.api.Selection;
import io.github.genie.sql.api.Selection.EntitySelected;
import io.github.genie.sql.api.Selection.MultiSelected;
import io.github.genie.sql.api.Selection.ProjectionSelected;
import io.github.genie.sql.api.Selection.SingleSelected;
import io.github.genie.sql.api.Slice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

final class QueryStructures {

    @EqualsAndHashCode
    static class QueryStructureImpl implements QueryStructure, Cloneable {

        Selection select;

        From from;

        Expression where = Expressions.TRUE;

        List<? extends Expression> groupBy = Lists.of();

        List<? extends Order<?>> orderBy = Lists.of();

        Expression having = Expressions.TRUE;

        List<? extends Column> fetch = Lists.of();

        Integer offset;

        Integer limit;

        LockModeType lockType = LockModeType.NONE;

        public QueryStructureImpl(Selection select, From from) {
            this.select = select;
            this.from = from;
        }

        public QueryStructureImpl(Class<?> from) {
            this.from = new FromEntity(from);
            this.select = new EntitySelectedImpl(from, false);
        }

        protected QueryStructureImpl copy() {
            try {
                return (QueryStructureImpl) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Selection select() {
            return select;
        }

        @Override
        public From from() {
            return from;
        }

        @Override
        public Expression where() {
            return where;
        }

        @Override
        public List<? extends Expression> groupBy() {
            return groupBy;
        }

        @Override
        public List<? extends Order<?>> orderBy() {
            return orderBy;
        }

        @Override
        public Expression having() {
            return having;
        }

        @Override
        public Integer offset() {
            return offset;
        }

        @Override
        public Integer limit() {
            return limit;
        }

        @Override
        public LockModeType lockType() {
            return lockType;
        }

        @Override
        public List<? extends Column> fetch() {
            return fetch;
        }

        @Override
        public String toString() {

            return "select " + select
                   + (isEmpty(fetch) ? "" : " fetch " + QueryStructures.toString(fetch))
                   + " from " + from.type().getName()
                   + (where == null || Expressions.isTrue(where) ? "" : " where " + where)
                   + (isEmpty(groupBy) ? "" : " group by " + QueryStructures.toString(groupBy))
                   + (having == null || Expressions.isTrue(having) ? "" : " having " + having)
                   + (isEmpty(orderBy) ? "" : " orderBy " + QueryStructures.toString(orderBy))
                   + (offset == null ? "" : " offset " + offset)
                   + (limit == null ? "" : " limit " + limit)
                   + (lockType == null || lockType == LockModeType.NONE ? "" : " lock(" + lockType + ")");
        }

        private static boolean isEmpty(Collection<?> objects) {
            return objects == null || objects.isEmpty();
        }

    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class FromEntity implements Entity {
        private final Class<?> type;
    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class FromSubQuery implements SubQuery {
        private final QueryStructure queryStructure;
    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class OrderImpl<T> implements Order<T> {
        private final Expression expression;
        private final SortOrder order;

        @Override
        public String toString() {
            return expression + " " + order;
        }
    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class EntitySelectedImpl implements EntitySelected {
        private final Class<?> resultType;
        private final boolean distinct;

        @Override
        public String toString() {
            return resultType.getName();
        }
    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class ProjectionSelectedImpl implements ProjectionSelected {
        private final Class<?> resultType;
        private final boolean distinct;

        @Override
        public String toString() {
            return resultType.getName();
        }
    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class MultiSelectedImpl implements MultiSelected {
        private final List<? extends Expression> expressions;
        private final boolean distinct;

        @Override
        public String toString() {
            return String.valueOf(expressions);
        }

    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class SingleSelectedImpl implements SingleSelected {
        private final Class<?> resultType;
        private final Expression expression;
        private final boolean distinct;

        @Override
        public String toString() {
            return String.valueOf(expression);
        }
    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class SliceImpl<T> implements Slice<T> {
        private final List<T> data;
        private final long total;
        private final int offset;
        private final int limit;
    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class ConstantImpl implements Constant {
        private final Object value;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class OperationImpl implements Operation {
        private final List<? extends Expression> operands;
        private final Operator operator;
    }

    @lombok.Data
    @Accessors(fluent = true)
    static final class ColumnImpl implements Column {
        private final String[] paths;
        @Getter(lazy = true)
        private final String identity = String.join(".", paths);

        @Override
        public String toString() {
            return identity();
        }

        @Override
        public int size() {
            return paths.length;
        }

        @Override
        public String get(int i) {
            return paths[i];
        }

        @Override
        public Column get(String path) {
            String[] strings = new String[size() + 1];
            System.arraycopy(paths, 0, strings, 0, paths.length);
            strings[size()] = path;
            return new ColumnImpl(strings);
        }

        @Override
        public Column parent() {
            if (size() <= 1) {
                return null;
            }
            String[] strings = new String[size() - 1];
            System.arraycopy(paths, 0, strings, 0, strings.length);
            return new ColumnImpl(strings);
        }

        @NotNull
        @Override
        public Iterator<String> iterator() {
            return new ArrayIterator<>(paths);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return Arrays.equals(paths, ((ColumnImpl) o).paths);
        }

        @Override
        public Column get(Column column) {
            String[] paths = new String[size() + column.size()];
            int i = 0;
            for (String s : this) {
                paths[i++] = s;
            }
            for (String s : column) {
                paths[i++] = s;
            }
            return new ColumnImpl(paths);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(paths);
        }
    }

    @NotNull
    private static String toString(List<?> list) {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    private QueryStructures() {
    }
}
