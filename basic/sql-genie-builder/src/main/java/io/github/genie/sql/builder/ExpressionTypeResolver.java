package io.github.genie.sql.builder;

import io.github.genie.sql.api.Column;
import io.github.genie.sql.api.Constant;
import io.github.genie.sql.api.Expression;
import io.github.genie.sql.api.Lists;
import io.github.genie.sql.api.Operation;
import io.github.genie.sql.api.Operator;
import io.github.genie.sql.api.SubQuery;
import io.github.genie.sql.builder.meta.EntityType;
import io.github.genie.sql.builder.meta.Metamodel;
import io.github.genie.sql.builder.meta.Type;
import io.github.genie.sql.builder.reflect.PrimitiveTypes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author HuangChengwei
 * @since 2024-03-26 9:01
 */
public class ExpressionTypeResolver {
    private final Metamodel metamodel;

    private static final List<Class<? extends Number>> NUMBER_TYPES = Lists.of(
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            BigInteger.class,
            Float.class,
            Double.class,
            BigDecimal.class
    );


    public ExpressionTypeResolver(Metamodel metamodel) {
        this.metamodel = metamodel;
    }

    public Class<?> getExpressionType(Expression expression, Class<?> entityType) {
        if (expression instanceof Column) {
            return getColumnType((Column) expression, entityType);
        }
        if (expression instanceof Constant) {
            return getConstantType((Constant) expression);
        }
        if (expression instanceof Operation) {
            return getOperationType((Operation) expression, entityType);
        }
        if (expression instanceof SubQuery) {
            return getSubQueryType((SubQuery) expression);
        }
        return Object.class;
    }

    private Class<?> getSubQueryType(SubQuery subQuery) {
        return subQuery.queryStructure().from().type();
    }

    public Class<?> getOperationType(Operation expression, Class<?> entityType) {
        Operator operator = expression.operator();
        //noinspection EnhancedSwitchMigration
        switch (operator) {
            case NOT:
            case AND:
            case OR:
            case GT:
            case EQ:
            case NE:
            case GE:
            case LT:
            case LE:
            case LIKE:
            case IS_NULL:
            case IS_NOT_NULL:
            case IN:
            case BETWEEN:
                return Boolean.class;
            case LOWER:
            case UPPER:
            case SUBSTRING:
            case TRIM:
                return String.class;
            case LENGTH:
            case COUNT:
                return Long.class;
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case MOD:
            case SUM:
                return getNumberType(expression, entityType);
            case DIVIDE:
            case AVG:
                return Double.class;
            case NULLIF:
            case IF_NULL:
            case MIN:
            case MAX:
                return getFirstOperandType(expression, entityType);
        }
        return Object.class;
    }

    private Class<?> getFirstOperandType(Operation expression, Class<?> entityType) {
        if (!expression.operands().isEmpty()) {
            return getExpressionType(expression.operands().get(0), entityType);
        }
        return Object.class;
    }

    private Class<?> getNumberType(Operation expression, Class<?> entityType) {
        int index = -1;
        for (Expression operand : expression.operands()) {
            Class<?> type = getExpressionType(operand, entityType);
            if (type.isPrimitive()) {
                type = PrimitiveTypes.getWrapper(type);
            }
            int i = NUMBER_TYPES.indexOf(type);
            if (i < 0 || i == NUMBER_TYPES.size() - 1) {
                index = i;
                break;
            }
            index = Math.max(index, i);
        }
        if (index >= 0 && index < NUMBER_TYPES.size()) {
            return NUMBER_TYPES.get(index);
        }
        return Object.class;
    }

    public Class<?> getConstantType(Constant expression) {
        return expression.value().getClass();
    }

    public Class<?> getColumnType(Column column, Class<?> entityType) {
        Type t = metamodel.getEntity(entityType);
        for (String s : column) {
            t = ((EntityType) t).getAttribute(s);
        }
        return t.javaType();
    }

}
