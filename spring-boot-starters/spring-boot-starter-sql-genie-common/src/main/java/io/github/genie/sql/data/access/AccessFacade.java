package io.github.genie.sql.data.access;

import io.github.genie.sql.api.ExpressionOperator.ComparableOperator;
import io.github.genie.sql.api.ExpressionOperator.NumberOperator;
import io.github.genie.sql.api.ExpressionOperator.PathOperator;
import io.github.genie.sql.api.ExpressionOperator.StringOperator;
import io.github.genie.sql.api.LockModeType;
import io.github.genie.sql.api.Order;
import io.github.genie.sql.api.Path;
import io.github.genie.sql.api.Path.ComparablePath;
import io.github.genie.sql.api.Path.NumberPath;
import io.github.genie.sql.api.Path.StringPath;
import io.github.genie.sql.api.Query.Collector;
import io.github.genie.sql.api.Query.ExpressionsBuilder;
import io.github.genie.sql.api.Query.OrderOperator;
import io.github.genie.sql.api.Query.QueryStructureBuilder;
import io.github.genie.sql.api.Query.Select;
import io.github.genie.sql.api.Query.SubQueryBuilder;
import io.github.genie.sql.api.Query.Where;
import io.github.genie.sql.api.Query.Where0;
import io.github.genie.sql.api.Root;
import io.github.genie.sql.api.Slice;
import io.github.genie.sql.api.Sliceable;
import io.github.genie.sql.api.TypedExpression;
import io.github.genie.sql.api.TypedExpression.PathExpression;
import io.github.genie.sql.api.Updater;
import io.github.genie.sql.api.tuple.Tuple;
import io.github.genie.sql.api.tuple.Tuple10;
import io.github.genie.sql.api.tuple.Tuple2;
import io.github.genie.sql.api.tuple.Tuple3;
import io.github.genie.sql.api.tuple.Tuple4;
import io.github.genie.sql.api.tuple.Tuple5;
import io.github.genie.sql.api.tuple.Tuple6;
import io.github.genie.sql.api.tuple.Tuple7;
import io.github.genie.sql.api.tuple.Tuple8;
import io.github.genie.sql.api.tuple.Tuple9;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class AccessFacade<T> implements BaseAccess<T> {

    protected abstract Select<T> select();

    protected abstract Updater<T> updater();

    @Override
    public <R> Where<T, R> select(Class<R> projectionType) {
        return select().select(projectionType);
    }

    @Override
    public Where0<T, Tuple> select(List<? extends TypedExpression<T, ?>> paths) {
        return select().select(paths);
    }

    @Override
    public Where0<T, Tuple> select(ExpressionsBuilder<T> selectBuilder) {
        return select().select(selectBuilder);
    }

    @Override
    public <R> Where0<T, R> select(TypedExpression<T, R> expression) {
        return select().select(expression);
    }

    @Override
    public <R> Where0<T, R> select(Path<T, ? extends R> path) {
        return select().select(path);
    }

    @Override
    public Where0<T, Tuple> select(Collection<Path<T, ?>> paths) {
        return select().select(paths);
    }

    @Override
    public <A, B> Where0<T, Tuple2<A, B>> select(Path<T, A> a, Path<T, B> b) {
        return select().select(a, b);
    }

    @Override
    public <A, B, C> Where0<T, Tuple3<A, B, C>> select(Path<T, A> a, Path<T, B> b, Path<T, C> c) {
        return select().select(a, b, c);
    }

    @Override
    public <A, B, C, D> Where0<T, Tuple4<A, B, C, D>> select(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d) {
        return select().select(a, b, c, d);
    }

    @Override
    public <A, B, C, D, E> Where0<T, Tuple5<A, B, C, D, E>> select(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e) {
        return select().select(a, b, c, d, e);
    }

    @Override
    public <A, B, C, D, E, F> Where0<T, Tuple6<A, B, C, D, E, F>> select(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f) {
        return select().select(a, b, c, d, e, f);
    }

    @Override
    public <A, B, C, D, E, F, G> Where0<T, Tuple7<A, B, C, D, E, F, G>> select(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f, Path<T, G> g) {
        return select().select(a, b, c, d, e, f, g);
    }

    @Override
    public <A, B, C, D, E, F, G, H> Where0<T, Tuple8<A, B, C, D, E, F, G, H>> select(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f, Path<T, G> g, Path<T, H> h) {
        return select().select(a, b, c, d, e, f, g, h);
    }

    @Override
    public <A, B, C, D, E, F, G, H, I> Where0<T, Tuple9<A, B, C, D, E, F, G, H, I>> select(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f, Path<T, G> g, Path<T, H> h, Path<T, I> i) {
        return select().select(a, b, c, d, e, f, g, h, i);
    }

    @Override
    public <A, B, C, D, E, F, G, H, I, J> Where0<T, Tuple10<A, B, C, D, E, F, G, H, I, J>> select(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f, Path<T, G> g, Path<T, H> h, Path<T, I> i, Path<T, J> j) {
        return select().select(a, b, c, d, e, f, g, h, i, j);
    }

    @Override
    public <A, B> Where0<T, Tuple2<A, B>> select(TypedExpression<T, A> a, TypedExpression<T, B> b) {
        return select().select(a, b);
    }

    @Override
    public <A, B, C> Where0<T, Tuple3<A, B, C>> select(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c) {
        return select().select(a, b, c);
    }

    @Override
    public <A, B, C, D> Where0<T, Tuple4<A, B, C, D>> select(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d) {
        return select().select(a, b, c, d);
    }

    @Override
    public <A, B, C, D, E> Where0<T, Tuple5<A, B, C, D, E>> select(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e) {
        return select().select(a, b, c, d, e);
    }

    @Override
    public <A, B, C, D, E, F> Where0<T, Tuple6<A, B, C, D, E, F>> select(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f) {
        return select().select(a, b, c, d, e, f);
    }

    @Override
    public <A, B, C, D, E, F, G> Where0<T, Tuple7<A, B, C, D, E, F, G>> select(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f, TypedExpression<T, G> g) {
        return select().select(a, b, c, d, e, f, g);
    }

    @Override
    public <A, B, C, D, E, F, G, H> Where0<T, Tuple8<A, B, C, D, E, F, G, H>> select(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f, TypedExpression<T, G> g, TypedExpression<T, H> h) {
        return select().select(a, b, c, d, e, f, g, h);
    }

    @Override
    public <A, B, C, D, E, F, G, H, I> Where0<T, Tuple9<A, B, C, D, E, F, G, H, I>> select(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f, TypedExpression<T, G> g, TypedExpression<T, H> h, TypedExpression<T, I> i) {
        return select().select(a, b, c, d, e, f, g, h, i);
    }

    @Override
    public <A, B, C, D, E, F, G, H, I, J> Where0<T, Tuple10<A, B, C, D, E, F, G, H, I, J>> select(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f, TypedExpression<T, G> g, TypedExpression<T, H> h, TypedExpression<T, I> i, TypedExpression<T, J> j) {
        return select().select(a, b, c, d, e, f, g, h, i, j);
    }

    @Override
    public <R> Where<T, R> selectDistinct(Class<R> projectionType) {
        return select().selectDistinct(projectionType);
    }

    @Override
    public Where0<T, Tuple> selectDistinct(List<? extends TypedExpression<T, ?>> paths) {
        return select().selectDistinct(paths);
    }

    @Override
    public Where0<T, Tuple> selectDistinct(ExpressionsBuilder<T> selectBuilder) {
        return select().selectDistinct(selectBuilder);
    }

    @Override
    public <R> Where0<T, R> selectDistinct(TypedExpression<T, R> expression) {
        return select().selectDistinct(expression);
    }

    @Override
    public <R> Where0<T, R> selectDistinct(Path<T, ? extends R> path) {
        return select().selectDistinct(path);
    }

    @Override
    public Where0<T, Tuple> selectDistinct(Collection<Path<T, ?>> paths) {
        return select().selectDistinct(paths);
    }

    @Override
    public <A, B> Where0<T, Tuple2<A, B>> selectDistinct(Path<T, A> a, Path<T, B> b) {
        return select().selectDistinct(a, b);
    }

    @Override
    public <A, B, C> Where0<T, Tuple3<A, B, C>> selectDistinct(Path<T, A> a, Path<T, B> b, Path<T, C> c) {
        return select().selectDistinct(a, b, c);
    }

    @Override
    public <A, B, C, D> Where0<T, Tuple4<A, B, C, D>> selectDistinct(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d) {
        return select().selectDistinct(a, b, c, d);
    }

    @Override
    public <A, B, C, D, E> Where0<T, Tuple5<A, B, C, D, E>> selectDistinct(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e) {
        return select().selectDistinct(a, b, c, d, e);
    }

    @Override
    public <A, B, C, D, E, F> Where0<T, Tuple6<A, B, C, D, E, F>> selectDistinct(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f) {
        return select().selectDistinct(a, b, c, d, e, f);
    }

    @Override
    public <A, B, C, D, E, F, G> Where0<T, Tuple7<A, B, C, D, E, F, G>> selectDistinct(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f, Path<T, G> g) {
        return select().selectDistinct(a, b, c, d, e, f, g);
    }

    @Override
    public <A, B, C, D, E, F, G, H> Where0<T, Tuple8<A, B, C, D, E, F, G, H>> selectDistinct(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f, Path<T, G> g, Path<T, H> h) {
        return select().selectDistinct(a, b, c, d, e, f, g, h);
    }

    @Override
    public <A, B, C, D, E, F, G, H, I> Where0<T, Tuple9<A, B, C, D, E, F, G, H, I>> selectDistinct(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f, Path<T, G> g, Path<T, H> h, Path<T, I> i) {
        return select().selectDistinct(a, b, c, d, e, f, g, h, i);
    }

    @Override
    public <A, B, C, D, E, F, G, H, I, J> Where0<T, Tuple10<A, B, C, D, E, F, G, H, I, J>> selectDistinct(Path<T, A> a, Path<T, B> b, Path<T, C> c, Path<T, D> d, Path<T, E> e, Path<T, F> f, Path<T, G> g, Path<T, H> h, Path<T, I> i, Path<T, J> j) {
        return select().selectDistinct(a, b, c, d, e, f, g, h, i, j);
    }

    @Override
    public <A, B> Where0<T, Tuple2<A, B>> selectDistinct(TypedExpression<T, A> a, TypedExpression<T, B> b) {
        return select().selectDistinct(a, b);
    }

    @Override
    public <A, B, C> Where0<T, Tuple3<A, B, C>> selectDistinct(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c) {
        return select().selectDistinct(a, b, c);
    }

    @Override
    public <A, B, C, D> Where0<T, Tuple4<A, B, C, D>> selectDistinct(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d) {
        return select().selectDistinct(a, b, c, d);
    }

    @Override
    public <A, B, C, D, E> Where0<T, Tuple5<A, B, C, D, E>> selectDistinct(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e) {
        return select().selectDistinct(a, b, c, d, e);
    }

    @Override
    public <A, B, C, D, E, F> Where0<T, Tuple6<A, B, C, D, E, F>> selectDistinct(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f) {
        return select().selectDistinct(a, b, c, d, e, f);
    }

    @Override
    public <A, B, C, D, E, F, G> Where0<T, Tuple7<A, B, C, D, E, F, G>> selectDistinct(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f, TypedExpression<T, G> g) {
        return select().selectDistinct(a, b, c, d, e, f, g);
    }

    @Override
    public <A, B, C, D, E, F, G, H> Where0<T, Tuple8<A, B, C, D, E, F, G, H>> selectDistinct(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f, TypedExpression<T, G> g, TypedExpression<T, H> h) {
        return select().selectDistinct(a, b, c, d, e, f, g, h);
    }

    @Override
    public <A, B, C, D, E, F, G, H, I> Where0<T, Tuple9<A, B, C, D, E, F, G, H, I>> selectDistinct(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f, TypedExpression<T, G> g, TypedExpression<T, H> h, TypedExpression<T, I> i) {
        return select().selectDistinct(a, b, c, d, e, f, g, h, i);
    }

    @Override
    public <A, B, C, D, E, F, G, H, I, J> Where0<T, Tuple10<A, B, C, D, E, F, G, H, I, J>> selectDistinct(TypedExpression<T, A> a, TypedExpression<T, B> b, TypedExpression<T, C> c, TypedExpression<T, D> d, TypedExpression<T, E> e, TypedExpression<T, F> f, TypedExpression<T, G> g, TypedExpression<T, H> h, TypedExpression<T, I> i, TypedExpression<T, J> j) {
        return select().selectDistinct(a, b, c, d, e, f, g, h, i, j);
    }

    @Override
    public Where<T, T> fetch(List<PathExpression<T, ?>> expressions) {
        return select().fetch(expressions);
    }

    @Override
    public Where<T, T> fetch(PathExpression<T, ?> path) {
        return select().fetch(path);
    }

    @Override
    public Where<T, T> fetch(PathExpression<T, ?> p0, PathExpression<T, ?> p1) {
        return select().fetch(p0, p1);
    }

    @Override
    public Where<T, T> fetch(PathExpression<T, ?> p0, PathExpression<T, ?> p1, PathExpression<T, ?> p3) {
        return select().fetch(p0, p1, p3);
    }

    @Override
    public Where<T, T> fetch(Collection<Path<T, ?>> paths) {
        return select().fetch(paths);
    }

    @Override
    public Where<T, T> fetch(Path<T, ?> path) {
        return select().fetch(path);
    }

    @Override
    public Where<T, T> fetch(Path<T, ?> p0, Path<T, ?> p1) {
        return select().fetch(p0, p1);
    }

    @Override
    public Where<T, T> fetch(Path<T, ?> p0, Path<T, ?> p1, Path<T, ?> p3) {
        return select().fetch(p0, p1, p3);
    }

    @Override
    public Where<T, T> where(TypedExpression<T, Boolean> predicate) {
        return select().where(predicate);
    }

    @Override
    public <N> PathOperator<T, N, ? extends Where<T, T>> where(Path<T, N> path) {
        return select().where(path);
    }

    @Override
    public <N extends Number & Comparable<N>> NumberOperator<T, N, ? extends Where<T, T>> where(NumberPath<T, N> path) {
        return select().where(path);
    }

    @Override
    public <N extends Comparable<N>> ComparableOperator<T, N, ? extends Where<T, T>> where(ComparablePath<T, N> path) {
        return select().where(path);
    }

    @Override
    public StringOperator<T, ? extends Where<T, T>> where(StringPath<T> path) {
        return select().where(path);
    }

    @Override
    public Collector<T> orderBy(List<? extends Order<T>> orders) {
        return select().orderBy(orders);
    }

    @Override
    public Collector<T> orderBy(Function<Root<T>, List<? extends Order<T>>> ordersBuilder) {
        return select().orderBy(ordersBuilder);
    }

    @Override
    public Collector<T> orderBy(Order<T> order) {
        return select().orderBy(order);
    }

    @Override
    public Collector<T> orderBy(Order<T> p0, Order<T> p1) {
        return select().orderBy(p0, p1);
    }

    @Override
    public Collector<T> orderBy(Order<T> order1, Order<T> order2, Order<T> order3) {
        return select().orderBy(order1, order2, order3);
    }

    @Override
    public OrderOperator<T, T> orderBy(Collection<Path<T, Comparable<?>>> paths) {
        return select().orderBy(paths);
    }

    @Override
    public OrderOperator<T, T> orderBy(Path<T, Comparable<?>> path) {
        return select().orderBy(path);
    }

    @Override
    public OrderOperator<T, T> orderBy(Path<T, Comparable<?>> p1, Path<T, Comparable<?>> p2) {
        return select().orderBy(p1, p2);
    }

    @Override
    public OrderOperator<T, T> orderBy(Path<T, Comparable<?>> p1, Path<T, Comparable<?>> p2, Path<T, Comparable<?>> p3) {
        return select().orderBy(p1, p2, p3);
    }

    @Override
    public long count() {
        return select().count();
    }

    @Override
    public List<T> getList(int offset, int maxResult, LockModeType lockModeType) {
        return select().getList(offset, maxResult, lockModeType);
    }

    @Override
    public List<T> getList(int offset, int maxResult) {
        return select().getList(offset, maxResult);
    }

    @Override
    public boolean exist(int offset) {
        return select().exist(offset);
    }

    @Override
    public Optional<T> first() {
        return select().first();
    }

    @Override
    public Optional<T> first(int offset) {
        return select().first(offset);
    }

    @Override
    public T getFirst() {
        return select().getFirst();
    }

    @Override
    public T getFirst(int offset) {
        return select().getFirst(offset);
    }

    @Override
    public T requireSingle() {
        return select().requireSingle();
    }

    @Override
    public Optional<T> single() {
        return select().single();
    }

    @Override
    public Optional<T> single(int offset) {
        return select().single(offset);
    }

    @Override
    public T getSingle() {
        return select().getSingle();
    }

    @Override
    public T getSingle(int offset) {
        return select().getSingle(offset);
    }

    @Override
    public List<T> getList() {
        return select().getList();
    }

    @Override
    public boolean exist() {
        return select().exist();
    }

    @Override
    public Optional<T> first(LockModeType lockModeType) {
        return select().first(lockModeType);
    }

    @Override
    public Optional<T> first(int offset, LockModeType lockModeType) {
        return select().first(offset, lockModeType);
    }

    @Override
    public T getFirst(LockModeType lockModeType) {
        return select().getFirst(lockModeType);
    }

    @Override
    public T getFirst(int offset, LockModeType lockModeType) {
        return select().getFirst(offset, lockModeType);
    }

    @Override
    public T requireSingle(LockModeType lockModeType) {
        return select().requireSingle(lockModeType);
    }

    @Override
    public Optional<T> single(LockModeType lockModeType) {
        return select().single(lockModeType);
    }

    @Override
    public Optional<T> single(int offset, LockModeType lockModeType) {
        return select().single(offset, lockModeType);
    }

    @Override
    public T getSingle(LockModeType lockModeType) {
        return select().getSingle(lockModeType);
    }

    @Override
    public T getSingle(int offset, LockModeType lockModeType) {
        return select().getSingle(offset, lockModeType);
    }

    @Override
    public List<T> getList(int offset, LockModeType lockModeType) {
        return select().getList(offset, lockModeType);
    }

    @Override
    public List<T> getList(LockModeType lockModeType) {
        return select().getList(lockModeType);
    }

    @Override
    public <R> R slice(Sliceable<T, R> sliceable) {
        return select().slice(sliceable);
    }

    @Override
    public Slice<T> slice(int offset, int limit) {
        return select().slice(offset, limit);
    }

    @Override
    public <X> SubQueryBuilder<X, T> asSubQuery() {
        return select().asSubQuery();
    }

    @Override
    public QueryStructureBuilder buildMetadata() {
        return select().buildMetadata();
    }

    @Override
    public Root<T> root() {
        return select().root();
    }

    @Override
    public T insert(T entity) {
        return updater().insert(entity);
    }

    @Override
    public List<T> insert(List<T> entities) {
        return updater().insert(entities);
    }

    @Override
    public List<T> update(List<T> entities) {
        return updater().update(entities);
    }

    @Override
    public T update(T entity) {
        return updater().update(entity);
    }

    @Override
    public void delete(Iterable<T> entities) {
        updater().delete(entities);
    }

    @Override
    public void delete(T entity) {
        updater().delete(entity);
    }

    @Override
    public T updateNonNullColumn(T entity) {
        return updater().updateNonNullColumn(entity);
    }
}
